📚 Smart Library Management System
Đồ án Cơ sở 1 - Hệ thống quản lý thư viện thông minh viết bằng ngôn ngữ Java.

📝 Giới thiệu
Dự án được xây dựng nhằm hỗ trợ việc quản lý sách, độc giả và quá trình mượn trả tại thư viện một cách tự động hóa. Hệ thống giúp giảm thiểu sai 
sót so với quản lý thủ công và cung cấp giao diện trực quan cho người sử dụng.

🛠 Công nghệ sử dụng
  - Ngôn ngữ: Java (JDK 11+)
  - Giao diện: Java Swing & AWT
  - Framework: Hibernate (ORM)
  - Cơ sở dữ liệu: MySQL 8.0
  - Công cụ phát triển: NetBeans IDE
  - Quản lý thư viện: Maven (hoặc JAR thủ công)

✨ Tính năng chính
  - Quản lý Tài khoản: Đăng nhập, phân quyền, thêm/sửa/xóa người dùng.
  - Quản lý Sách: Cập nhật thông tin sách, tác giả, thể loại, tình trạng sách.
  - Quản lý Độc giả: Lưu trữ thông tin cá nhân và lịch sử mượn sách.
  - Quản lý Mượn/Trả: Xử lý các phiếu mượn, tính toán hạn trả và xử lý vi phạm (nếu có).
  - Thống kê: Báo cáo số lượng sách tồn, sách đang được mượn.

🚀 Hướng dẫn cài đặt
  1. Chuẩn bị Cơ sở dữ liệu
    - Cài đặt MySQL Server.
    - Tạo database mới: CREATE DATABASE library_db;
    - Import file .sql (nếu có) trong thư mục database/ hoặc để Hibernate tự động tạo bảng (nếu cấu hình hbm2ddl.auto).

  2. Cấu hình Hibernate
    - Mở file src/hibernate.cfg.xml và cập nhật thông tin kết nối của bạn:
        XML
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/library_db</property>
        <property name="hibernate.connection.username">your_username</property>
        <property name="hibernate.connection.password">your_password</property>

  3. Chạy ứng dụng
    - Mở dự án bằng NetBeans IDE.
    - Đảm bảo các thư viện trong mục Libraries đã đầy đủ (Hibernate, MySQL Connector).
    - Chuột phải vào Project chọn Clean and Build.
    - Tìm file Main.java hoặc LoginUI.java, chuột phải chọn Run File.

📁 Cấu trúc thư mục
  - src/model: Chứa các lớp Entity (User, Book,...) định nghĩa dữ liệu.
  - src/dao: Lớp truy xuất dữ liệu sử dụng Hibernate Session.
  - src/view: Giao diện người dùng (JFrame, JPanel).
  - src/util: Các lớp hỗ trợ (HibernateUtil, Format...).

👥 Thành viên thực hiện
  - Nhóm: 2 người.
  - Thành viên 1: Nguyễn Xuân Anh 25IC001
  - Thành viên 2: Lê Việt Hoàng 25IC003

