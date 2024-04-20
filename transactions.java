import javax.xml.transform.Result;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;


public class transactions {

    private static final String url = "jdbc:mysql://localhost:3306/lenden";
    private static final String username = "root";
    private static final String password = "@#aditya2006";
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch(ClassNotFoundException e) {
            System.out.println(e);
        }

        try {
            Connection cn = DriverManager.getConnection(url,username,password);
            String debit = "update t set balence = balence - ? where acc_no = ? ";
            String credit = "update t set balence = balence + ? where acc_no = ?";
            PreparedStatement pdebit = cn.prepareStatement(debit);
            PreparedStatement pcredit = cn.prepareStatement(credit);
            Scanner sc = new Scanner(System.in);
            System.out.println("enter amount : ");
            double amount = sc.nextDouble();
            System.out.println("enter your account number : ");
            int account = sc.nextInt();
            pdebit.setDouble(1,amount);
            pdebit.setInt(2,account);

            pcredit.setDouble(1,amount);
            pcredit.setInt(2,102);
            pdebit.executeUpdate();
            pcredit.executeUpdate();
            if(isSufficient(cn,account,amount)) {
                cn.commit();
                System.out.println("transaction Done");
            }
//            else {
//                cn.rollback();
//                System.out.println("transation failed");
//            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    static boolean isSufficient(Connection cn,int acc_no,double amount) {
        try {
            String query = "select balence from t where acc_no = ?";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1,acc_no);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                double b = rs.getDouble("balence");
                if((b-amount) > 0){
                    return true;
                }
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
