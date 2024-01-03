package com.driver;

import java.util.*;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        if(balance<5000)
        {
            throw new InSufficientBalanceException("Insufficient Balance");
        }
        this.tradeLicenseId=tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isValid(this.tradeLicenseId))
        {
            String ans=orgString(this.tradeLicenseId);
            if(ans.length()==0)
            {
                throw new LicenseInvalid("Valid License can not be generated");
            }
            else
            {
                this.tradeLicenseId=ans;
            }
        }
    }

    private boolean isValid(String s) {
        for(int i=1;i<s.length();i++)
        {
            if(s.charAt(i-1)==s.charAt(i))
            {
                return false;
            }
        }
        return true;
    }
    static class pair{
        char ch;
        int freq;
        pair(char ch,int freq)
        {
            this.ch=ch;
            this.freq=freq;
        }
    }
    private String orgString(String s) {
        HashMap<Character,Integer>map=new HashMap<>();
        PriorityQueue<pair>pq=new PriorityQueue<>((a,b)->{
            return a.freq>b.freq ? -1 : 1;
        });
        StringBuilder sb=new StringBuilder();
        for(char ch:s.toCharArray())
        {
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        for(char key:map.keySet())
        {
            pq.add(new pair(key, map.get(key)));
        }
        pair prev=pq.remove();
        sb.append(prev.ch);
        prev.freq--;
        while(pq.size()>0)
        {
            pair curr=pq.remove();
            sb.append(curr.ch);
            curr.freq--;
            if(prev.freq>0)
            {
                pq.add(prev);
            }
            prev=curr;
        }
        if(sb.length()!=s.length())
        {
            return "";
        }
        return sb.toString();
    }}
