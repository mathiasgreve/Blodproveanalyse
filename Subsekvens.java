public class Subsekvens {
    public final String subsekvens;
    private int antallForekomster;

    // konstruktør
    public Subsekvens(String subsekvens, int antForekomster) {
        this.subsekvens = subsekvens;
        antallForekomster = antForekomster;
    }

    public int hentAntallForekomster() {
        return antallForekomster;
    }

    public void endreAntallForekomster(int antallForekom) {
        this.antallForekomster += antallForekom;
    }

    public void oekAntallForekomster() {
        antallForekomster++;
    }

    @Override
    public String toString() {
        return "(" + subsekvens + ", " + antallForekomster + ")";
    }
}