package reservation;
import java.sql.*;
import java.util.Scanner;

public class Cancellation {
    public static void cancelTicket() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter PNR: ");
        int pnr = sc.nextInt();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM reservation WHERE pnr=?"
            );
            ps.setInt(1, pnr);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Passenger: " + rs.getString("name"));
                System.out.println("Train: " + rs.getString("train_name"));
                System.out.print("Confirm Cancellation (Y/N): ");
                char ch = sc.next().charAt(0);

                if (ch == 'Y' || ch == 'y') {
                    PreparedStatement del = con.prepareStatement(
                        "DELETE FROM reservation WHERE pnr=?"
                    );
                    del.setInt(1, pnr);
                    del.executeUpdate();
                    System.out.println("Ticket Cancelled");
                }
            } else {
                System.out.println("Invalid PNR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
