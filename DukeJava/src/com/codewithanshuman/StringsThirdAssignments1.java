package com.codewithanshuman;

import edu.duke.*;

public class StringsThirdAssignments1 {

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
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true){
            String currentGene = findGenes(dna, startIndex);
            if ( currentGene.isEmpty() ){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    public void testOnAllGene(String dna){
        System.out.println("Number of genes : " + countGene(dna) );
        getAllGenes(dna);
        System.out.println("Storaging Completed!");
        System.out.println("C/G ratio: " + cgRatio(dna) );
        System.out.println("CTG counts:" + countCTG(dna));
    }
    public float cgRatio( String dna ) {
        String dnaLow = dna.toLowerCase();
        int cgCount = 0;
        int start = 0;
        while (true) {
            int pos = dnaLow.indexOf("c", start);
            if (pos == -1) {
                start = 0;
                break;
            }
            cgCount += 1;
            start = pos + 1;
        }
        while (true) {
            int pos = dnaLow.indexOf("g", start);
            if (pos == -1) {
                start = 0;
                break;
            }
            cgCount += 1;
            start = pos + 1;
        }
        return ( (float) cgCount ) / dna.length();
    }
    public int countCTG(String dna){
        int countCTG =0;
        int CTGIndex = 0;
        while (true){
            int currentCTG = dna.indexOf("CTG",CTGIndex);
            if (currentCTG == -1) {
                break;
            }
            countCTG++;
            CTGIndex = dna.indexOf("CTG", currentCTG +3);
        }
        return countCTG;
    }
    public void testCTG() {
        System.out.println("Number of CTGs: " + countCTG("CTGxxxCTGxxxCTGyyyCTGxCTG"));      //5
    }

    public void processGenes(StorageResource sr){
        int countSr = 0;
        int countCGRatio = 0;
        int totalGenes = 0;
        int longestGene = 0;
        String gene ="";
        for (String f: sr.data()) {
            totalGenes++;
            if(f.length()>60){
                System.out.println(f);
                countSr++;
            }
            double cgRat = cgRatio(f);
            if(cgRat>0.35){
                countCGRatio++;
            }
            gene = findGenes(f,0);
            if (gene.length()>longestGene){
                longestGene = gene.length();
            }
        }
        System.out.println("CG Ratio: " + countCGRatio);
        System.out.println("Genes that are 60+ chars: " + countSr);
        System.out.println("The length of the longest gene: " + longestGene);
        System.out.println("Total Genes: " + totalGenes);
    }

    public void testProcessGenes() {

        String nineLong = "ATGxxxTAAyyyATGxxxTAG";             //no genes longer than 9
        StorageResource geneList = getAllGenes(nineLong);
        processGenes(geneList);

        String dna1 = "ATGxxxyyyTAAyyyATGxxxyyyxxxyyyxxxTAG";  //2 genes longer than 9
        geneList = getAllGenes(dna1);
        processGenes(geneList);

        String dna2 = "ATGCGCCyyTAAyyyATGxxxyyyCGGGGCxxxTAG";  //genes with 0.35+ CG ratio
        geneList = getAllGenes(dna2);
        processGenes(geneList);

        String dna3 = "ATGxxxyyyxxxyyyTAG";                    //genes with 0.35- CG ratio
        geneList = getAllGenes(dna3);
        processGenes(geneList);

    }

    public void testProcessWithRealGene(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //System.out.println("DNA: " + dna.toUpperCase());
        System.out.println("Total CTGs: " + countCTG(dna));
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);
    }
}
