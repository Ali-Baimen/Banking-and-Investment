package Facade;
import Account.Account;
import Account.SavingAccount;
import Account.InvestmentAccount;
import Decorators.AccountDecorator;
import Decorators.InsuranceDecorator;
import Decorators.RewardPointsDecorator;
import Decorators.TaxOptimizerDecorator;

public class BankingFacade {
    public Account openAccountWithBenefits(String accountType) {
        System.out.println("\n-Opening Account with Benefits-");
        Account account;

        if ("savings".equalsIgnoreCase(accountType)) {
            account = new SavingAccount();
        } else if ("investment".equalsIgnoreCase(accountType)) {
            account = new InvestmentAccount();
        } else {
            throw new IllegalArgumentException("Unknown account type");
        }



        account = new RewardPointsDecorator(account);
        account = new InsuranceDecorator(account);

        System.out.println("Account created with Rewards & Insurance");
        return account;
    }

    public Account investWithSafetyMode(String investmentType) {
        System.out.println("\n-Opening Investment Account (Safety Mode)-");
        Account account;

        if ("portfolio".equalsIgnoreCase(investmentType)) {
            account = new InvestmentAccount();
        } else {
            throw new IllegalArgumentException("Unknown investment type");
        }


        account = new TaxOptimizerDecorator(account);
        account = new InsuranceDecorator(account);

        System.out.println("Investment account created with Tax Optimization & Insurance");
        return account;
    }

    public void closeAccount(Account account) {
        System.out.println("\n-Closing Account-");
        System.out.println("Final Balance: $" + account.getBalance());
        System.out.println("Account closed successfully!");
        System.out.println("Thank you for banking with us!");
    }

    public void performTransaction(Account account, double amount, String type) {
        System.out.println("\n-Performing " + type.toUpperCase() + "-");
        if ("deposit".equalsIgnoreCase(type)) {
            account.deposit(amount);
        } else if ("withdraw".equalsIgnoreCase(type)) {
            account.withdraw(amount);
        }
    }
}

