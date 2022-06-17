package com.codewithanshuman;

public class StringsFirstAssignments2 {
    public String findGeneSimple(String dna, String startCodon, String stopCodon){
        String result = "";
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (startIndex == -1){
            return "";
        }
        result = dna.substring(startIndex,stopIndex+3);
        return result;
    }
    public void testFindGeneSimple(){
        String startCodon = "ATG";
        String stopCodon = "TAA";
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA strand is " + dna);
        String gene = findGeneSimple(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        dna = "ATCCTATGCTTCGGGCTCTAATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findGeneSimple(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        dna = "ATGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findGeneSimple(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        dna = "gatgctataat";
        System.out.println("DNA strand is " + dna);
        gene = findGeneSimple(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        dna = "atgctataa";
        System.out.println("DNA strand is " + dna);
        gene = findGeneSimple(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
    }
}
