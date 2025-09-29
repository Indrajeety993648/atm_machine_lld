package atm_machine_LLD;

public class KeypadDemo {

    public static void main(String[] args) {
        System.out.println("=== ATM Keypad Interface Demonstration ===\n");

        // Test Physical Keypad
        System.out.println("1. Testing Physical Keypad:");
        System.out.println("=".repeat(40));
        testKeypad(new PhysicalKeypad());

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Test Digital Keypad
        System.out.println("2. Testing Digital Keypad:");
        System.out.println("=".repeat(40));
        testKeypad(new DigitalKeypad());

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Test ATM with different keypads
        System.out.println("3. Testing ATM with Different Keypads:");
        System.out.println("=".repeat(45));

        // ATM with Physical Keypad
        System.out.println("\nATM with Physical Keypad:");
        ATM atmPhysical = new ATM(new PhysicalKeypad());
        Card card = new Card("1234567890123456", "John Doe", "12/25", "ABC Bank");

        atmPhysical.insertCard(card);
        atmPhysical.enterPin("1234");

        // ATM with Digital Keypad
        System.out.println("\nATM with Digital Keypad:");
        ATM atmDigital = new ATM(new DigitalKeypad());

        atmDigital.insertCard(card);
        atmDigital.enterPin("1234");

        System.out.println("\n=== Keypad Interface Benefits ===");
        System.out.println("✅ Hardware abstraction");
        System.out.println("✅ Easy switching between physical and digital keypads");
        System.out.println("✅ Secure mode for PIN entry");
        System.out.println("✅ Standard mode for amount entry");
        System.out.println("✅ Self-testing and availability checking");
        System.out.println("✅ Follows Strategy Pattern for different input methods");
    }

    private static void testKeypad(Keypad keypad) {
        String keypadType = keypad.getClass().getSimpleName();

        // Test availability
        System.out.println("Keypad Available: " + keypad.isAvailable());

        // Test secure mode for PIN
        System.out.println("\nTesting PIN Entry (Secure Mode):");
        keypad.setSecureMode(true);
        System.out.println("Secure Mode: " + keypad.isSecureMode());
        String pin = keypad.getInput();
        System.out.println("PIN entered: " + "*".repeat(pin.length()) + " (masked)");
        keypad.clearInput();

        // Test standard mode for amount
        System.out.println("\nTesting Amount Entry (Standard Mode):");
        keypad.setSecureMode(false);
        System.out.println("Secure Mode: " + keypad.isSecureMode());
        String amount = keypad.getInput();
        System.out.println("Amount entered: $" + amount);
        keypad.clearInput();

        // Test specific features
        if (keypad instanceof PhysicalKeypad) {
            PhysicalKeypad physicalKeypad = (PhysicalKeypad) keypad;
            physicalKeypad.enableBacklight();
            physicalKeypad.performSelfTest();
        } else if (keypad instanceof DigitalKeypad) {
            DigitalKeypad digitalKeypad = (DigitalKeypad) keypad;
            digitalKeypad.setBrightness(75);
            digitalKeypad.performTouchCalibration();
        }
    }
}