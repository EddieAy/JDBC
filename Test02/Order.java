package Test02;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Order {
    public static void main(String[] args) {
        Map<String, String> userOrder = initUI();

        boolean loginSuccess = loginMethod(userOrder);

        System.out.println(loginSuccess ? "Login Success":"Login Fail");

    }


    /**
     * 表示用于登录状态
     * @param userOrder
     * @return true表示登录成功 false表示登录失败
     */
    private static boolean loginMethod(Map<String, String> userOrder) {
        boolean loginSuccess = false;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String keywords = userOrder.get("Order");

        try {
            //1.初始化驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.建立连接
            String url = "jdbc:mysql://127.0.0.1:3306/zera";
            String user = "root";
            String password = "zera";
            connection = DriverManager.getConnection(url,user,password);
            //3.Create statement
            String sql = "select ename from emp order by ename " + keywords;
            statement = connection.createStatement();

            //4.执行sql
            //select * from costumer where name = 'Emma' and password = '   qweqe' or '1'='1  '
            //qweqe' or '1=
//            5.返回集合
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getString("ename"));
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
            if (statement != null) {
                try {
                    statement.close();
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
        System.out.println("输入ASC升序,DESC降序:");
        String order = s.nextLine();
        
        Map<String,String> userOrder = new HashMap<>();
        userOrder.put("Order",order);


        return userOrder;
    }
}
