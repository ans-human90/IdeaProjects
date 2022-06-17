package com.codewithanshuman;

public class OOCaesarCipher {
    private String Alphabet;
    private String ShiftedAlphabet;
    public OOCaesarCipher(int key){
        Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ShiftedAlphabet = Alphabet.substring(key)+
                Alphabet.substring(0,key);;
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = Alphabet.indexOf(currChar);
            if( idx != -1 ) {
                char newChar = ShiftedAlphabet.charAt(idx);
                if (Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, newChar);
                } else if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input){
        return "";
    }
}
