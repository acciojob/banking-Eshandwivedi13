package com.driver;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        int n = tradeLicenseId.length();
        for(int i=0; i<n-1; i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                throw new userDefinedException("Valid License can not be generated");
            }
        }
        /*for(int i=0; i<n-1; i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                ArrayList<Character> tempStr = new ArrayList<>();
                for(char c : tradeLicenseId.toCharArray()){
                    tempStr.add(c);
                }
                Collections.shuffle(tempStr);
                tradeLicenseId = new String();
                for(char c : tempStr){
                    tradeLicenseId += c;
                }
            }
        }*/

    }
}
