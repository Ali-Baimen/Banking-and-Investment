package Decorators;
import Account.Account;
public class RewardPointsDecorator extends AccountDecorator {
    private double rewardPoints = 0;

    public RewardPointsDecorator(Account account) {
        super(account);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        rewardPoints += (amount / 10); // 1 point per $10
        System.out.println("Reward points earned (+" + (int)(amount/10) + " points | Total: " +
                (int)rewardPoints + ")");
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | [Reward Points: " + (int)rewardPoints + "]";
    }
}

