package level3;

import java.sql.*;
import java.util.Scanner;

public class Database3 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Accepted");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/chettinad",
                "root",
                "Mathesh@1303"
            );

            System.out.println("Connection success");

            Statement st = con.createStatement();

            System.out.print("Enter rno to delete: ");
            int rno = scan.nextInt();

            int result = st.executeUpdate(
                "delete from student where regno = " + rno
            );

            if (result > 0) {
                System.out.println("Successfully deleted");
            }
            else {
                System.out.println("No record found");
            }

            st.close();
            con.close();
            scan.close();

        }
        catch (Exception e) {
            System.out.println("Error Reason: " + e);
        }
    }
}