package com.codewithanshuman;

import edu.duke.FileResource;

public class AutoDecryption {

    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i=0; i<message.length();i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] +=1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] vals){
        int maxDex= 0;
        for (int i=0;i<vals.length;i++){
            if (vals[i] > vals[maxDex]){
                maxDex = i;
            }
        }
        return maxDex;
    }

    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if( maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }

    public void testAutoDecryption(){
        int key = 15;
        FileResource fr = new FileResource("dataFile/message2.txt");
        String mess = fr.asString();
        CaesarCipher ccN = new CaesarCipher();
        String encrypted = ccN.encrypt(mess, key);
        System.out.println(encrypted);
        String tmp = decrypt(encrypted);
        System.out.println(tmp);
    }

    public String halfOfString(String message, int start){
        StringBuilder replace = new StringBuilder(message);
        for(int i=0;i<message.length();i++){
            char currChar = message.charAt(i);
            if ( start == 0) {
                if (i % 2 == 0) {
                    replace.setCharAt(i, currChar);
                } else {
                    currChar = '\0';
                    replace.setCharAt(i, currChar);
                }
            }
            else {
                if(i%2 != 0){
                    replace.setCharAt(i, currChar);
                } else {
                    currChar = '\0';
                    replace.setCharAt(i, currChar);
                }
            }
        }
        return replace.toString();
    }

    /*
       for (int k = start; k< message.length() ; k+= 2) {
            answer = answer + message.charAt(k);
       }
     */

    public void testHalfOfString(){
        String hos = halfOfString("Qbkm Zgis", 0);
        System.out.println("For start 0: "+hos);
        hos = halfOfString("Qbkm Zgis", 1);
        System.out.println("For start 1: "+hos);
    }

    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        int key = maxIndex - 4;
        if( maxIndex < 4){
            key = 26 - (4 - maxIndex);
        }
        return 26-key;
    }

    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String firstHalf = halfOfString(encrypted,0);
        int key1 = getKey(firstHalf);
        String secondHalf = halfOfString(encrypted,1);
        int key2 = getKey(secondHalf);
        System.out.println("key1: "+key1+" key2: "+key2);
        return cc.encryptTwoKeys(encrypted,key1,key2);
    }

    public void testDecryptTwoKeys(){
        CaesarCipher ccdtk = new CaesarCipher();
        FileResource fr = new FileResource("dataFile/message2.txt");
        //FileResource fr = new FileResource("dataFile/mysteryTwoKeysPractice.txt");
        String str = fr.asString();
        //String str ="At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        //String str = "First Legion";
        System.out.println("Actual string: \n" +str);
        String encrypted = ccdtk.encryptTwoKeys(str,8,21);
        System.out.println("Encrypted String: \n"+encrypted);
        String testing = ccdtk.encryptTwoKeys(encrypted,(26-8),26-21);
        System.out.println("Testing by old CC: \n"+testing);
        //String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        //String encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        String tmp = decryptTwoKeys(encrypted);
        //String tmp = decryptTwoKeys(str);
        System.out.println("Decrypted: \n" +tmp);
    }

}
