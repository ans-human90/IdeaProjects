package com.codewithanshuman;

import edu.duke.FileResource;

public class WordLengths {

    public void countWordLengths(FileResource resource, int[] counts){
        int largest_so_far = 0;
        String[] words_of_length = new String[50];
        for (String word: resource.words()) {
            int index = word.length();
            if (word.startsWith("\"") && word.endsWith("\"")) {
                word = word.substring(1,index-1);
                index = index - 2;
            }
            if (word.endsWith(",") || word.endsWith(".")){
                index--;
                word = word.substring(0,index);
            }
            counts[index] += 1;
            if (index>largest_so_far){
                largest_so_far = index;
            }
            if(words_of_length[index] == null){
                words_of_length[index] = word;
            }
            else {
                words_of_length[index] += " " + word;
            }
        }
        for (int i=1; i<counts.length;i++){
            if(words_of_length[i] != null) {
                System.out.println(counts[i] + " words of length " + i + " : " + words_of_length[i]);
            }
        }
    }

    public int indexOfMax(int[] values){
        int largestValue = 0;
        for(int k=0; k<values.length; k++){
            if(largestValue<values[k]){
                largestValue = values[k];
            }
        }
        return largestValue;
    }

    public void testCountWordLengths(){
        FileResource resource = new FileResource("dataFile/smallHamlet.txt");
        //FileResource resource = new FileResource("dataFile/romeo.txt");
        //FileResource resource = new FileResource("dataFile/lotsOfWords.txt");
        int[] counts = new int[31];
        countWordLengths(resource,counts);
        int lv = indexOfMax(counts);
        System.out.println("Largest index position : "+ lv);
    }

}
