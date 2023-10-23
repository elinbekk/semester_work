package com.example.semester_work1.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashService {
    public String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger bi = new BigInteger(1, messageDigest);
        String hashPassword = bi.toString(16);
        while(hashPassword.length()<32){
            hashPassword = "0" + hashPassword;
        }
        return hashPassword;
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        System.out.println(hash("qwerty"));
//        System.out.println(hash("qwer"));
//    }
}
