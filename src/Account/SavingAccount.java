package Account;

public class SavingAccount implements Account {
    protected double balance = 0;
    protected String accountType = "Savings Account";

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited $" + amount + " to " + accountType);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn $" + amount + " from " + accountType);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDetails() {
        return accountType + " | Balance: $" + balance;
    }
}
