package src.model4.EarthquakeFilterStarterProgram;

public class PhraseFilter implements Filter {
    private String where;
    private String phase;

    public PhraseFilter(String where, String phase) {
        this.where = where;
        this.phase = phase;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return switch (where) {
            case "start" -> qe.getInfo().startsWith(phase);
            case "end" -> qe.getInfo().endsWith(phase);
            case "any" -> qe.getInfo().contains(phase);
            default -> false;
        };
    }
}
