package atm_machine_LLD;

public class DispenseStrategyDemo {

    public static void main(String[] args) {
        System.out.println("=== ATM Dispense Strategy Demonstration ===\n");

        int[] testAmounts = { 130, 275, 580, 95 };

        DispenseStrategy[] strategies = {
                new MinimumNotesStrategy(),
        };

        String[] strategyNames = {
                "Minimum Notes Strategy",
        };

        for (int amount : testAmounts) {
            System.out.println("Dispensing Amount: $" + amount);
            System.out.println("=".repeat(50));

            for (int i = 0; i < strategies.length; i++) {
                System.out.println("\n" + (i + 1) + ". " + strategyNames[i] + ":");
                strategies[i].dispense(amount);

                if (strategies[i] instanceof MinimumNotesStrategy) {
                    MinimumNotesStrategy minStrategy = (MinimumNotesStrategy) strategies[i];
                    System.out.println("   Total Notes: " + minStrategy.getTotalNotesCount(amount));
                }
                System.out.println();
            }

            System.out.println("=".repeat(70) + "\n");
        }

        // Demonstrate runtime strategy switching
        System.out.println("=== Runtime Strategy Switching Demo ===\n");

        ATM atm = new ATM();
        Card card = new Card("1234567890123456", "John Doe", "12/25", "ABC Bank");

        System.out.println("ATM initialized with MinimumNotesStrategy (default)");

        // Simulate a withdrawal with minimum notes strategy
        System.out.println("\n1. Withdrawal with Minimum Notes Strategy:");
        CashDispenser dispenser = atm.getDispenser();
        dispenser.dispenseCash(150);

        System.out.println("\n=== Strategy Pattern Benefits ===");
        System.out.println("✅ Runtime strategy switching");
        System.out.println("✅ Easy to add new dispensing algorithms");
        System.out.println("✅ Follows Open/Closed Principle");
        System.out.println("✅ Separates algorithm from context");
    }
}