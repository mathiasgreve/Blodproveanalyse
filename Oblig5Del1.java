import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Oblig5Del1 {
    public static void main(String[] args) {
        SubsekvensRegister register = new SubsekvensRegister();

        

        // leter i /metadata.csv etter filnavn for innlesning
        ArrayList<String> filer = new ArrayList<>();
        File metadata = new File(args[0] + "/metadata.csv");
        Scanner sc = null;
        try {
            sc = new Scanner(metadata);
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf);
        }

        while (sc.hasNextLine()) {
            try {
                filer.add(sc.nextLine());
            } catch (NullPointerException npe) {
                // System.out.println("Feil: for kort bokstavsekvens. Stopper programmet/innlesning");
                System.out.println(npe);
                System.exit(1);
            }
        }
        sc.close();

        //leser inn filer
        for(String fil : filer){
            System.out.println("Filnavn: "+fil);
            register.settInnNyHashM(register.lesInnFil(args[0]+"/"+fil));
        }

        System.out.println("Antall hashmap i subsekvensregister "+register.hentAntallHashM());

        while(register.hentAntallHashM()>1){
            register.settInnNyHashM(register.slaaSammenHashM(register.taUtHashMap(),register.taUtHashMap()));
        }

        System.out.println("Antall hashmap i subsekvensregister etter sammenlaaing: "+register.hentAntallHashM());

        
        String storstSekv="";
        int storstAnt = 0;
        for(String s : register.hashBeholder.get(0).keySet()){
            if(register.hashBeholder.get(0).get(s).hentAntallForekomster() > storstAnt ){
                storstSekv = s;
                storstAnt = register.hashBeholder.get(0).get(s).hentAntallForekomster();
            }
        }
        System.out.println("--Subsekvens med hoeyest antall forekomster--\n"+storstSekv+": "+storstAnt);
        // register.hashBeholder.get(0).forEach((key, value) -> System.out.println(value));


    }
}
