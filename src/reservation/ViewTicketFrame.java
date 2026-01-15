package reservation;

import javax.swing.*;
import java.sql.*;

public class ViewTicketFrame {

    public ViewTicketFrame() {
        String pnr = JOptionPane.showInputDialog("Enter PNR to View Ticket:");

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

            JOptionPane.showMessageDialog(null, details, 
                "View Ticket", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
