// import java.util.ArrayList;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        SubsekvensRegister register = new SubsekvensRegister();

        register.settInnNyHashM(register.lesInnFil("TestOppgaveEks/fil1.csv"));
        register.settInnNyHashM(register.lesInnFil("TestOppgaveEks/fil2.csv"));
        System.out.println("Skriver ut fil1");
        register.taUtHashMap().forEach((key, value) -> System.out.println(value)); 
        System.out.println("Skriver ut fil2");
        register.taUtHashMap().forEach((key, value) -> System.out.println(value));

        register.settInnNyHashM(register.lesInnFil("TestOppgaveEks/fil1.csv"));
        register.settInnNyHashM(register.lesInnFil("TestOppgaveEks/fil2.csv"));
        HashMap<String, Subsekvens> h = new HashMap<>();
        h=slaaSammenHashM(register.taUtHashMap(), register.taUtHashMap());
        System.out.println("Skriver ut fil1+fil2");
        h.forEach((key, value) -> System.out.println(value));

    }

    static HashMap<String, Subsekvens> slaaSammenHashM(HashMap<String, Subsekvens> hashM1, HashMap<String, Subsekvens> hashM2) {
        // HashMap<String, Subsekvens> hashMap = new HashMap<>();
        // hashM2.forEach(
        //     (key, value)
        //     ->
        // Subsekvens subsek = null;
        for(String key: hashM2.keySet()){
            // Subsekvens subsek = new Subsekvens(key,)
            if(hashM1.containsKey(key)){
                hashM1.get(key).endreAntallForekomster(hashM2.get(key).hentAntallForekomster());
            } else{
                hashM1.put(key,hashM2.get(key));
            }
        }

        
        return hashM1;
    }

}
