package Decorators;
import Account.Account;
abstract public class AccountDecorator implements Account {
    protected Account decoratedAccount;

    public AccountDecorator(Account account) {
        this.decoratedAccount = account;
    }

    @Override
    public void deposit(double amount) {
        decoratedAccount.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        decoratedAccount.withdraw(amount);
    }

    @Override
    public double getBalance() {
        return decoratedAccount.getBalance();
    }

    @Override
    public String getDetails() {
        return decoratedAccount.getDetails();
    }
}
