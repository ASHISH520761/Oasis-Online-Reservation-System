package reservation;

import javax.swing.*;
import java.sql.*;

public class CancellationFrame {

    public CancellationFrame() {
        String pnr = JOptionPane.showInputDialog("Enter PNR:");

        if (pnr == null || pnr.isEmpty()) return;

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM reservation WHERE pnr=?");
            ps.setInt(1, Integer.parseInt(pnr));

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Invalid PNR");
                return;
            }

            String details =
                "🎫 Ticket Details\n\n" +
                "PNR: " + rs.getInt("pnr") + "\n" +
                "Name: " + rs.getString("name") + "\n" +
                "Age: " + rs.getInt("age") + "\n" +
                "Train No: " + rs.getString("train_no") + "\n" +
                "Train Name: " + rs.getString("train_name") + "\n" +
                "Class: " + rs.getString("class_type") + "\n" +
                "Date: " + rs.getDate("journey_date") + "\n" +
                "From: " + rs.getString("source") + "\n" +
                "To: " + rs.getString("destination");

            int confirm = JOptionPane.showConfirmDialog(
                null,
                details + "\n\nConfirm Cancellation?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                PreparedStatement del =
                    con.prepareStatement("DELETE FROM reservation WHERE pnr=?");
                del.setInt(1, Integer.parseInt(pnr));
                del.executeUpdate();

                JOptionPane.showMessageDialog(null, "Ticket Cancelled Successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
