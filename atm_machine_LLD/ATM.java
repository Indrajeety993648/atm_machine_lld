package atm_machine_LLD;

public class ATM {
    private ATMState state;
    private CardReader cardReader;
    private PinEntry pinEntry;
    private CashDispenser dispenser;
    private Printer printer;
    private BankConnector bankConnector;

    private Card currentCard;
    private String currentTransactionType;
    private double currentAmount;

    // Primary constructor with dependency injection
    public ATM(CardReader cardReader, PinEntry pinEntry, CashDispenser dispenser,
            Printer printer, BankConnector bankConnector, ATMState initialState) {
        this.cardReader = cardReader;
        this.pinEntry = pinEntry;
        this.dispenser = dispenser;
        this.printer = printer;
        this.bankConnector = bankConnector;
        this.state = initialState;
    }

    // Constructor with default keypad - still uses dependency injection
    public ATM(CardReader cardReader, CashDispenser dispenser, Printer printer,
            BankConnector bankConnector, ATMState initialState, Keypad keypad) {
        this.cardReader = cardReader;
        this.pinEntry = new PinEntry(keypad);
        this.dispenser = dispenser;
        this.printer = printer;
        this.bankConnector = bankConnector;
        this.state = initialState;
    }

    // Default constructor for backward compatibility - creates default instances
    public ATM() {
        this(new CardReader(),
                new PinEntry(new PhysicalKeypad()),
                new CashDispenser(new MinimumNotesStrategy()),
                new Printer(),
                new BankConnector(),
                new IdleState());
    }

    // Constructor to specify keypad type - improved with dependency injection
    public ATM(Keypad keypad) {
        this(new CardReader(),
                new CashDispenser(new MinimumNotesStrategy()),
                new Printer(),
                new BankConnector(),
                new IdleState(),
                keypad);
    }

    public void setDispenseStrategy(DispenseStrategy strategy) {
        this.dispenser.setDispenseStrategy(strategy);
    }

    public void insertCard(Card card) {
        cardReader.insertCard(card);
        this.currentCard = card;
        state.onCardInserted(this);
    }

    public void ejectCard() {
        cardReader.ejectCard();
        this.currentCard = null;
        setState(new IdleState());
    }

    public void enterPin(String pin) {
        state.onPinEntered(this, pin);
    }

    public void selectTransaction(String transactionType) {
        this.currentTransactionType = transactionType;
        state.onSelectTransaction(this, transactionType);
    }

    public void enterAmount(double amount) {
        this.currentAmount = amount;
        state.onAmountEntered(this, amount);
    }

    public void cancel() {
        state.onCancel(this);
    }

    // Getters and setters
    public ATMState getState() {
        return state;
    }

    public void setState(ATMState state) {
        this.state = state;
    }

    public CardReader getCardReader() {
        return cardReader;
    }

    public PinEntry getPinEntry() {
        return pinEntry;
    }

    public CashDispenser getDispenser() {
        return dispenser;
    }

    public Printer getPrinter() {
        return printer;
    }

    public BankConnector getBankConnector() {
        return bankConnector;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public String getCurrentTransactionType() {
        return currentTransactionType;
    }

    public void setCurrentTransactionType(String currentTransactionType) {
        this.currentTransactionType = currentTransactionType;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }
}