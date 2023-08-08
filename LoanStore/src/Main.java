import com.sun.org.apache.bcel.internal.generic.SWITCH;
import model.Loan;
import service.LoanStoreService;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LoanStoreService loanStore = new LoanStoreService();

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1: Add Loan");
            System.out.println("2: Aggregate and Print");
            System.out.println("3: Check Due Dates and Print Alerts");
            System.out.println("4: Quit");
            System.out.println("4: Print All Loans");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Enter Loan details (Loan ID, Customer ID, Lender ID, Amount, Remaining Amount, Payment Date, Interest Per Day, Due Date, Penalty Per Day):");
                    String loanDetails = scanner.nextLine();
                    String[] parts = loanDetails.split(",");

                    Loan loan = new Loan(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),
                            LocalDate.parse(parts[5], formatter), Double.parseDouble(parts[6]),
                            LocalDate.parse(parts[7], formatter), Double.parseDouble(parts[8]));

                    loanStore.addLoanData(loan);
                    break;

                case "2":
                    loanStore.aggregateAndPrint();
                    break;

                case "3":
                    loanStore.Alert();
                    break;

                case "4":
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                case "5":
                    loanStore.printAllLoans();
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}