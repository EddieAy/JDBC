package Test02;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NewLogin {
    public static void main(String[] args) {
        Map<String, String> userInfoPassword = initUI();

        boolean loginSuccess = loginMethod(userInfoPassword);

        System.out.println(loginSuccess ? "Login Success":"Login Fail");

    }


    /**
     * 表示用于登录状态
     * @param userInfoPassword
     * @return true表示登录成功 false表示登录失败
     */
    private static boolean loginMethod(Map<String, String> userInfoPassword) {
        boolean loginSuccess = false;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            //1.初始化驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.建立连接
            String url = "jdbc:mysql://127.0.0.1:3306/zera";
            String user = "root";
            String password = "zera";
            connection = DriverManager.getConnection(url,user,password);
            //3.Create statement
            String sql = "select * from costumer where name = ? and password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,userInfoPassword.get("UserName"));
            ps.setString(2,userInfoPassword.get("Password"));

            //4.执行sql
            resultSet = ps.executeQuery();
            //select * from costumer where name = 'Emma' and password = '   qweqe' or '1'='1  '
            //qweqe' or '1=
//            5.返回集合
            if (resultSet.next()){
                loginSuccess = true;
            }

            return loginSuccess;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //6.释放资源
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

    /**
     * 初始化登录信息
     * @return
     */

    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("UserName:");
        String username = s.nextLine();
        System.out.println("Password:");
        String password = s.nextLine();
        Map<String,String> userInfoPassword = new HashMap<>();
        userInfoPassword.put("UserName",username);
        userInfoPassword.put("Password",password);


        return userInfoPassword;
    }
}
