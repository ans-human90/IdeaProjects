package com.codewithanshuman;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    public MarkovWord(int howMany) {
        myRandom = new Random();
        myOrder = howMany;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    private int indexOf(String[] words, WordGram target, int start){
        for (int i=start; i<words.length - myOrder;i++){
            WordGram wg = new WordGram(words,i,myOrder);
            if(wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length){
            int start = indexOf(myText,kGram,pos);
            if (start == -1){
                break;
            }
            if (start + 1 >= myText.length){
                break;
            }
            String next = myText[start+myOrder];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }

    public void testIndexOf(){
        //String st = "this is just a test yes this is a simple test";
        //setTraining(st);
        //System.out.println(indexOf(myText,"simple",2));
    }

    public String toString(){
        return "MarkovWordModel of order "+myOrder+".";
    }

}
