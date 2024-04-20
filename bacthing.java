
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Scanner;

public class bacthing {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String password = "@#aditya2006";
    private static final String username = "root";

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
//        try {
//            Connection cn = DriverManager.getConnection(url,username,password);
//            Statement st = cn.createStatement();
//            Scanner sc= new Scanner(System.in);
//            while(true) {
//                System.out.println("enter name,grade : ");
//                String name = sc.next();
//                String grade = sc.next();
//                System.out.println("enter more data : (Y/N)");
//                String choice = sc.next();
//                String query = String.format("insert into my(name,grade) values('%s','%s')", name, grade);
//                st.addBatch(query);
//
//                if (choice.toUpperCase().equals("N")) {
//                    break;
//                }
//            }
//                int afr[] = st.executeBatch();
//                for(int i=0;i<afr.length;i++){
//                    if(afr[i] == 0) {
//                        System.out.println(i+++"this query not inserted succesfully");
//                    }
//                }
//
//
//
//
//        }
        try {
            Connection cn = DriverManager.getConnection(url,username,password);
            String query = "insert into my(name,grade) values(?,?)";
            PreparedStatement ps = cn.prepareStatement(query);
            Scanner sc= new Scanner(System.in);
            while(true) {
                System.out.println("enter name,grade : ");
                String name = sc.next();
                String grade = sc.next();
                System.out.println("enter more data : (Y/N)");
                String choice = sc.next();
                ps.setString(1,name);
                ps.setString(2,grade);

                ps.addBatch();

                if (choice.toUpperCase().equals("N")) {
                    break;
                }
            }
            int afr[] = ps.executeBatch();
        }
        catch(SQLException e) {
            e.getMessage();
        }
    }
}