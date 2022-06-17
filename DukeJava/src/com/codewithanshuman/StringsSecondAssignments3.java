package com.codewithanshuman;

public class StringsSecondAssignments3 {

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
    public int countGene(String dna){
        int howMany = 0;
        int startIndex = 0;
        while (true){
            String  currentGene = findGenes(dna,startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            howMany++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return howMany;
    }
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on "+ dna);
        printAllGene(dna);
        System.out.println("Number of genes : " + countGene(dna) );
    }
}
