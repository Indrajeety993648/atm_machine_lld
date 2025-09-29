package atm_machine_LLD;

/**
 * Builder pattern implementation for creating ATM instances with flexible
 * configuration.
 * This eliminates hard-coded dependencies and provides a clean API for ATM
 * construction.
 */
public class ATMBuilder {
    private CardReader cardReader;
    private PinEntry pinEntry;
    private CashDispenser dispenser;
    private Printer printer;
    private BankConnector bankConnector;
    private ATMState initialState;
    private Keypad keypad;
    private DispenseStrategy dispenseStrategy;

    public ATMBuilder() {
        // Set sensible defaults
        this.keypad = new PhysicalKeypad();
        this.dispenseStrategy = new MinimumNotesStrategy();
        this.initialState = new IdleState();
    }

    public ATMBuilder withCardReader(CardReader cardReader) {
        this.cardReader = cardReader;
        return this;
    }

    public ATMBuilder withPinEntry(PinEntry pinEntry) {
        this.pinEntry = pinEntry;
        return this;
    }

    public ATMBuilder withKeypad(Keypad keypad) {
        this.keypad = keypad;
        return this;
    }

    public ATMBuilder withCashDispenser(CashDispenser dispenser) {
        this.dispenser = dispenser;
        return this;
    }

    public ATMBuilder withDispenseStrategy(DispenseStrategy strategy) {
        this.dispenseStrategy = strategy;
        return this;
    }

    public ATMBuilder withPrinter(Printer printer) {
        this.printer = printer;
        return this;
    }

    public ATMBuilder withBankConnector(BankConnector bankConnector) {
        this.bankConnector = bankConnector;
        return this;
    }

    public ATMBuilder withInitialState(ATMState initialState) {
        this.initialState = initialState;
        return this;
    }

    public ATM build() {
        // Create instances only if not provided
        if (cardReader == null) {
            cardReader = new CardReader();
        }

        if (pinEntry == null) {
            pinEntry = new PinEntry(keypad);
        }

        if (dispenser == null) {
            dispenser = new CashDispenser(dispenseStrategy);
        }

        if (printer == null) {
            printer = new Printer();
        }

        if (bankConnector == null) {
            bankConnector = new BankConnector();
        }

        return new ATM(cardReader, pinEntry, dispenser, printer, bankConnector, initialState);
    }

    // Static factory method for default ATM
    public static ATM createDefaultATM() {
        return new ATMBuilder().build();
    }

    // Static factory method for ATM with specific keypad
    public static ATM createATMWithKeypad(Keypad keypad) {
        return new ATMBuilder()
                .withKeypad(keypad)
                .build();
    }

    // Static factory method for ATM with specific strategy
    public static ATM createATMWithStrategy(DispenseStrategy strategy) {
        return new ATMBuilder()
                .withDispenseStrategy(strategy)
                .build();
    }
}