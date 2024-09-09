import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Oblig5Hele {
    public static void main(String[] args) throws InterruptedException {
        Monitor2 monitorHattSykdom = new Monitor2(new SubsekvensRegister(), "Syk");
        Monitor2 monitorIkkeSykdom = new Monitor2(new SubsekvensRegister(), "Frisk");

        HashMap<String, Boolean> filerHash = new HashMap<>();
        File metadata = new File(args[0] + "/metadata.csv");
        Scanner sc = null;
        try {
            sc = new Scanner(metadata);
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf);
        }

        while (sc.hasNextLine()) {
            try {
                String filnavn = "";
                Boolean syk;
                String[] linje = sc.nextLine().split(",");
                filnavn = linje[0];
                syk = Boolean.parseBoolean(linje[1]);
                filerHash.put(filnavn, syk);
            } catch (NullPointerException npe) {
                System.out.println(npe);
                System.exit(1);
            }
        }
        sc.close();

        // Traader for a lese filer
        Thread[] trader = new Thread[filerHash.size()];
        int teller = 0;
        for (Map.Entry<String, Boolean> set : filerHash.entrySet()) {
            if (set.getValue()) {
                trader[teller] = new Thread(new LeseTrad(args[0] + "/" + set.getKey(), monitorHattSykdom));
                trader[teller].start();
            } else if (!set.getValue()) {
                trader[teller] = new Thread(new LeseTrad(args[0] + "/" + set.getKey(), monitorIkkeSykdom));
                trader[teller].start();
            }
            teller++;
        }

        for (int i = 0; i < trader.length; i++) {
            try {
                trader[i].join();
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
        }

        System.out.println("Antall hashmap i subsekvensregister for de som har hatt sykdom: "
                + monitorHattSykdom.hentAntallHashM());
        System.out.println("Antall hashmap i subsekvensregister for de som IKKE har hatt sykdom: "
                + monitorIkkeSykdom.hentAntallHashM());

        // Traader for a flette
        final int ANTALL_FLETTETRADER = 8;
        Thread[] traderFletteIS = new Thread[ANTALL_FLETTETRADER];
        for (int i = 0; i < ANTALL_FLETTETRADER; i++) {
            traderFletteIS[i] = new Thread(new FletteTrad(monitorIkkeSykdom, monitorIkkeSykdom.register, "frisk"));
            traderFletteIS[i].start();
        }

        Thread[] traderFletteS = new Thread[ANTALL_FLETTETRADER];
        for (int i = 0; i < ANTALL_FLETTETRADER; i++) {
            traderFletteS[i] = new Thread(new FletteTrad(monitorHattSykdom, monitorHattSykdom.register, "syk"));
            traderFletteS[i].start();
        }

        // for (int i = 0; i < traderFletteIS.length; i++) {
        // try {
        // traderFletteIS[i].join();
        // } catch (InterruptedException ie) {
        // System.out.println(ie);
        // }
        // }

        for (Thread trad : traderFletteS) {
            trad.join();
        }

        // for (int i = 0; i < traderFletteS.length; i++) {
        // try {
        // traderFletteS[i].join();
        // } catch (InterruptedException ie) {
        // System.out.println(ie);
        // }
        // }

        for (Thread trad : traderFletteIS) {
            trad.join();
        }

        System.out.println("Antall hashmap i subsekvensregister etter sammenlaaing. Ikke sykdom: "
                + monitorIkkeSykdom.register.hentAntallHashM());

        String storstSekv = "";
        int storstAnt = 0;
        for (String s : monitorIkkeSykdom.register.hashBeholder.get(0).keySet()) {
            if (monitorIkkeSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster() > storstAnt) {
                storstSekv = s;
                storstAnt = monitorIkkeSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster();
            }
        }

        System.out.println("Antall hashmap i subsekvensregister etter sammenlaaing. Hatt sykdom: "
                + monitorHattSykdom.register.hentAntallHashM());

        System.out.println(
                "\n--Subsekvens med hoeyest antall forekomster (Ikke sykdom)--\n" + storstSekv + ": " + storstAnt);

        String storstSekvS = "";
        int storstAntS = 0;
        for (String s : monitorHattSykdom.register.hashBeholder.get(0).keySet()) {
            if (monitorHattSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster() > storstAntS) {
                storstSekvS = s;
                storstAntS = monitorHattSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster();
            }
        }

        System.out.println(
            "\n--Subsekvens med hoeyest antall forekomster (Hatt sykdom)--\n" + storstSekvS + ": " + storstAntS);


        int diff = 0;
        int diffA = 0;
        String storstDiffSekvS = "";
        String storstDiffSekvSA = "";
        HashMap<String, Integer> hashDif = new HashMap<>();
        for (String s : monitorHattSykdom.register.hashBeholder.get(0).keySet()) {

            if (monitorIkkeSykdom.register.hashBeholder.get(0).containsKey(s)) {
                if ((monitorHattSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster()
                        - monitorIkkeSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster()) > diff) {
                    diff = monitorHattSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster()
                            - monitorIkkeSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster();
                    storstDiffSekvS = s;

                    // legger til subsekvens i beholder hvis differansen er storre enn eller lik 7
                    if (diff >= 7) {
                        hashDif.put(s, diff);
                    }
                }
            } else {
                if (monitorHattSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster()
                        - 0 > diffA) {
                    diffA = monitorHattSykdom.register.hashBeholder.get(0).get(s).hentAntallForekomster()
                            - 0;
                    storstDiffSekvSA = s;
                    if (diffA >= 7) {
                        hashDif.put(s, diffA);
                    }
                }
            }
        }
        if (diff > diffA) {
            System.out.println(
                    "\n--Subsekvens med storst differanse mellom syk og frisk--\n" + storstDiffSekvS + ": " + diff);
        } else {
            System.out.println(
                    "\n--Subsekvens med storst differanse mellom syk og frisk--\n" + storstDiffSekvSA + ": " + diffA);
        }

        System.out.println("\nSubsekvenser med minst 7 flere forekomster hos syke: ");
        for (Map.Entry<String, Integer> set : hashDif.entrySet()) {
            System.out.println(set.getKey() + " (" + set.getValue() + ")");
        }

    }
}
