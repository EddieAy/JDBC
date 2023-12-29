package Test01;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test03 {
    public static void main(String[] args) {


        String url = "jdbc:mysql://127.0.0.1:3306/zera";
        String user = "root";
        String passoword = "zera";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //不需要返回值  只需要类加载 这个动作
            Connection connection = DriverManager.getConnection(url,user,passoword);
            System.out.println("connection = " + connection);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
