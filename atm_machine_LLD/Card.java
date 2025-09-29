package atm_machine_LLD;

public class Card {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String bankName;

    public Card(String cardNumber, String cardHolderName, String expiryDate, String bankName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getBankName() {
        return bankName;
    }
}