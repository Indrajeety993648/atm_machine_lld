package atm_machine_LLD;

public interface ATMState {
    void onCardInserted(ATM atm);

    void onPinEntered(ATM atm, String pin);

    void onSelectTransaction(ATM atm, String txType);

    void onAmountEntered(ATM atm, double amount);

    void onCancel(ATM atm);
}