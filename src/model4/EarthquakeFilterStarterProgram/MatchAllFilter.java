package src.model4.EarthquakeFilterStarterProgram;

import java.util.ArrayList;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;

    public MatchAllFilter(){
        filters = new ArrayList<>();
    }
    public ArrayList<Filter> getFilters(){
        return filters;
    }

    public void addFilter(Filter f){
        filters.add(f);
    }
    @Override
    public boolean satisfies(QuakeEntry qe) {
        return filters.stream().allMatch(f->f.satisfies(qe));
    }
    @Override
    public String getName() {
        return "Filters used are: "+this.getClass().getName();
    }


}
