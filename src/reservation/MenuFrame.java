package reservation;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Main Menu");
        setSize(300, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(4, 1, 10, 10));

        JButton reserve = new JButton("Reservation");
        JButton view = new JButton("View Ticket");
        JButton cancel = new JButton("Cancellation");
        JButton exit = new JButton("Exit");

        add(reserve);
        add(view);
        add(cancel);
        add(exit);

        reserve.addActionListener(e -> new ReservationFrame());
        view.addActionListener(e -> new ViewTicketFrame());
        cancel.addActionListener(e -> new CancellationFrame());
        exit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}
