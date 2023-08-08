package Test;

import model.Loan;
import org.junit.jupiter.api.BeforeEach;
import service.LoanStoreService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LoanStoreServiceTest {
    LoanStoreService loanStoreService;
    DateTimeFormatter formatter;

    @BeforeEach
    void setUp() {
        loanStoreService = new LoanStoreService();
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Test
    void testAddLoanData() {
        Loan loan = new Loan("L1", "C1", "LEN1", 10000, 10000,
                LocalDate.parse("05/06/2023", formatter), 1,
                LocalDate.parse("05/07/2023", formatter), 0.01);

        loanStoreService.addLoanData(loan);
        assertEquals(1, loanStoreService.loans.size());
    }

    @Test
    void testAggregateAndPrint() {
        Loan loan1 = new Loan("L1", "C1", "LEN1", 10000, 9000,
                LocalDate.parse("05/06/2023", formatter), 1,
                LocalDate.parse("05/07/2023", formatter), 0.01);

        Loan loan2 = new Loan("L2", "C1", "LEN1", 20000, 19000,
                LocalDate.parse("06/06/2023", formatter), 1,
                LocalDate.parse("06/07/2023", formatter), 0.01);

        loanStoreService.addLoanData(loan1);
        loanStoreService.addLoanData(loan2);
        loanStoreService.aggregateAndPrint();
    }

    @Test
    void testAlert() {
        Loan loan = new Loan("L1", "C1", "LEN1", 10000, 10000,
                LocalDate.parse("05/06/2021", formatter), 1,
                LocalDate.parse("05/07/2021", formatter), 0.01);

        loanStoreService.addLoanData(loan);
        loanStoreService.Alert();

    }

    @Test
    void testPrintAllLoans() {
        Loan loan1 = new Loan("L1", "C1", "LEN1", 10000, 9000,
                LocalDate.parse("05/06/2023", formatter), 1,
                LocalDate.parse("05/07/2023", formatter), 0.01);

        Loan loan2 = new Loan("L2", "C1", "LEN1", 20000, 19000,
                LocalDate.parse("06/06/2023", formatter), 1,
                LocalDate.parse("06/07/2023", formatter), 0.01);

        loanStoreService.addLoanData(loan1);
        loanStoreService.addLoanData(loan2);
        loanStoreService.printAllLoans();
    }
}
