package atm_machine_LLD;

public class MiniStatementTransaction extends Transaction {

    public MiniStatementTransaction(String transactionId, String cardNumber) {
        super(transactionId, cardNumber, 0.0);
    }

    @Override
    public boolean execute(BankConnector bankConnector) {
        try {
            MiniStatement miniStatement = bankConnector.getMiniStatementObject(cardNumber);
            setStatus("COMPLETED");
            System.out.println("Mini Statement generated successfully:");
            System.out.println(miniStatement.toString());
            return true;
        } catch (Exception e) {
            setStatus("FAILED");
            System.out.println("Mini statement generation failed due to system error.");
            return false;
        }
    }

    public boolean executeAndPrint(BankConnector bankConnector, Printer printer) {
        try {
            MiniStatement miniStatement = bankConnector.getMiniStatementObject(cardNumber);
            setStatus("COMPLETED");
            printer.printMiniStatement(miniStatement);
            return true;
        } catch (Exception e) {
            setStatus("FAILED");
            System.out.println("Mini statement generation failed due to system error.");
            return false;
        }
    }

    @Override
    public String getTransactionType() {
        return "MINI_STATEMENT";
    }
}