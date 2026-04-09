/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.ThuTrang;

import Model.TKB;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;
import TienIch.ButtonStyleHelper;
import TienIch.TableSortHelper;

/**
 *
 * @author Admin
 */
public class FrmTKB extends JPanel {

    // ===== TABLE =====
    private JTable table;
    private DefaultTableModel model;

    // ===== FILTER =====
    private JTextField txtMaLopLoc;
    private JButton btnXemDanhSach, btnTimTheoLop;

    // ===== FORM =====
    private JTextField txtMaLopThem, txtMaMH, txtMaGV, txtPhong, txtTietBD, txtTietKT;
    private JComboBox<Integer> cboThuThem;
    private JButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnMoi, btnXuatExcel;

    public FrmTKB() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== 1. PANEL NORTH TỔNG: CHỨA TIÊU ĐỀ + TOOL =====
        // Dùng BorderLayout: Title ở trên, Tool ở dưới
        JPanel pnlNorth = new JPanel(new BorderLayout(0, 10));

        // -- Tiêu đề --
        //thêm ngày 09/04/2026
        String titleText = Model.Auth.isHocSinh() ? "THỜI KHÓA BIỂU" : "THỜI KHÓA BIỂU / LỊCH DẠY";
        JLabel title = new JLabel(titleText, JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(0, 102, 204));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Cách dưới 1 chút
        
        // Thêm tiêu đề vào vị trí trên cùng của pnlNorth
        pnlNorth.add(title, BorderLayout.NORTH);


        // -- Tool (Panel chứa các nút Xem và Tìm kiếm) --
        JPanel pnlTop = new JPanel(new GridLayout(2, 1, 5, 5));

        // Group Xem
        JPanel pnlView = new JPanel();
        pnlView.setBorder(new TitledBorder("Xem thời khóa biểu"));
        btnXemDanhSach = new JButton("Xem danh sách");
        ButtonStyleHelper.styleButtonView(btnXemDanhSach);
        pnlView.add(btnXemDanhSach);
        pnlTop.add(pnlView);

