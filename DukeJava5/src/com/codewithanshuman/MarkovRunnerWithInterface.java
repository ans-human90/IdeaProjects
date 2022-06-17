package com.codewithanshuman;

import edu.duke.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 25;

        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }

    public void testHashMap(){
        FileResource fr = new FileResource("data/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 500;
        int seed = 615;
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        runModel(emm,st,size,seed);
    }

    public void compareMethods(){
        FileResource fr = new FileResource("data/hawthorne.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;

        double start1 = System.nanoTime();
        MarkovModel mm = new MarkovModel(2);
        runModel(mm, st, size, seed);
        double end1 = System.nanoTime();
        double diff1 = end1 - start1;

        double start2 = System.nanoTime();
        EfficientMarkovModel em = new EfficientMarkovModel(2);
        runModel(em,st,size,seed);
        double end2 = System.nanoTime();
        double diff2 = end2 - start2;

        System.out.println("For 1: "+ diff1 + " secs");
        System.out.println("For 2: "+ diff2 + " secs");
        System.out.println("Diff between these: " + ((diff1-diff2)/1e9/3) + " secs");

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

}
