# ATM Machine Low-Level Design (LLD)

## Overview

This project implements a comprehensive ATM (Automated Teller Machine) system using object-oriented design principles and design patterns. The system demonstrates proper implementation of SOLID principles, State Pattern, Strategy Pattern, and Template Method Pattern.

## Class Diagram

📊 **Class Diagram**: ![UML Diagram](https://drive.google.com/uc?id=1iNc7qNQwKLDN-qfb7iO4C1YmqYSCeAcX)

## Features

### Core Functionality

- ✅ Card insertion and validation
- ✅ PIN authentication
- ✅ Balance inquiry
- ✅ Cash withdrawal
- ✅ PIN change
- ✅ Mini statement generation
- ✅ Receipt printing
- ✅ Transaction history

### Hardware Abstraction

- 🔧 **Keypad Interface**: Supports both Physical and Digital keypads
- 💰 **Cash Dispenser**: Configurable dispensing strategies
- 🖨️ **Printer**: Receipt and statement printing
- 💳 **Card Reader**: Card insertion/ejection management

## Design Patterns Implemented

### 1. State Pattern

The ATM operates through different states, each handling specific user interactions:

- `IdleState`: Initial state, waiting for card insertion
- `CardInsertedState`: Card inserted, waiting for PIN
- `AuthenticatedState`: PIN validated, ready for transaction selection
- `DispensingState`: Processing cash withdrawal
- `PrintingState`: Printing receipts and finalizing transactions

### 2. Strategy Pattern

Flexible cash dispensing algorithms:

- `MinimumNotesStrategy`: Dispenses cash using minimum number of notes
- Easily extensible for new dispensing strategies

### 3. Template Method Pattern

Transaction processing with common workflow:

- Base `Transaction` class defines the execution template
- Specific transaction types implement their own logic

## Architecture

### Core Components

#### ATM Class

The main context class that orchestrates all operations:

```java
public class ATM {
    private ATMState state;
    private CardReader cardReader;
    private PinEntry pinEntry;
    private CashDispenser dispenser;
    private Printer printer;
    private BankConnector bankConnector;
}
```

#### State Management

- **ATMState Interface**: Defines all possible operations
- **BaseATMState**: Abstract base with common functionality
- **Concrete States**: Implement state-specific behaviors

#### Transaction Hierarchy

- **Transaction**: Abstract base class
- **WithdrawTransaction**: Handles money withdrawal
- **BalanceEnquiryTransaction**: Shows account balance
- **ChangePinTransaction**: Updates PIN
- **MiniStatementTransaction**: Generates transaction history

## SOLID Principles Implementation

### ✅ Single Responsibility Principle (SRP)

Each class has a single, well-defined responsibility:

- `Card`: Stores card information
- `CardReader`: Manages card operations
- `CashDispenser`: Handles cash dispensing
- `Printer`: Manages printing operations

### ✅ Open/Closed Principle (OCP)

- New transaction types can be added without modifying existing code
- New dispensing strategies can be implemented
- New states can be added to the state machine

### ✅ Liskov Substitution Principle (LSP)

- All strategy implementations are interchangeable
- State objects can be substituted without breaking functionality

### Dependency Inversion Principle (DIP)

- Interfaces are used for major components

## Project Structure

```
atm_machine_LLD/
├── ATM.java                          # Main ATM context class
├── ATMState.java                     # State interface
├── BaseATMState.java                 # Abstract state base
├── IdleState.java                    # Initial state
├── CardInsertedState.java            # Card inserted state
├── AuthenticatedState.java           # Authenticated state
├── DispensingState.java              # Cash dispensing state
├── PrintingState.java                # Receipt printing state
├── Transaction.java                  # Transaction base class
├── WithdrawTransaction.java          # Withdrawal implementation
├── BalanceEnquiryTransaction.java    # Balance inquiry implementation
├── ChangePinTransaction.java         # PIN change implementation
├── MiniStatementTransaction.java     # Mini statement implementation
├── DispenseStrategy.java             # Strategy interface
├── MinimumNotesStrategy.java         # Minimum notes strategy
├── CashDispenser.java                # Cash dispensing system
├── Keypad.java                       # Keypad interface
├── PhysicalKeypad.java               # Physical keypad implementation
├── DigitalKeypad.java                # Digital keypad implementation
├── Card.java                         # Card entity
├── CardReader.java                   # Card reading system
├── PinEntry.java                     # PIN entry system
├── Printer.java                      # Printing system
├── Receipt.java                      # Receipt entity
├── MiniStatement.java                # Mini statement entity
├── BankConnector.java                # Bank connectivity
├── StateTransitionManager.java       # State transition interface
├── DefaultStateTransitionManager.java # Default state transitions
├── StateValidator.java               # State validation interface
├── DefaultStateValidator.java        # Default state validation
├── Main.java                         # Demo application
├── KeypadDemo.java                   # Keypad demonstration
└── DispenseStrategyDemo.java         # Strategy demonstration
```

## How to Run

### Prerequisites

- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/Indrajeety993648/atm_machine_lld.git
   cd atm_machine_lld
   ```

2. Compile the Java files:

   ```bash
   javac atm_machine_LLD/*.java
   ```

3. Run the main application:
   ```bash
   java atm_machine_LLD.Main
   ```

### Demo Programs

Run different demonstration programs:

```bash
# Main ATM simulation
java atm_machine_LLD.Main

# Keypad interface demo
java atm_machine_LLD.KeypadDemo

# Dispense strategy demo
java atm_machine_LLD.DispenseStrategyDemo
```

## Usage Examples

### Basic ATM Operations

```java
// Create ATM instance
ATM atm = new ATM();

// Create a card
Card card = new Card("1234567890123456", "John Doe", "12/25", "ABC Bank");

// Simulate ATM operations
atm.insertCard(card);
atm.enterPin("1234");
atm.selectTransaction("balance");
```

### Changing Dispense Strategy

```java
ATM atm = new ATM();
atm.setDispenseStrategy(new MinimumNotesStrategy());
```

## Sample Output

```
=== ATM Machine Simulation ===
ATM is ready for use...

1. Inserting card...
Card inserted: John Doe

2. Entering PIN...
PIN validated successfully. Please select a transaction.

3. Checking balance...
Transaction selected: balance
Your current balance is: $5000.0
Printing receipt...
=== ATM RECEIPT ===
Transaction ID: TXN1727634567890
Type: balance
Amount: $0.0
Balance: $5000.0
Date/Time: 2025-09-29 10:30:45
Card: ****3456
==================
Please collect your receipt
Transaction completed. Ejecting card.
Ejecting card...
```

## Extensibility

### Adding New Transaction Types

1. Extend the `Transaction` class
2. Implement the `execute()` method
3. Add transaction type to `AuthenticatedState`

### Adding New Dispensing Strategies

1. Implement the `DispenseStrategy` interface
2. Add your algorithm logic
3. Set the strategy using `atm.setDispenseStrategy()`

### Adding New States

1. Implement the `ATMState` interface or extend `BaseATMState`
2. Add state transitions in the state machine
3. Update the `DefaultStateTransitionManager`

## Known Issues & Improvements

### Current Limitations

- ⚠️ Hard-coded dependencies in ATM constructor
- ⚠️ Inconsistent state implementation patterns
- ⚠️ Limited error handling and validation
- ⚠️ Magic strings used for transaction types

### Planned Improvements

- 🔄 Implement dependency injection
- 🔄 Add comprehensive error handling
- 🔄 Use enums for constants
- 🔄 Add configuration management
- 🔄 Implement proper logging
- 🔄 Add unit tests

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is created for educational purposes to demonstrate low-level design principles and design patterns implementation.

## Author

**Indrajeety993648**

- GitHub: [@Indrajeety993648](https://github.com/Indrajeety993648)

---

_This project demonstrates the implementation of a real-world ATM system using object-oriented design principles, design patterns, and best practices in software engineering._