        // Group Tìm
        JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder("Tìm kiếm"));
        pnlSearch.add(new JLabel("Mã lớp:"));
        txtMaLopLoc = new JTextField(15);
        pnlSearch.add(txtMaLopLoc);
        btnTimTheoLop = new JButton("Tìm");
        ButtonStyleHelper.styleButtonSearch(btnTimTheoLop);
        pnlSearch.add(btnTimTheoLop);
        pnlTop.add(pnlSearch);

        // Thêm Tool vào phần giữa của pnlNorth (nằm dưới tiêu đề)
        pnlNorth.add(pnlTop, BorderLayout.CENTER);

        // ==> QUAN TRỌNG: Đưa cả khối pnlNorth ra giao diện chính
        add(pnlNorth, BorderLayout.NORTH);

        // ===== 2. TABLE (CENTER) =====
        model = new DefaultTableModel(
            new String[]{"ID", "Lớp", "Mã MH", "Tên MH", "GV", "Phòng", "Thứ", "Tiết BD", "Tiết KT"}, 0
        );
        table = new JTable(model);
        table.removeColumn(table.getColumnModel().getColumn(2)); // Ẩn cột Mã MH
        TableSortHelper.enableTableSorting(table);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(26);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setDefaultRenderer(new TienIch.CustomTableHeaderRenderer());
        add(new JScrollPane(table), BorderLayout.CENTER);


        // ===== 3. FORM INPUT (SOUTH) =====
        JPanel pnlSouth = new JPanel(new BorderLayout());
        pnlSouth.setBorder(new TitledBorder("Thêm / Cập nhật TKB"));

        JPanel pnlInput = new JPanel(new GridLayout(4, 4, 10, 8));

        pnlInput.add(new JLabel("Mã lớp"));
        txtMaLopThem = new JTextField();
        pnlInput.add(txtMaLopThem);

        pnlInput.add(new JLabel("Mã MH"));
        txtMaMH = new JTextField();
        pnlInput.add(txtMaMH);

        pnlInput.add(new JLabel("Mã GV"));
        txtMaGV = new JTextField();
        pnlInput.add(txtMaGV);

        pnlInput.add(new JLabel("Phòng"));
        txtPhong = new JTextField();
        pnlInput.add(txtPhong);

        pnlInput.add(new JLabel("Thứ"));
        cboThuThem = new JComboBox<>(new Integer[]{2, 3, 4, 5, 6, 7});
        pnlInput.add(cboThuThem);

        pnlInput.add(new JLabel("Tiết BD"));
        txtTietBD = new JTextField();
        pnlInput.add(txtTietBD);

        pnlInput.add(new JLabel("Tiết KT"));
        txtTietKT = new JTextField();
        pnlInput.add(txtTietKT);

        pnlSouth.add(pnlInput, BorderLayout.CENTER);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnThem = new JButton("Thêm");
        ButtonStyleHelper.styleButtonAdd(btnThem);
        btnSua = new JButton("Sửa");
        ButtonStyleHelper.styleButtonEdit(btnSua);
        btnXoa = new JButton("Xóa");
        ButtonStyleHelper.styleButtonDelete(btnXoa);
        btnLuu = new JButton("Lưu");
        ButtonStyleHelper.styleButtonSave(btnLuu);
        btnHuy = new JButton("Hủy");
        ButtonStyleHelper.styleButtonCancel(btnHuy);
        btnMoi = new JButton("Làm Mới");
        ButtonStyleHelper.styleButtonView(btnMoi);
        btnXuatExcel = new JButton("Xuất Excel");
        ButtonStyleHelper.styleButtonExport(btnXuatExcel);

        Dimension sz = new Dimension(90, 35);
        btnThem.setPreferredSize(sz);
        btnSua.setPreferredSize(sz);
        btnXoa.setPreferredSize(sz);
        btnLuu.setPreferredSize(sz);
        btnHuy.setPreferredSize(sz);
        btnMoi.setPreferredSize(sz);
        btnXuatExcel.setPreferredSize(new Dimension(120, 35));

        pnlBtn.add(btnThem);
        pnlBtn.add(btnSua);
        pnlBtn.add(btnXoa);
        pnlBtn.add(btnLuu);
        pnlBtn.add(btnHuy);
        pnlBtn.add(btnMoi);
        pnlBtn.add(btnXuatExcel);

        pnlSouth.add(pnlBtn, BorderLayout.SOUTH);
        add(pnlSouth, BorderLayout.SOUTH);
        //thêm này 09/04/2026
        if (Model.Auth.isHocSinh()) {
            pnlSearch.setVisible(false);
            pnlSouth.setVisible(false);
        }
    }

    /* =================================================
       DATA
       ================================================= */
    public String getMaLopLoc() {
        return txtMaLopLoc.getText().trim();
    }

    public TKB getTKBInput() {
        TKB t = new TKB();
        t.setMaLop(txtMaLopThem.getText().trim());
        t.setMaMH(txtMaMH.getText().trim());
        t.setMaGV(txtMaGV.getText().trim());
        t.setMaPhong(txtPhong.getText().trim());
        t.setThu((int) cboThuThem.getSelectedItem());
        t.setTietBatDau(Integer.parseInt(txtTietBD.getText()));
        t.setTietKetThuc(Integer.parseInt(txtTietKT.getText()));
        return t;
    }

    public void setTableData(List<TKB> list) {
        model.setRowCount(0);
        for (TKB t : list) {
            model.addRow(new Object[]{
                t.getMaTKB(), t.getMaLop(), t.getMaMH(), t.getTenMH(),
                t.getMaGV(), t.getMaPhong(),
                t.getThu(), t.getTietBatDau(), t.getTietKetThuc()
            });
        }
    }

    public JTable getTable() { return table; }
    
    public JButton getBtnThem() { return btnThem; }
    public JButton getBtnSua() { return btnSua; }
    public JButton getBtnXoa() { return btnXoa; }
    public JButton getBtnLuu() { return btnLuu; }
    public JButton getBtnHuy() { return btnHuy; }

    public void clearForm() {
        txtMaLopThem.setText("");
        txtMaMH.setText("");
        txtMaGV.setText("");
        txtPhong.setText("");
        txtTietBD.setText("");
        txtTietKT.setText("");
    }

    public void fillForm(int viewRow) {
        if (viewRow < 0) return;
        int row = table.convertRowIndexToModel(viewRow);
        txtMaLopThem.setText(model.getValueAt(row,1).toString());
        txtMaMH.setText(model.getValueAt(row,2).toString()); // Cột ẩn Mã MH
        txtMaGV.setText(model.getValueAt(row,4).toString());
        txtPhong.setText(model.getValueAt(row,5).toString());
        cboThuThem.setSelectedItem(Integer.parseInt(model.getValueAt(row,6).toString()));
        txtTietBD.setText(model.getValueAt(row,7).toString());
        txtTietKT.setText(model.getValueAt(row,8).toString());
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    /* =================================================
       EVENTS
       ================================================= */
    public void addBtnXemDanhSachListener(ActionListener l) {
        btnXemDanhSach.addActionListener(l);
    }

    public void addBtnTimTheoLopListener(ActionListener l) {
        btnTimTheoLop.addActionListener(l);
    }

    public void addBtnThemListener(ActionListener l) {
        btnThem.addActionListener(l);
    }

    public void addBtnSuaListener(ActionListener l) {
        btnSua.addActionListener(l);
    }

    public void addBtnXoaListener(ActionListener l) {
        btnXoa.addActionListener(l);
    }

    public void addBtnLuuListener(ActionListener l) {
        btnLuu.addActionListener(l);
    }

    public void addBtnHuyListener(ActionListener l) {
        btnHuy.addActionListener(l);
    }

    public void addBtnMoiListener(ActionListener l) {
        btnMoi.addActionListener(l);
    }

    public void addBtnXuatExcelListener(ActionListener l) {
        btnXuatExcel.addActionListener(l);
    }

    public void addTableMouseListener(MouseAdapter l) {
        table.addMouseListener(l);
    }
}
