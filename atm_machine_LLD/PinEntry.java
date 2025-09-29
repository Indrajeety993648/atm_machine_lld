package atm_machine_LLD;

public class PinEntry {
    private Keypad keypad;

    public PinEntry(Keypad keypad) {
        this.keypad = keypad;
    }

    // Default constructor with physical keypad
    public PinEntry() {
        this.keypad = new PhysicalKeypad();
    }

    public String enterPin() {
        System.out.println("Please enter your PIN:");
        keypad.setSecureMode(true);
        String pin = keypad.getInput();
        keypad.clearInput();
        return pin;
    }

    public boolean validatePin(String enteredPin, String actualPin) {
        return enteredPin != null && enteredPin.equals(actualPin);
    }

    public String getNewPin() {
        System.out.println("Enter new PIN:");
        keypad.setSecureMode(true);
        String newPin = keypad.getInput();
        keypad.clearInput();
        return newPin;
    }

    public String getAmount() {
        System.out.println("Enter amount:");
        keypad.setSecureMode(false);
        String amount = keypad.getInput();
        keypad.clearInput();
        return amount;
    }

    public void setKeypad(Keypad keypad) {
        this.keypad = keypad;
    }

    public Keypad getKeypad() {
        return keypad;
    }

    public boolean isKeypadAvailable() {
        return keypad.isAvailable();
    }
}