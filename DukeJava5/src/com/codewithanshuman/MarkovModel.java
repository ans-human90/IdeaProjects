package com.codewithanshuman;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel{

    private int N;

    public MarkovModel(int n) {
        N = n;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index,index+N);
        sb.append(key);

        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    public String toString(){
        return "MarkovModel of order "+N+".";
    }

}
