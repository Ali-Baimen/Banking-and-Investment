package Account;

public interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    String getDetails();
}
