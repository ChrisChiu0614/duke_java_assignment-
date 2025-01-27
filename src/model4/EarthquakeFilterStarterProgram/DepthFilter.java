package src.model4.EarthquakeFilterStarterProgram;

public class DepthFilter implements Filter{
    private double minimum;
    private double maximum;

    public DepthFilter(double minimum, double maximum){
        this.minimum = minimum;
        this.maximum = maximum;
    }
    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth()>=minimum && qe.getDepth()<=maximum;
    }

    @Override
    public String getName() {
        return "Filters used are: "+this.getClass().getName();
    }
}
