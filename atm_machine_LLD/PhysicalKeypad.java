package atm_machine_LLD;

public class PhysicalKeypad implements Keypad {
    private boolean secureMode;
    private boolean available;
    private StringBuilder inputBuffer;

    public PhysicalKeypad() {
        this.secureMode = false;
        this.available = true;
        this.inputBuffer = new StringBuilder();
    }

    @Override
    public String getInput() {
        if (!available) {
            throw new RuntimeException("Keypad is not available");
        }

        System.out.println("Physical Keypad: Please enter input using physical buttons...");

        // Simulate physical keypad input
        if (secureMode) {
            System.out.println("Secure mode: Input will be masked");
            // Mock PIN input for demonstration
            return "1234";
        } else {
            System.out.println("Standard mode: Input will be visible");
            // Mock amount input for demonstration
            return "100";
        }
    }

    @Override
    public void clearInput() {
        inputBuffer.setLength(0);
        System.out.println("Physical Keypad: Input buffer cleared");
    }

    @Override
    public boolean isSecureMode() {
        return secureMode;
    }

    @Override
    public void setSecureMode(boolean secure) {
        this.secureMode = secure;
        if (secure) {
            System.out.println("Physical Keypad: Secure mode enabled - PIN entry");
        } else {
            System.out.println("Physical Keypad: Standard mode enabled");
        }
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Additional methods specific to physical keypad
    public void enableBacklight() {
        System.out.println("Physical Keypad: Backlight enabled");
    }

    public void disableBacklight() {
        System.out.println("Physical Keypad: Backlight disabled");
    }

    public boolean performSelfTest() {
        System.out.println("Physical Keypad: Performing self-test...");
        // Simulate self-test
        boolean testResult = true;
        setAvailable(testResult);
        System.out.println("Physical Keypad: Self-test " + (testResult ? "PASSED" : "FAILED"));
        return testResult;
    }
}