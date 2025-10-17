import Account.Account;
import Account.SavingAccount;
import Account.InvestmentAccount;
import Decorators.InsuranceDecorator;
import Decorators.RewardPointsDecorator;
import Decorators.TaxOptimizerDecorator;
import Decorators.AccountDecorator;
import Facade.BankingFacade;
public class Main {
    public static void main(String[] args) {
        BankingFacade bank = new BankingFacade();



        System.out.println("SAVINGS ACCOUNT WITH REWARDS + INSURANCE");
        Account savingsAccount = bank.openAccountWithBenefits("savings");
        bank.performTransaction(savingsAccount, 5000, "deposit");
        bank.performTransaction(savingsAccount, 1000, "withdraw");
        System.out.println("\nAccount Status: " + savingsAccount.getDetails());



        System.out.println("\n\nINVESTMENT ACCOUNT (SAFETY MODE)");
        Account investmentAccount = bank.investWithSafetyMode("portfolio");
        bank.performTransaction(investmentAccount, 10000, "deposit");
        bank.performTransaction(investmentAccount, 2000, "withdraw");
        System.out.println("\nAccount Status: " + investmentAccount.getDetails());



        System.out.println("\n\nPREMIUM ACCOUNT (ALL BENEFITS)");
        Account premiumAccount = new SavingAccount();
        premiumAccount = new RewardPointsDecorator(premiumAccount);
        premiumAccount = new InsuranceDecorator(premiumAccount);
        premiumAccount = new TaxOptimizerDecorator(premiumAccount);



        System.out.println("Premium Account Created!");
        bank.performTransaction(premiumAccount, 8000, "deposit");
        System.out.println("\nAccount Status: " + premiumAccount.getDetails());



        System.out.println("\n\nLOSING ACCOUNTS");
        bank.closeAccount(savingsAccount);
        bank.closeAccount(investmentAccount);
        bank.closeAccount(premiumAccount);
    }
}