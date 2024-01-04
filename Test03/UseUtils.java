package Test03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UseUtils {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = utils.getConnection();

            String sql = "select ename from emp where ename like ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1,"_A%");
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("ename"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            utils.close(connection,ps,resultSet);
        }
    }
}
