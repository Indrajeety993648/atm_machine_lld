package atm_machine_LLD;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ATM Machine Simulation ===");
        System.out.println("Demonstrating different ATM creation approaches...\n");

        // Create a sample card
        Card card = new Card("1234567890123456", "John Doe", "12/25", "ABC Bank");

        // Method 1: Using Factory Pattern
        System.out.println("1. Creating Standard ATM using Factory Pattern:");
        ATM standardATM = ATMFactory.createStandardATM();
        demonstrateATMOperations(standardATM, card, "Standard ATM");

        System.out.println("\n" + "=".repeat(70));

        // Method 2: Using Builder Pattern
        System.out.println("\n2. Creating Modern ATM using Builder Pattern:");
        ATM modernATM = new ATMBuilder()
                .withKeypad(new DigitalKeypad())
                .withDispenseStrategy(new MinimumNotesStrategy())
                .build();
        demonstrateATMOperations(modernATM, card, "Modern ATM");

        System.out.println("\n" + "=".repeat(70));

        // Method 3: Using Dependency Injection
        System.out.println("\n3. Creating ATM with Dependency Injection:");
        CardReader cardReader = new CardReader();
        PinEntry pinEntry = new PinEntry(new PhysicalKeypad());
        CashDispenser dispenser = new CashDispenser(new MinimumNotesStrategy());
        Printer printer = new Printer();
        BankConnector bankConnector = new BankConnector();

        ATM injectedATM = new ATM(cardReader, pinEntry, dispenser, printer, bankConnector, new IdleState());
        demonstrateATMOperations(injectedATM, card, "Dependency Injected ATM");

        System.out.println("\n=== ATM Simulation Complete ===");
    }

    private static void demonstrateATMOperations(ATM atm, Card card, String atmType) {
        System.out.println("--- " + atmType + " Operations ---");
        try {
            // 1. Insert card
            System.out.println("1. Inserting card...");
            atm.insertCard(card);
            Thread.sleep(1000);

            // 2. Enter PIN
            System.out.println("\n2. Entering PIN...");
            atm.enterPin("1234");
            Thread.sleep(1000);

            // 3. Check balance
            System.out.println("\n3. Checking balance...");
            atm.selectTransaction("balance");
            Thread.sleep(2000);

            // Reset ATM for next transaction
            System.out.println("\n" + "=".repeat(50));
            atm = new ATM();
            atm.insertCard(card);
            atm.enterPin("1234");

            // 4. Withdraw money
            System.out.println("\n4. Withdrawing money...");
            atm.selectTransaction("withdraw");
            Thread.sleep(1000);
            atm.enterAmount(500.0);
            Thread.sleep(2000);

            // Reset ATM for next transaction
            System.out.println("\n" + "=".repeat(50));
            atm = new ATM();
            atm.insertCard(card);
            atm.enterPin("1234");

            // 5. Get mini statement
            System.out.println("\n5. Getting mini statement...");
            atm.selectTransaction("ministatement");
            Thread.sleep(2000);

            // 6. Demonstrate transaction classes
            System.out.println("\n" + "=".repeat(50));
            System.out.println("\n6. Demonstrating Transaction Classes:");
            demonstrateTransactions(atm);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Simulation interrupted");
        }
    }

    private static void demonstrateTransactions(ATM atm) {
        BankConnector bankConnector = atm.getBankConnector();

        // Create different types of transactions
        WithdrawTransaction withdrawTx = new WithdrawTransaction("TXN001", "1234567890123456", 100.0);
        BalanceEnquiryTransaction balanceTx = new BalanceEnquiryTransaction("TXN002", "1234567890123456");
        ChangePinTransaction changePinTx = new ChangePinTransaction("TXN003", "1234567890123456", "1234", "5678");
        MiniStatementTransaction miniStmtTx = new MiniStatementTransaction("TXN004", "1234567890123456");

        System.out.println("\nExecuting transactions:");

        System.out.println("\n- Withdraw Transaction:");
        withdrawTx.execute(bankConnector);

        System.out.println("\n- Balance Enquiry Transaction:");
        balanceTx.execute(bankConnector);

        System.out.println("\n- Change PIN Transaction:");
        changePinTx.execute(bankConnector);

        System.out.println("\n- Mini Statement Transaction:");
        miniStmtTx.execute(bankConnector);
    }
}
