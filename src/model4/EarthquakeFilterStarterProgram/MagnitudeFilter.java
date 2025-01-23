package src.model4.EarthquakeFilterStarterProgram;

public class MagnitudeFilter implements Filter{
    private double minimum;
    private double maximum;

    public MagnitudeFilter(double minimum, double maximum){
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude()>=minimum && qe.getMagnitude()<=maximum;
    }
}
