import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "@#aditya2006";
        try(Connection connection = DriverManager.getConnection(url,username,password)) {

            System.out.println("connected to the database");
            System.out.println(connection);

        }catch(SQLException e) {

            System.out.println("Connection failed : "+e.getMessage());

        }
    }
}