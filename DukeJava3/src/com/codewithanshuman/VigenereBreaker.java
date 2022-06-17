package com.codewithanshuman;
import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int k=whichSlice; k<message.length(); k+=totalSlices){
            char ch = message.charAt(k);
            sb.append(ch);
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i =0; i<klength; i++) {
            String str = sliceString(encrypted, i , klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(str);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for (String s: fr.words()){
            String word = s. toLowerCase();
            if (! dictionary.contains(word)){
                dictionary.add(word);
            }
        }
        return dictionary;
    }

    public int countWords (String message, HashSet<String> dictionary){
        int realWords = 0;
        String[] words = message.split("\\W");
        for (String word : words){
            word = word.toLowerCase();
            if(dictionary.contains(word)){
                realWords++;
            }
        }
        return realWords;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int keylength = 0;
        int maxRealWord = 0;
        int[] finalKeys = new int[100];
        String finalDecrypted = "";
        for(int i=1; i<=100;i++){
            int[] keys = tryKeyLength(encrypted,i,mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(keys);
            String message = vc.decrypt(encrypted);
            int currRealWords = countWords(message,dictionary);
            if (currRealWords > maxRealWord){
                keylength = i;
                maxRealWord = currRealWords;
                finalDecrypted = message;
                finalKeys = keys;
            }
        }
        /*System.out.println("Key Length: "+keylength);
        System.out.println("Max Real Words: "+ maxRealWord);
        System.out.print("Keys: ");
        for(int i=0; i< finalKeys.length;i++) {
            System.out.print(finalKeys[i] + ", ");
        }
        System.out.println("\n --------------------------\n");*/
        return finalDecrypted;
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        char ch='\0';
        int maxChCount = 0;
        HashMap<Character,Integer> letterCounts = new HashMap<Character, Integer>();
        for(String word : dictionary){
           for(int k=0; k<word.length();k++){
               Character chatAt = word.charAt(k);
               if(! letterCounts.containsKey(chatAt)){
                   letterCounts.put(chatAt,1);
               }
               else {
                   int value = letterCounts.get(chatAt);
                   letterCounts.put(chatAt,value+1);
               }
           }
        }
        for(Character cc : letterCounts.keySet()){
            int count = letterCounts.get(cc);
            if(count>maxChCount){
                maxChCount = count;
                ch=cc;
            }
        }
        return ch;
    }

    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int countMaxWord = 0;
        String decrypted = "";
        String lang = "";
        for (String lg : languages.keySet()){
            HashSet<String> dict = languages.get(lg);
            String currDecrypted = breakForLanguage(encrypted,dict);
            int currMaxWord = countWords(currDecrypted,dict);
            if(currMaxWord>countMaxWord){
                countMaxWord = currMaxWord;
                decrypted = currDecrypted;
                lang = lg;
            }
        }
        System.out.println(lang+"\n");
        return decrypted;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource("data/secretmessage4.txt");
        String encryptedMessage = fr.asString();
        HashMap<String,HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String[] langsDict = new String[]{"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        for(int i = 0; i < langsDict.length; i++){
            String currLang = langsDict[i];
            FileResource dict = new FileResource("dictionaries/"+currLang);
            HashSet<String> dictionary = readDictionary(dict);
            languages.put(currLang, dictionary);
        }
        String decrypted = breakForAllLangs(encryptedMessage,languages);
        System.out.println(decrypted);
    }

}
