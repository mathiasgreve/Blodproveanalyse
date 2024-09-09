import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SubsekvensRegister {
    ArrayList<HashMap<String, Subsekvens>> hashBeholder = new ArrayList<>();

    // tar en HashMap med subsekvenser som parameter og legger denne til som element
    // i registeret (ArrayList)
    public void settInnNyHashM(HashMap<String, Subsekvens> hashmap) {
        // setter inn en HashMap med subsekvenser
        hashBeholder.add(hashmap);
    }

    // ta ut en vilkårlig Hashmap med subsekvenser
    public HashMap<String, Subsekvens> taUtHashMap() {
        return hashBeholder.remove(0);
    }

    // spørre hvor mange HashMap-er den inneholder
    public int hentAntallHashM() {
        return hashBeholder.size();
    }

    // les inn en fil og legger til UNIKE subsekvenser i en HashMap
    // returnerer denne HashMapen
    public static HashMap<String, Subsekvens> lesInnFil(String filnavn) {
        HashMap<String, Subsekvens> hashMap = new HashMap<>();

        File fil = new File(filnavn);
        Scanner sc = null;
        try {
            sc = new Scanner(fil);
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf);
        }

        while (sc.hasNextLine()) {
            try {
                for (String s : hentSubsekvenser(sc.nextLine())) {
                    if (!hashMap.containsKey(s)) {
                        Subsekvens subsek = new Subsekvens(s, 1);
                        hashMap.put(s, subsek);
                    }
                }
            } catch (NullPointerException npe) {
                System.out.println("Feil: for kort bokstavsekvens. Stopper programmet/innlesning");
                System.out.println(npe);
                System.exit(1);
            }
        }
        sc.close();
        return hashMap;
    }

    // hjelpemetode (til les inn fil) - tar inn en tekststreng som parameter og
    // lager substrenger
    // paa 3 bokstaver som legges til i en ArrayList.
    // returnerer ArrayListen
    static ArrayList<String> hentSubsekvenser(String tekstlinje) {
        if (tekstlinje.length() < 3) {
            return null;
        }
        ArrayList<String> subsekvenserListe = new ArrayList<>();
        for (int i = 0; i < (tekstlinje.length() - 2); i++) {
            subsekvenserListe.add(tekstlinje.substring(i, i + 3));
        }
        return subsekvenserListe;
    }

    // slaar sammen to HashMap-er til én
    // returnerer denne HashMappen
    HashMap<String, Subsekvens> slaaSammenHashM(HashMap<String, Subsekvens> hashM1,
            HashMap<String, Subsekvens> hashM2) {

        for (String key : hashM2.keySet()) {
            if (hashM1.containsKey(key)) {
                hashM1.get(key).endreAntallForekomster(hashM2.get(key).hentAntallForekomster());
            } else {
                hashM1.put(key, hashM2.get(key));
            }
        }

        return hashM1;
    }
}
