package com.driver;
import java.util.*;
//Mine CODE ->>>
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
    static class info{
        char ch;
        int freq;
        info(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }
    }
}


