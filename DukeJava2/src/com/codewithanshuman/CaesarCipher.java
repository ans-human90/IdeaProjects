package com.codewithanshuman;

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String uAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lAlphabet = uAlphabet.toLowerCase();
        String uShiftedAlphabet = uAlphabet.substring(key)+
                uAlphabet.substring(0,key);
        String lShiftedAlphabet = uShiftedAlphabet.toLowerCase();
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar)) {
                int idx = uAlphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = uShiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else if( Character.isLowerCase(currChar) ){
                int idx = lAlphabet.indexOf(currChar);
                if (idx != -1){
                    char newChar = lShiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public void testCaesar() {
        int key = 17;
        key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }

    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String uAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lAlphabet = uAlphabet.toLowerCase();
        String uShiftedAlphabet1 = uAlphabet.substring(key1)+
                uAlphabet.substring(0,key1);
        String lShiftedAlphabet1 = uShiftedAlphabet1.toLowerCase();
        String uShiftedAlphabet2 = uAlphabet.substring(key2)+
                uAlphabet.substring(0,key2);
        String lShiftedAlphabet2 = uShiftedAlphabet2.toLowerCase();

        for (int i=0 ; i<input.length() ; i++){
            char currChar = encrypted.charAt(i);
            if (i%2==0){
                if (Character.isUpperCase(currChar)) {
                    int idx = uAlphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = uShiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else if( Character.isLowerCase(currChar) ){
                    int idx = lAlphabet.indexOf(currChar);
                    if (idx != -1){
                        char newChar = lShiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
            else {
                if (Character.isUpperCase(currChar)) {
                    int idx = uAlphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = uShiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else if( Character.isLowerCase(currChar) ){
                    int idx = lAlphabet.indexOf(currChar);
                    if (idx != -1){
                        char newChar = lShiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }

    public  void testEncryptTwoKeys(){
        String tmp;
        //tmp = encryptTwoKeys("First Legion",23,17);
        //tmp = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15);
        //tmp = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21);
        tmp = encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?",21,8);
        System.out.println(tmp);
    }

}


