package com.codewithanshuman;

import edu.duke.*;

public class MarkovRunner {

    public void runModel(IMarkovModel markov, String text, int size){
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed){
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //MarkovWordOne markovWord = new MarkovWordOne();
        //runModel(markovWord, st, 120,139);
        //MarkovWordTwo markovWord = new MarkovWordTwo();
        //runModel(markovWord, st, 120,832);
        MarkovWord mw = new MarkovWord(5);
        runModel(mw,st,120,844);

    }

    public void runMarkovZero() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setRandom(1024);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovOne() {
        FileResource fr = new FileResource("data/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setRandom(365);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovFour(){
        FileResource fr = new FileResource("data/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovFour markov = new MarkovFour();
        markov.setRandom(715);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovModel(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel(7);
        markov.setRandom(953);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
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

    public void testHashMap(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is a test yes this is really a test yes a test this is wow";
        int size = 100;
        int seed = 531;
        EfficientMarkovWord mod = new EfficientMarkovWord(5);
        runModel(mod, st, size, seed);
        System.out.println("printing");
        mod.printHashMapInfo();
    }

}
