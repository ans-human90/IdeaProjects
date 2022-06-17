package com.codewithanshuman;

public class WordPlay {

    public boolean isVowel(Character ch){
        char tmp = Character.toLowerCase(ch);
        if(tmp == 'a'|| tmp == 'e'|| tmp == 'i' || tmp =='o' || tmp == 'u'){
            return true;
        }
        return false;
    }

    public String replaceVowels(String pharse, char ch){
        StringBuilder replace = new StringBuilder(pharse);
        for(int i=0; i<pharse.length();i++){
            char currChar = pharse.charAt(i);
            if(isVowel(currChar)){
                replace.setCharAt(i,ch);
            }
        }
        return replace.toString();
    }

    public String emphasize(String pharse, char ch){
        StringBuilder replace = new StringBuilder(pharse);
        for(int i=0; i<pharse.length();i++){
            char currChar = pharse.charAt(i);
            if(currChar == ch){
                if(i%2==0) {
                    replace.setCharAt(i, '*');
                }
                else {
                    replace.setCharAt(i,'+');
                }
            }
        }
        return replace.toString();
    }

    public void testing(){
        String tmp ="";
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
        tmp = replaceVowels("Hello World",'*');
        System.out.println(tmp);
        tmp = emphasize("dna ctgaaactga", 'a');
        System.out.println(tmp);
        tmp = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(tmp);
    }

}
