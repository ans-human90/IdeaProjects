package com.codewithanshuman;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> myChar;
    private ArrayList<Integer> myFreqs;
    public CharactersInPlay(){
        myChar = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void update(String person){
        int index = myChar.indexOf(person);
        if (index == -1){
            myChar.add(person);
            myFreqs.add(1);
        }
        else {
            int value = myFreqs.get(index);
            myFreqs.set(index,value+1);
        }
    }
    public void findAllCharacters(){
        FileResource resource = new FileResource("dataFile/errors.txt");
        for(String str: resource.lines()){
            int idx = str.indexOf(".");
            if (idx != -1) {
                str = str.substring(0, idx);
                update(str);
            }
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        for(int k=0;k<myChar.size();k++){
            if(myFreqs.get(k)>=num1 && myFreqs.get(k)<=num2){
                System.out.println(myChar.get(k) + " " + myFreqs.get(k));
            }
        }
    }

    public void tester(){
        findAllCharacters();
        for(int i=0; i<myChar.size();i++){
            if(myFreqs.get(i)>30) {
                System.out.println(myChar.get(i) + " " + myFreqs.get(i));
            }
        }
        System.out.println("\n *************** \n");
        charactersWithNumParts(10,15);
    }

}
