import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class test {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
/*        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionStr = "jdbc:mysql://localhost:3306/jwd_testing";
        Connection connection = DriverManager.getConnection(connectionStr,"root","12345");
        String selector = "SELECT * FROM roles";
        PreparedStatement preparedStatement  = connection.prepareStatement(selector);
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()){
            System.out.println(res.getInt(1) + "  " + res.getString(2));
        }*/

        String s1 = "Java";
        String s2 = new String("Java");
        System.out.println(s1==s2);
    }
}
