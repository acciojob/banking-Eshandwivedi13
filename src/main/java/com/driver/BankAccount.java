package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(9*digits<sum || sum<0){
            throw new AcNumberException("Account Number can not be generated");
        }
        StringBuilder accNo = new StringBuilder();
        // Start with the most significant digit
        for (int i = 1; i <= digits; i++){
            // Generate a random digit between 0 and 9 (both inclusive)
            int digit = (int) (Math.random() * 10);//gives random decimal value and converting in integer
            // Append the digit to the account number
            accNo.append(digit);
        }
        return accNo.toString();
    }

    public double getMinBalance() {
        return minBalance;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
       if (balance - amount < minBalance) {
            throw new InSufficientBalanceException("Insufficient balance");
        } else {
            balance -= amount;
        }
    }

}