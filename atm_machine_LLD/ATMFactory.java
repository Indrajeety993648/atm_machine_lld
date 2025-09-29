package atm_machine_LLD;

/**
 * Factory pattern implementation for creating different types of ATM
 * configurations.
 * This provides pre-configured ATM instances for common use cases.
 */
public class ATMFactory {

    /**
     * Creates a standard ATM with physical keypad and minimum notes strategy
     */
    public static ATM createStandardATM() {
        return new ATMBuilder()
                .withKeypad(new PhysicalKeypad())
                .withDispenseStrategy(new MinimumNotesStrategy())
                .build();
    }

    /**
     * Creates a modern ATM with digital keypad and minimum notes strategy
     */
    public static ATM createModernATM() {
        return new ATMBuilder()
                .withKeypad(new DigitalKeypad())
                .withDispenseStrategy(new MinimumNotesStrategy())
                .build();
    }

    /**
     * Creates an ATM for testing with mock components
     */
    public static ATM createTestATM(BankConnector mockBankConnector) {
        return new ATMBuilder()
                .withBankConnector(mockBankConnector)
                .withKeypad(new PhysicalKeypad())
                .withDispenseStrategy(new MinimumNotesStrategy())
                .build();
    }

    /**
     * Creates a custom ATM with specified components
     */
    public static ATM createCustomATM(Keypad keypad, DispenseStrategy strategy,
            BankConnector bankConnector) {
        return new ATMBuilder()
                .withKeypad(keypad)
                .withDispenseStrategy(strategy)
                .withBankConnector(bankConnector)
                .build();
    }

    /**
     * Creates an ATM with dependency injection - all dependencies provided
     * externally
     */
    public static ATM createATMWithDependencies(CardReader cardReader,
            PinEntry pinEntry,
            CashDispenser dispenser,
            Printer printer,
            BankConnector bankConnector) {
        return new ATM(cardReader, pinEntry, dispenser, printer, bankConnector, new IdleState());
    }
}