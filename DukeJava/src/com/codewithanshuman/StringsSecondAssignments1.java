package com.codewithanshuman;

public class StringsSecondAssignments1 {

    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        String result = "";
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1) {
            int diff = currIndex-startIndex;
            if ( diff %3 == 0) {
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon,currIndex+1);
            }
        }
        return dnaStr.length();
    }
    public String findGene(String dna){
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int temp = Math.min(taaIndex,tagIndex);
        int minIndex = Math.min(temp,tgaIndex);
        if (minIndex == dna.length()){
            return "";
        }
        return dna.substring( startIndex , minIndex +3 );
    }
    public void testFindGene(){
        String dna = "AATGCAGTAATATGGT";
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna);
        System.out.println("Gene is " + gene);
        dna = "ATCCTATGCTTCGGGCTCTAATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);
        dna = "ATGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);
        dna = "gatgctataat";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);
        dna = "atgctataa";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);
    }
    public String findGenes(String dna,int where){
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG",where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int temp = Math.min(taaIndex,tagIndex);
        int minIndex = Math.min(temp,tgaIndex);
        if (minIndex == dna.length()){
            return "";
        }
        return dna.substring( startIndex , minIndex +3 );
    }
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on "+ dna);
        printAllGene(dna);
    }
    public void printAllGene(String dna){
        int startIndex = 0;
        while (true){
            String  currentGene = findGenes(dna,startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
}


