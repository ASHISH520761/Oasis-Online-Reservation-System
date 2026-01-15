package reservation;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ReservationFrame extends JFrame {

    JTextField name, age, trainNo, cls, date, from, to;

    public ReservationFrame() {
        setTitle("Reservation");
        setSize(400, 350);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(8, 2, 5, 5));

        name = field("Name");
        age = field("Age");
        trainNo = field("Train No");
        cls = field("Class");
        date = field("Journey Date (YYYY-MM-DD)");
        from = field("From");
        to = field("To");

        JButton book = new JButton("Book Ticket");
        add(new JLabel());
        add(book);

        book.addActionListener(e -> bookTicket());

        setVisible(true);
    }

    JTextField field(String label) {
        add(new JLabel(label));
        JTextField tf = new JTextField();
        add(tf);
        return tf;
    }

    void bookTicket() {
        try {
            // 🔐 BASIC VALIDATION
            if (name.getText().isEmpty() || age.getText().isEmpty()
                    || trainNo.getText().isEmpty() || cls.getText().isEmpty()
                    || date.getText().isEmpty()
                    || from.getText().isEmpty() || to.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "All fields are required!");
                return;
            }

            int ageValue;
            try {
                ageValue = Integer.parseInt(age.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Age must be a number");
                return;
            }

            Connection con = DBConnection.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(this,
                        "Database connection failed");
                return;
            }

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO reservation " +
                "(name, age, train_no, train_name, class_type, journey_date, source, destination) " +
                "VALUES (?,?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, name.getText());
            ps.setInt(2, ageValue);
            ps.setString(3, trainNo.getText());
            ps.setString(4, "Express");
            ps.setString(5, cls.getText());
            ps.setDate(6, Date.valueOf(date.getText())); // SAFE DATE
            ps.setString(7, from.getText());
            ps.setString(8, to.getText());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int pnr = rs.getInt(1);

            JOptionPane.showMessageDialog(this,
                "🎟 Ticket Reserved Successfully\n\nPNR: " + pnr);

            dispose();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                "Date format must be YYYY-MM-DD");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error while booking ticket:\n" + ex.getMessage());
        }
    }
}
