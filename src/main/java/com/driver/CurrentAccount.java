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

//Mine CODE ->>>

/*
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        int n = tradeLicenseId.length();
        boolean makeItValid = false;
        for(int i=0; i<n-1; i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                makeItValid = true;
                break;
            }
        }
        if(!makeItValid){//already a valid licenseId
            return;
        }

        PriorityQueue<info> isPossible =  new PriorityQueue<>((a,b) -> (b.freq - a.freq));//want to arrange in descending order
        StringBuilder sb = new StringBuilder();
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0; i<tradeLicenseId.length(); i++){
            char currentChar = tradeLicenseId.charAt(i);
            map.put(currentChar, map.getOrDefault(currentChar, 0) + 1);
        }
        for(char key : map.keySet()){
            if(map.get(key) > 0){
                isPossible.add(new info(key, map.get(key)));//index + 'a' = character, & map[i] = freq
            }
        }
        info block = isPossible.poll();
        if (block != null) {
            sb.append(block.ch);
            if(block.freq >= tradeLicenseId.length() + 1){
                throw new LicenseInvalid("Valid License can not be generated");
            }
            block.freq--;
        }
        while(isPossible.size() != 0){
            info temp = isPossible.poll();
            sb.append(temp.ch);
            temp.freq--;
            if (block != null && block.freq != 0) {//if character's freq is not 0
                isPossible.add(block);
            }
            block = temp;
        }
        if (block != null && block.freq > 0) {//it means this is a repeating character, reorganization not possible
            return;
        }
//        System.out.println(tradeLicenseId);
        this.tradeLicenseId = sb.toString();
//        System.out.println(tradeLicenseId);


//Method -2
//        for(int i=0; i<n-1; i++){
//            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
//                ArrayList<Character> tempStr = new ArrayList<>();
//                for(char c : tradeLicenseId.toCharArray()){
//                    tempStr.add(c);
//                }
//                Collections.shuffle(tempStr);
//                tradeLicenseId = new String();
//                for(char c : tempStr){
//                    tradeLicenseId += c;
//                }
//            }
//        }

    }
}
class info{
    char ch;
    int freq;
    info(char ch, int freq){
        this.ch = ch;
        this.freq = freq;
    }
}*/

