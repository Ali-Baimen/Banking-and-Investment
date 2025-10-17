package Decorators;
import Account.Account;
public class TaxOptimizerDecorator extends AccountDecorator {
    private double taxSavings = 0;

    public TaxOptimizerDecorator(Account account) {
        super(account);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        taxSavings = amount * 0.15; // Simulate 15% tax optimization
        System.out.println("Tax optimization applied (Est. savings: $" +
                String.format("%.2f", taxSavings) + ")");
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | [Tax Optimized]";
    }
}

