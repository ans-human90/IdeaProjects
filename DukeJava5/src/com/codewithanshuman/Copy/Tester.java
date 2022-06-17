package com.codewithanshuman.Copy;

import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows(){
        MarkovOne mo = new MarkovOne();
        mo.setTraining("this is a test yes this is a test.");
        System.out.println(mo.getFollows("t."));
    }
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource("data/melville.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> tmp = new ArrayList<>( markov.getFollows("th") );
        System.out.println(tmp.size());
    }
}
