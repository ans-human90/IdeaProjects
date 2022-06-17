package com.codewithanshuman;

public class StringsSecondAssignments2 {
    public int howMany ( String stringa , String stringb ){
        int howMany = 0;
        int currIndex = 0;
        while(true){
            currIndex = stringb.indexOf(stringa,currIndex);
            if (currIndex == -1){
                break;
            }
            howMany++;
            currIndex = currIndex + stringa.length();
        }
        return howMany;
    }
    public void testHowMany(){
        System.out.println("In ATGAACGAATTGAATC the GAA occurs: ");
        System.out.println(howMany("GAA","ATGAACGAATTGAATC")+"times");
        System.out.println("In ATAAAA the AA occurs: ");
        System.out.println(howMany("AA","ATAAAA")+"times");
    }
}
