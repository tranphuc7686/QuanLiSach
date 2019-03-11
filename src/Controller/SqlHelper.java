package Controller;

import Model.Sach;
import Model.SinhVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlHelper {
    // Kết nối vào SQLServer.
    // (Sử dụng thư viện điều khiển SQLJDBC)
    public static Connection getSQLServerConnection()
            throws SQLException, ClassNotFoundException {
        String hostName = "127.0.0.1";
        String sqlInstanceName = "";
        String database = "QUANLISACH";
        String userName = "sa";
        String password = "sa";

        return getSQLServerConnection(hostName, sqlInstanceName,
                database, userName, password);
    }

    // Trường hợp sử dụng SQLServer.
    // Và thư viện SQLJDBC.
    public static Connection getSQLServerConnection(String hostName,
                                                    String sqlInstanceName, String database, String userName,
                                                    String password) throws ClassNotFoundException, SQLException {
        // Khai báo class Driver cho DB SQLServer
        // Việc này cần thiết với Java 5
        // Java6 tự động tìm kiếm Driver thích hợp.
        // Nếu bạn dùng Java6, thì ko cần dòng này cũng được.
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Cấu trúc URL Connection dành cho SQLServer
        // Ví dụ:
        // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
                + ";databaseName=" + database;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        System.out.println(conn.toString());
        return conn;
    }

    public List<SinhVien> getAllSinhVien() throws SQLException, ClassNotFoundException {
        List<SinhVien> resulf = new ArrayList<>();
        // Lấy ra đối tượng Connection kết nối vào DB.
        Connection connection = this.getSQLServerConnection();

        // Tạo đối tượng Statement.
        Statement statement = connection.createStatement();

        String sql = "Select * from dangnhap";

        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        ResultSet rs = statement.executeQuery(sql);

        // Duyệt trên kết quả trả về.
        while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
            String taiKhoang = rs.getString(1);
            String msv = rs.getString(2);
            String pass = rs.getString(3);
            String maMuon = rs.getString(4);
            resulf.add(new SinhVien(taiKhoang, msv, pass,maMuon));
        }
        // Đóng kết nối
        connection.close();
        return resulf;
    }

    public List<Sach> getAllSach() throws SQLException, ClassNotFoundException {
        List<Sach> resulf = new ArrayList<>();
        // Lấy ra đối tượng Connection kết nối vào DB.
        Connection connection = this.getSQLServerConnection();

        // Tạo đối tượng Statement.
        Statement statement = connection.createStatement();

        String sql = "Select * from sach";

        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        ResultSet rs = statement.executeQuery(sql);

        // Duyệt trên kết quả trả về.
        while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
            String maSach = rs.getString(1);
            String tenSach = rs.getString(2);
            String theLoai = rs.getString(3);
            String nxb = rs.getString(4);
            String msv = rs.getString(5);
            String giaTien = rs.getString(6);
            resulf.add(new Sach(maSach, tenSach, theLoai, nxb, msv,giaTien));
        }
        // Đóng kết nối
        connection.close();
        return resulf;
    }

    public boolean executeUpdate(String sqlPara) throws SQLException, ClassNotFoundException {
        // Lấy ra kết nối tới cơ sở dữ liệu.
        Connection connection = null;
        boolean resulf = false;
         connection = this.getSQLServerConnection();

            Statement statement = connection.createStatement();
            String sql = sqlPara;

            // Thực thi câu lệnh.
            // executeUpdate(String) sử dụng cho các loại lệnh Insert,Update,Delete.
            int rowCount = statement.executeUpdate(sql);

            // In ra số dòng được trèn vào bởi câu lệnh trên.
            if (rowCount >= 1) {
                resulf = true;
            }

        return resulf;

    }
}
