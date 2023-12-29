package Test01;

import java.sql.*;
import java.util.ResourceBundle;

public class Test04 {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Test01/jdbc");
        String driver = resourceBundle.getString("driver");
        String url= resourceBundle.getString("url");
        String user = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");

        String sql = "select ename,sal,hiredate from emp";
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
//            resultSet.get
            System.out.println("ename" + "\t" + "sal" + "\t" + "hiredate");
            while (resultSet.next()){
                System.out.println(
                        resultSet.getString("ename")
                                + "\t"
                                + resultSet.getString("sal")
                                + "\t"
                                + resultSet.getString("hiredate"));
            }
//            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
