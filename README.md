  🏫 High School Management System (Project: Demo-CNPM)
------------------------------------------------------------------------------------------------
Hệ thống Quản trị Nhà trường & Đào tạo THPT toàn diện

Dự án được thiết kế nhằm số hóa toàn bộ quy trình vận hành của một trường Trung học Phổ thông, từ quản lý nhân sự, đào tạo đến tài chính và chính sách.

------------------------------------------------------------------------------------------------
📖 Tổng quan & Các Phân hệ Chức năng (Modules)

Hệ thống được tổ chức thành 5 phân hệ chính bám sát nghiệp vụ giáo dục thực tế:

1. 📂 Hồ sơ & Cơ cấu (Core Structure)
   
- Quản lý Lớp học: Tổ chức danh sách lớp theo khối, năm học.

- Quản lý Giáo viên: Lưu trữ hồ sơ nhân sự, trình độ chuyên môn

- Quản lý Tổ bộ môn: Phân tách quản lý theo đặc thù môn học (Toán, Lý, Hóa...).

 2. 📚 Đào tạo (Education Management)
   
- Quản lý Môn học: Thiết lập chương trình đào tạo và định mức môn học.

- Thời khóa biểu / Lịch dạy: Hệ thống sắp xếp lịch trình thông minh cho giáo viên và học sinh.

- Phòng học & Thiết bị: Quản lý cơ sở vật chất và tình trạng sử dụng phòng học.

3. 📊 Khảo thí & Kết quả (Assessment & Results)
   
- Quản lý Điểm số: Kế thừa dữ liệu Hồ sơ học sinh (đảm bảo tính toàn vẹn dữ liệu). Chỉ cho phép giáo viên, admin cập nhật (`UPDATE`) điểm số các môn và tự động tính toán điểm trung bình.

- Quản lý Hạnh kiểm: Đánh giá rèn luyện định kỳ của học sinh.

- Quản lý Lịch thi: Tổ chức và thông báo lịch thi tập trung.

4. 💰 Hành chính & Tài vụ (Administration & Finance)
   
- Quản lý Học phí: Theo dõi trạng thái đóng phí và xuất hóa đơn.

- Quản lý Thông báo: Kênh truyền thông nội bộ giữa nhà trường với giáo viên và học sinh.

- Quản lý Phúc khảo: Tiếp nhận và xử lý các yêu cầu xem lại kết quả học tập.

5. 🔐 Hệ thống & Chính sách (Security & Policy)
   
- Hồ sơ Học sinh (Chi tiết): Quản lý lý lịch học sinh.

- Quản lý Tài khoản User: Phân quyền người dùng (RBAC) chặt chẽ.

- Đối tượng chính sách: Quản lý miễn giảm và các chế độ ưu tiên cho học sinh.

-------------------------------------------------------------------------------------------------------------------
🏗️ Kiến trúc & Công nghệ (Tech Stack)

- Dự án áp dụng mô hình MVC (Model-View-Controller) kết hợp DAO Pattern để đảm bảo tính đóng gói và dễ bảo trì.

- Frontend: Java Swing với Custom UI Components (Sidebar động, giao diện tối ưu hóa trải nghiệm người dùng).

- Backend: Java Core xử lý Logic nghiệp vụ.

- Database: Microsoft SQL Server (Xử lý truy vấn phức tạp và đảm bảo toàn vẹn dữ liệu).

- Connection: JDBC Driver (Cấu hình encrypt=true;trustServerCertificate=true; để bảo mật kết nối).

📂 Sơ đồ Cấu trúc Dự án

Plaintext

<img width="630" height="179" alt="image" src="https://github.com/user-attachments/assets/a208afda-707b-4df9-9577-5db5f2b941f6" />

🛠️ Hướng dẫn Cài đặt

1. Cơ sở dữ liệu:

+ Sử dụng SQL Server 2025 (hoặc bản thấp hơn).

+ Tạo Database tên: QuanLyHocSinh.

+ Cấu hình tài khoản: user: sa, password: 123456.

2. Môi trường Java:

+ Clone dự án: git clone https://github.com/tranglee05/Demo-CNPM.git

+ Mở dự án bằng IntelliJ IDEA.

+ Cấu hình Project SDK là Java 17 hoặc cao hơn.

+ Import thư viện mssql-jdbc vào Project Structure.

3. Khởi chạy:

+ Chạy file QuanLyHocSinh.java để vào màn hình Đăng nhập.

+ Đăng nhập với các quyền Admin, Giáo viên hoặc Học sinh để trải nghiệm phân quyền Sidebar.
