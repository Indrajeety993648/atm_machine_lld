package atm_machine_LLD;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrintingState implements ATMState {

    @Override
    public void onCardInserted(ATM atm) {
        System.out.println("Transaction in progress. Please wait for receipt.");
    }

    @Override
    public void onPinEntered(ATM atm, String pin) {
        System.out.println("Transaction in progress. Please wait for receipt.");
    }

    @Override
    public void onSelectTransaction(ATM atm, String txType) {
        // Print receipt for the completed transaction
        printReceipt(atm);
        System.out.println("Transaction completed. Ejecting card.");
        atm.ejectCard();
    }

    @Override
    public void onAmountEntered(ATM atm, double amount) {
        // Print receipt for the completed transaction
        printReceipt(atm);
        System.out.println("Transaction completed. Ejecting card.");
        atm.ejectCard();
    }

    @Override
    public void onCancel(ATM atm) {
        System.out.println("Printing receipt and ejecting card.");
        printReceipt(atm);
        atm.ejectCard();
    }

    private void printReceipt(ATM atm) {
        String transactionId = "TXN" + System.currentTimeMillis();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double currentBalance = atm.getBankConnector().getBalance(atm.getCurrentCard().getCardNumber());

        Receipt receipt = new Receipt(
                transactionId,
                atm.getCurrentTransactionType(),
                atm.getCurrentAmount(),
                currentBalance,
                dateTime,
                atm.getCurrentCard().getCardNumber());

        atm.getPrinter().print(receipt);
    }
}