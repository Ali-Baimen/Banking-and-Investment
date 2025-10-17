package Decorators;
import Account.Account;
public class InsuranceDecorator extends AccountDecorator {
    private double insurancePremium = 0.3;

    public InsuranceDecorator(Account account) {
        super(account);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        System.out.println("Insurance protection applied (+$" + insurancePremium + " premium)");
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        System.out.println("Withdrawal protected by insurance (+$" + insurancePremium + " premium)");
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | [Insurance Coverage]";
    }
}
