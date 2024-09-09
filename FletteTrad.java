public class FletteTrad implements Runnable {

    Monitor2 monitor2;
    // final int antTrader = 8;
    SubsekvensRegister toHash;

    public FletteTrad(Monitor2 monitor2, SubsekvensRegister register, String tilstand) {
        this.monitor2 = new Monitor2(register, tilstand);
    }

    @Override
    public void run() {
        while (monitor2.hentAntallHashM() > 1 && !Thread.interrupted()) {
            // while (!Thread.interrupted()) {

            try {
                monitor2.settInnFlettet(monitor2.hentUtTo());
                System.out.println("antall hashmapper for "+monitor2.id+" "+monitor2.hentAntallHashM());
            } catch (InterruptedException ie) {
                System.out.println("feil!!!");
                break;
            }



        }

    }

}
