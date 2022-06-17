package com.codewithanshuman;

public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter(String wh, String ph){
        where =wh;
        phrase = ph;
    }

    @Override
    public String getName() {
        return "Phrase ";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if(where == "start" && qe.getInfo().startsWith(phrase)){
            return true;
        }
        else if(where == "end" && qe.getInfo().endsWith(phrase)){
            return true;
        }
        else if(where == "any" && qe.getInfo().indexOf(phrase)>=0){
            return true;
        }
        else{
            return false;
        }
    }
}
