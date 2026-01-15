package reservation;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {

    JTextField user;
    JPasswordField pass;

    public LoginFrame() {
        setTitle("Login - Online Reservation System");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        user = new JTextField();
        add(user);

        add(new JLabel("Password:"));
        pass = new JPasswordField();
        add(pass);

        JButton login = new JButton("Login");
        add(new JLabel());
        add(login);

        login.addActionListener(e -> authenticate());

        setVisible(true);
    }

    void authenticate() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");

            ps.setString(1, user.getText());
            ps.setString(2, new String(pass.getPassword()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new MenuFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
