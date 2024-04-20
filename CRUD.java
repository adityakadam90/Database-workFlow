import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUD {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String username = "root";
    private static final String password = "@#aditya2006";

    public static void main(String args[]) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner sc = new Scanner(System.in);

            int choice;
            while(true){
                System.out.print("enter your query choice.\n1.select\n2.insert\n3.update\n4.delete\n5.exit\n: ");
                choice = sc.nextInt();
                switch(choice) {
                    case 1:select();
                    break;
                    case 2:insert();
                    break;
                    case 3:update();
                    break;
                    case 4:delete();
                    break;
                    case 5: return;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void select(){
        try{
            Connection c = DriverManager.getConnection(url,username,password);
            Scanner sc = new Scanner(System.in);
            Statement s = c.createStatement();
            String query = String.format("select * from my;");
            ResultSet rs = s.executeQuery(query);
            System.out.println("+----+-----------+");
            System.out.println("| id |    name   |");
            System.out.println("+----+-----------+");
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("|%-4d|%-11s|\n",id,name);
                System.out.println("|----|-----------|");
            }
        }
        catch(SQLException e) {
            System.out.println("error in select");
        }
    }
    public static void insert(){
        try{
            Connection c = DriverManager.getConnection(url,username,password);
            Scanner sc = new Scanner(System.in);
            Statement s = c.createStatement();
            System.out.print("enter data (id,name) : ");
            int id = sc.nextInt();
            String name = sc.next();
            String query = String.format("insert into my values(%d,'%s')",id,name);
            int afr = s.executeUpdate(query);
            if(afr > 0){
                System.out.println("data inserted succesfully");
            }
            else {
                System.out.println("data not inserted");
            }

        }catch(SQLException e) {
            System.out.println("error in insert");
        }
    }
    public static void update(){
        try{
            Connection c = DriverManager.getConnection(url,username,password);
            Scanner sc = new Scanner(System.in);
            Statement s = c.createStatement();
            System.out.print("enter data (id) for updating name : ");
            int id = sc.nextInt();
            System.out.print("enter updating data(name) : ");
            String name = sc.next();
            String query = String.format("update my set name='%s' where id='%d'",name,id);
            int afr = s.executeUpdate(query);
            if(afr > 0){
                System.out.println("data updated succesfully");
            }
            else {
                System.out.println("data not updated");
            }

        }catch(SQLException e) {
            System.out.println("error in update");
        }
    }
    public static void delete() {
        try{
            Connection c = DriverManager.getConnection(url,username,password);
            Scanner sc = new Scanner(System.in);
            Statement s = c.createStatement();
            System.out.print("enter id for deleting its whole data : ");
            int id = sc.nextInt();
            String query = String.format("delete from my where id=%d",id);
            int afr = s.executeUpdate(query);
            if(afr > 0){
                System.out.println("data deleted succesfully");
            }
            else {
                System.out.println("data not deleted");
            }

        }catch(SQLException e) {
            System.out.println("error in delete");
        }
    }
}
