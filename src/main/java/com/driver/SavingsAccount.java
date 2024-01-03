package com.driver;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name, balance, 0);
        this.rate = rate;
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }
    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
        if(amount > maxWithdrawalLimit){
            throw new LimitExceed("Maximum Withdraw Limit Exceed");
        }
        else{
            withdraw(amount);
        }
    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        double SI = getBalance()*(rate/100)*years;
        return SI;
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        double P = getBalance();
        double r = rate/100;
        int n = times;
        int t = years;
        double CI = P * (Math.pow((1 + (r/n)), (n*t)));
        return CI;
    }

    public double getMaxWithdrawalLimit() {
        return maxWithdrawalLimit;
    }

    public double getRate() {
        return rate;
    }
}
