package reservation;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!Login.authenticate()) {
            System.out.println("Invalid Login");
            return;
        }

        while (true) {
            System.out.println("\n1. Reservation");
            System.out.println("2. Cancellation");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: Reservation.bookTicket(); break;
                case 2: Cancellation.cancelTicket(); break;
                case 3: System.exit(0);
            }
        }
    }
}
