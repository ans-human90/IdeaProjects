package com.codewithanshuman;

import java.util.ArrayList;

public class MatchAllFilter implements Filter{

    private ArrayList<Filter> filters;

    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f){
        filters.add(f);
    }

    @Override
    public String getName() {
        String names= "";
        for (Filter f: filters){
            names = names + f.getName();
        }
        return names;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f: filters){
            if (!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
}
