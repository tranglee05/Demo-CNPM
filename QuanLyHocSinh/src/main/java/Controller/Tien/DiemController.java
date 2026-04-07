package Controller.Tien; 

import Dao.DiemDAO;
import Dao.LopDAO;
import Dao.MonHocDAO;
import Model.Auth;
import Model.Diem;
import Model.LopGVCN;
import Model.MonHoc;
import View.Tien.QuanLyDiemPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import TienIch.XuatExcel;
import Model.Auth;

public class DiemController { 
    
    private QuanLyDiemPanel view;
    private DiemDAO dao;

    public DiemController(QuanLyDiemPanel view) {
        this.view = view;
        this.dao = new DiemDAO();
        
        loadComboBoxData();
        
        // Gán hành động cho các nút bấm ngay khi khởi tạo
        initEvents();
        
        // Load dữ liệu mặc định lên bảng luôn cho đỡ trống
        loadData(); 
    }

    private void loadComboBoxData() {
        LopDAO lopDAO = new LopDAO();
        MonHocDAO monHocDAO = new MonHocDAO();

        List<LopGVCN> lops = lopDAO.getAllLop();
        List<String> maLops = new ArrayList<>();
        for (LopGVCN l : lops) {
            maLops.add(l.getMaLop());
        }
        view.setMaLopData(maLops);

        List<MonHoc> mons = monHocDAO.getAll();
        List<String> maMons = new ArrayList<>();
        for (MonHoc m : mons) {
            maMons.add(m.getMaMH());
        }
        view.setMonHocData(maMons);

        List<Integer> hks = dao.getDistinctHocKy();
        if (hks.isEmpty()) {
            hks.add(1);
            hks.add(2);
        }
        view.setHocKyData(hks);
    }

    private void initEvents() {
        
        // 1. Nút Lọc (Xem danh sách theo lớp/môn/kỳ)
        view.addBtnXemListener(e -> loadData());

        // 2. Nút Tìm kiếm
        view.addBtnTimKiemListener(e -> searchData());

        // 3. Nút Cập Nhật Điểm (Logic quan trọng nhất)
        view.addBtnCapNhatListener(e -> {
            // Lấy dữ liệu điểm từ các ô nhập trên giao diện
            Diem d = view.getDiemInput();
            
            // Kiểm tra: Nếu view trả về null tức là người dùng nhập chữ vào ô số
            if (d == null) {
                view.showMessage("Điểm số phải là số thực (Ví dụ: 8.5)!"); 
                return;
            }
            // Kiểm tra: Phải chọn học sinh rồi mới sửa điểm được
            if (d.getMaHS().isEmpty()) {
                view.showMessage("Vui lòng click chọn học sinh trên bảng trước!"); 
                return;
            }

            // Gọi DAO để lưu xuống database
            if (dao.updateDiem(d)) {
                view.showMessage("Đã cập nhật điểm thành công!");
                loadData(); // Load lại bảng để thấy điểm mới vừa sửa
            } else {
                view.showMessage("Cập nhật thất bại! Hãy kiểm tra kết nối CSDL.");
            }
        });

        // 4. Sự kiện click vào bảng -> Đổ dữ liệu lên các ô nhập
        view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                view.fillFormInput(row); // Gọi hàm bên View để điền text
            }
        });
        
        // 5. Nút Xuất Excel (Dùng class tiện ích chung)
        view.addBtnXuatExcelListener(e -> {
            XuatExcel.xuatFileExcel(view.getTable(), view);
        });
    }

   /* // Hàm lấy danh sách điểm dựa theo bộ lọc (Lớp, Môn, Kỳ)
    private void loadData() {
        String maLop = view.getMaLopFilter();

        // Nếu chưa nhập lớp thì thôi không load (tránh lỗi query)
        if (maLop.isEmpty()) {
             // Có thể báo lỗi hoặc im lặng tùy bạn
             return;
        }

        String maMon = view.getMaMonFilter();
        int hocKy = view.getHocKyFilter();

        // Gọi DAO lấy list về và đẩy lên bảng
        List<Diem> list = dao.getDiemByFilter(maLop, maMon, hocKy);
        view.setTableData(list);
    }*/

    // Hàm tìm kiếm theo tên hoặc mã
    private void searchData() {
        String keyword = view.getTuKhoaTimKiem();

        if (keyword.isEmpty()) {
            view.showMessage("Nhập tên hoặc mã HS để tìm kiếm nhé!");
            return;
        }

        List<Diem> list = dao.searchDiem(keyword);
        view.setTableData(list);

        if (list.isEmpty()) {
            view.showMessage("Không tìm thấy học sinh nào với từ khóa: " + keyword);
        }
    }
    // Thêm cho phân quyền tài khoản
    // Import thêm Auth vào đầu file
    // import com.qlhs.main.Auth;

    private void loadData() {
        List<Diem> list;

        if (Auth.isHocSinh()) {
            // Lấy mã học sinh từ class Auth (viết hoa để đảm bảo trùng khớp mã trong database)
            String maHocSinh = Auth.maNguoiDung.toUpperCase();

            // Gọi đúng hàm getDiemByMaHS trong DiemDAO
            list = dao.getDiemByMaHS(maHocSinh);

            // Ẩn nút cập nhật nếu là học sinh
            if (view.getBtnCapNhat() != null) {
                view.getBtnCapNhat().setVisible(false);
            }

        } else {
            // NẾU LÀ ADMIN / GIÁO VIÊN: Load dữ liệu theo bộ lọc ComboBox
            String maLop = view.getMaLopFilter();

            // Tránh lỗi khi vừa mở form chưa có dữ liệu lớp
            if (maLop.isEmpty()) return;

            String maMon = view.getMaMonFilter();
            int hocKy = view.getHocKyFilter();

            // Gọi đúng hàm getDiemByFilter trong DiemDAO
            list = dao.getDiemByFilter(maLop, maMon, hocKy);
        }

        // Cuối cùng: Đổ danh sách lên bảng
        view.setTableData(list);
    }
}