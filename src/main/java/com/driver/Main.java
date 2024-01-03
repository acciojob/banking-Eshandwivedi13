package com.driver;

public class Main {
    public static void main(String[] args) {
        try {
            CurrentAccount curAc = new CurrentAccount("eshan", 10000.0, "112233");
            curAc.validateLicenseId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}