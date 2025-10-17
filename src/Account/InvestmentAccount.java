package Account;

public class InvestmentAccount implements Account {
    protected double balance = 0;
    protected String accountType = "Investment Account";

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Invested $" + amount + " in " + accountType);
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

