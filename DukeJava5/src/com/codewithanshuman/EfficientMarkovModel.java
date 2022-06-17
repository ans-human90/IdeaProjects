package com.codewithanshuman;


import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private HashMap<String, ArrayList<String>> myMap;
    private int N;

    public EfficientMarkovModel(int n) {
        N=n;
        myMap = new HashMap<String, ArrayList<String>>();
    }

    @Override
    public void setTraining(String s){
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-N);
        String current = myText.substring(index, index+N);
        sb.append(current);
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(current);
            if(follows == null){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            current = current.substring(1) + next;
        }
        return sb.toString();
    }

    private void buildMap() {
        for (int i = 0; i < myText.length() - (N-1); i++) {

            String current = myText.substring(i, i+N);
			//System.out.println(current);
            String follow ="";
            if(i+N<myText.length())
                follow = myText.substring(i+N, i+N+1);

            if (myMap.containsKey(current)) {
                myMap.get(current).add(follow);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                myMap.put(current, list);
            }
        }

    }

    @Override
    public ArrayList<String> getFollows(String key) {
        return myMap.get(key);
    }

    public void printHashMapInfo() {
        System.out.println("It has " +  myMap.size() + " keys in the HashMap");
        int maxSize = 0;
        for (String key : myMap.keySet()) {
            maxSize = Math.max(maxSize, myMap.get(key).size());
			//System.out.printf("Key:\t[%s]\tvalues: ", key);
			//System.out.println(myMap.get(key));
        }
        System.out.println("The maximum number of keys following a key is " + maxSize);
        ArrayList<String> keys = new ArrayList<String>();
        for (String key : myMap.keySet()) {
            if(myMap.get(key).size() == maxSize){
                keys.add(key);
            }
        }
        System.out.println("Keys that have the largest ArrayList are: " + keys);
    }

    @Override
    public String toString(){
        return String.format("Efficient MarkovModel of order %d",N);
    }

}
/*
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int N;
    private HashMap<String, ArrayList<String>> myMaps;

    public EfficientMarkovModel(int n) {
        N = n;
        myMaps = new HashMap<String, ArrayList<String>>();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - N);
        String key = myText.substring(index, index + N);
        sb.append(key);

        for (int k = 0; k < numChars - N; k++) {
            ArrayList<String> follows = buildMap(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        printHashMapInfo();

        return sb.toString();
    }

    public ArrayList<String> buildMap(String key){
        if (! myMaps.containsKey(key)){
            myMaps.put(key,getFollows(key));
        }
        return myMaps.get(key);
    }

    public void printHashMapInfo(){
            String dex = "";
            int max = 0;
            for (String s: myMaps.keySet()) {
                //System.out.println("key: "+s+"  & values: "+myMaps.get(s));
                ArrayList<String> tmp = new ArrayList<String>(myMaps.get(s));
                if (tmp.size()>max){
                    max=tmp.size();
                    dex = s;
                }
            }

            System.out.println("Number of keys: "+ myMaps.size());
            System.out.println("size of the largest value in the HashMap: "+ max);
            for (String s: myMaps.keySet()){
                ArrayList<String> tmp = new ArrayList<String>(myMaps.get(s));
                if(tmp.size() == max){
                    System.out.println(s);
                }
            }
    }


    public String toString() {
        return "EfficientMarkovModel of order " + N + ".";
    }
}
 */