package Test02;

import java.sql.*;

public class PsCRUD {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/zera";
            String user = "root";
            String password = "zera";
            connection = DriverManager.getConnection(url,user,password);

            //1.增
//            String sql = "insert into costumer (name,age,gender,password) values(?,?,?,?)";
//            ps = connection.prepareStatement(sql);
//            ps.setString(1,"a23");
//            ps.setInt(2,23);
//            ps.setString(3,"M");
//            ps.setString(4,"QWER1234");

            //2.删
//            String sql = "delete from costumer where id = ?";
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1,6);
//            ps.executeUpdate();

            //3.改
            String sql = "update costumer set age = ? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,22);
            ps.setInt(2,1);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
