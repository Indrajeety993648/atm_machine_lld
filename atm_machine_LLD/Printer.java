package atm_machine_LLD;

public class Printer {

    public void print(Receipt receipt) {
        System.out.println("Printing receipt...");
        System.out.println(receipt.toString());
        System.out.println("Please collect your receipt");
    }

    public void printMiniStatement(String statement) {
        System.out.println("Printing mini statement...");
        System.out.println(statement);
        System.out.println("Please collect your mini statement");
    }

    public void printMiniStatement(MiniStatement miniStatement) {
        System.out.println("Printing mini statement...");
        System.out.println(miniStatement.toString());
        System.out.println("Please collect your mini statement");
    }

    public boolean isAvailable() {
        return true; // Simulate printer availability
    }
}