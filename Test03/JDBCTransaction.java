package Test03;

import java.sql.*;

public class JDBCTransaction {
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
            connection.setAutoCommit(false);

//            String sql = "update costumer set age = ? where name = ?";
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1,19);
//            ps.setString(2,"Emma");
//            ps.executeUpdate();
            String sql = "update costumer set age = ? where name = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,26);
            ps.setString(2,"Oliver");
            int count = ps.executeUpdate();


            String s = null;
            s.toString();
            ps.setInt(1,72);
            ps.setString(2,"Noah");
            count+= ps.executeUpdate();
            System.out.println(count == 2?"更新成功":"更新失败");

            connection.commit();

        } catch (ClassNotFoundException | SQLException e) {
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
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
