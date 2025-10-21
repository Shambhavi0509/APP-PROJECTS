import java.util.Scanner;

// BankAccount class (OOP concept)
class BankAccount {
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        if (initialBalance >= 0)
            this.balance = initialBalance;
        else
            this.balance = 0;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Amount deposited successfully!");
        } else {
            System.out.println("❌ Invalid deposit amount!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Amount withdrawn successfully!");
        } else if (amount > balance) {
            System.out.println("⚠️ Insufficient balance!");
        } else {
            System.out.println("❌ Invalid withdrawal amount!");
        }
    }

    // Check balance
    public void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    // Display account info
    public void showAccountInfo() {
        System.out.println("\n👤 Account Holder: " + accountHolder);
        System.out.println("💵 Balance: ₹" + balance);
    }
}

// Main Class
public class BankAccountManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Account creation
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ₹");
        double initialBalance = sc.nextDouble();

        BankAccount account = new BankAccount(name, initialBalance);
        System.out.println("\n🏦 Bank Account Created Successfully!");

        int choice;
        do {
            // Menu
            System.out.println("\n=== Bank Menu ===");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Account Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double wit = sc.nextDouble();
                    account.withdraw(wit);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    account.showAccountInfo();
                    break;
                case 5:
                    System.out.println("👋 Thank you for using Bank Account Management!");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
