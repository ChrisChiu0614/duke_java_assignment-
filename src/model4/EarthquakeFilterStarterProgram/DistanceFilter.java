package src.model4.EarthquakeFilterStarterProgram;

import src.model4.EarthquakeFilterStarterProgram.Location;

public class DistanceFilter implements Filter{
    private Location location;
    private double maximum;

    public DistanceFilter(Location location, double maximum){
        this.location = location;
        this.maximum = maximum;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return location.distanceTo(qe.getLocation())<maximum;
    }
    @Override
    public String getName() {
        return "Filters used are: "+this.getClass().getName();
    }
}
