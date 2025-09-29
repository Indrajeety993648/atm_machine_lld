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

    public ATM() {
        this.cardReader = new CardReader();
        this.pinEntry = new PinEntry(new PhysicalKeypad()); // Default to physical keypad
        this.dispenser = new CashDispenser(new MinimumNotesStrategy()); // Using minimum notes by default
        this.printer = new Printer();
        this.bankConnector = new BankConnector();
        this.state = new IdleState();
    }

    // Constructor to specify keypad type
    public ATM(Keypad keypad) {
        this.cardReader = new CardReader();
        this.pinEntry = new PinEntry(keypad);
        this.dispenser = new CashDispenser(new MinimumNotesStrategy());
        this.printer = new Printer();
        this.bankConnector = new BankConnector();
        this.state = new IdleState();
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