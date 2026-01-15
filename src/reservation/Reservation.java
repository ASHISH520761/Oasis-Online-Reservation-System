package reservation;
import java.sql.*;
import java.util.Scanner;

public class Reservation {
    public static void bookTicket() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Age: ");
        int age = sc.nextInt();

        System.out.print("Train No: ");
        String tno = sc.next();

        String tname = "Express";

        System.out.print("Class Type: ");
        String cls = sc.next();

        System.out.print("Journey Date (YYYY-MM-DD): ");
        String date = sc.next();

        System.out.print("From: ");
        String from = sc.next();

        System.out.print("To: ");
        String to = sc.next();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO reservation VALUES (NULL,?,?,?,?,?,?,?,?)"
            );
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, tno);
            ps.setString(4, tname);
            ps.setString(5, cls);
            ps.setString(6, date);
            ps.setString(7, from);
            ps.setString(8, to);

            ps.executeUpdate();
            System.out.println("Ticket Reserved Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
