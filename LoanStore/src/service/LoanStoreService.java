package service;

import model.Loan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LoanStoreService {

    public List<Loan> loans = new ArrayList<>();
    public void addLoanData(Loan loan){
            if(loan.getPaymentDate().isAfter(loan.getDueDate())){
                throw new IllegalArgumentException("Payment date can't be greater than dueDate");
            }
            loans.add(loan);
            System.out.println("Loan Added Successfully with Id : " + loan.getLoanId());

    }

    public void aggregateAndPrint() {
        Map<String, Double> remainingAmountByLender = loans.stream()
                .collect(Collectors.groupingBy(Loan::getLenderId, Collectors.summingDouble(Loan :: getRemainingAmount)));
        System.out.println("Remaining Amount by Lender: " + remainingAmountByLender);

        Map<Double, Double> interestByInterest = loans.stream()
                .collect(Collectors.groupingBy(Loan::getInterestPerDay,
                        Collectors.summingDouble(Loan::getInterestPerDay)));
        System.out.println("Interest Group by Interest: " + interestByInterest);

        Map<String, Double> penaltyByCustomerId = loans.stream()
                .collect(Collectors.groupingBy(Loan::getLenderId,
                        Collectors.summingDouble(Loan::getPenaltyPerDay)));
        System.out.println("Penalty GroupBy Customer: " + penaltyByCustomerId);

        // I am adding one more methods since
        // Requirements were not much to me, clear if you want remainingAmount group by (lenderId, Interest, Penalty)

        Map<String, Map<Double, Map<String, Double>>> remainingAmountByLenderInterestCustomer = loans.stream()
                .collect(Collectors.groupingBy(Loan::getLenderId,
                        Collectors.groupingBy(Loan::getInterestPerDay,
                                Collectors.groupingBy(Loan::getCustomerId,
                                        Collectors.summingDouble(Loan::getRemainingAmount)))));
        System.out.println("Remaining Amount by Lender, Interest, and Customer: " + remainingAmountByLenderInterestCustomer);

    }

    public void Alert(){
        LocalDate today = LocalDate.now();
        for(Loan loan : loans){
            if (loan.getDueDate().isBefore(today)) {
                System.out.println("Alert: Loan " + loan.getLoanId() + " is past due date!");
            }
        }
    }
    public void printAllLoans() {
        System.out.println("Loan ID\tCustomer ID\tLender ID\tAmount\tRemaining Amount\tPayment Date\tInterest Per Day\tDue Date\tPenalty Per Day");

        for (Loan loan : loans) {
            System.out.println(loan.getLoanId() + "\t" + loan.getCustomerId() + "\t" + loan.getLenderId() + "\t" + loan.getAmount() + "\t" + loan.getRemainingAmount() + "\t" +
                    loan.getPaymentDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\t" + loan.getInterestPerDay() + "\t" +
                    loan.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\t" + loan.getPenaltyPerDay());
        }
    }
}
