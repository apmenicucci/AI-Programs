import java.util.*;

class BankAccount {
    private String accountNumber;
    private String owner;
    private double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    } 
    
    public String getOwner(){
        return owner;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        if (balance >= amount){
            balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    @Override
    public String toString(){
        return "Account Number: " + accountNumber + ", Owner: " + owner + ", Balance: $" + balance;
    }
}

class Transaction {
    private String transactionType;
    private double amount;
    private Date date;

    public Transaction(String transactionType, double amount){
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString(){
        return "Type: " + transactionType + ", Amount: $" + amount + ", Date: " + date;
    }
}

public class BankingSystem {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //create new account
        BankAccount account = new BankAccount("1234567890", "Example Person", 1000.0);
        List<Transaction> transactionHistory = new ArrayList<>();

        while (true) {
            System.out.println("Welcome to the Banking System");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    transactionHistory.add(new Transaction("Deposit", depositAmount));
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    transactionHistory.add(new Transaction("Withdrawal", withdrawalAmount));
                    break;
                case 4:
                    System.out.println("Transaction History:");
                    for (Transaction transaction : transactionHistory) {
                        System.out.println(transaction);
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the Banking System.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}