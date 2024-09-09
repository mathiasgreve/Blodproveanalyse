import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 {
    SubsekvensRegister registerMonitor;

    private Lock laas = new ReentrantLock();

    // konstruktor
    public Monitor1(SubsekvensRegister register) {
        registerMonitor = register;
    }

    public void settInnNyHashM(HashMap<String, Subsekvens> hashmap) {

        laas.lock();
        try {
            registerMonitor.settInnNyHashM(hashmap);
        } finally {
            laas.unlock();
        }
    }

    // ta ut en vilkårlig Hashmap med subsekvenser
    public HashMap<String, Subsekvens> taUtHashMap() /* throws InterruptedException */ {

        return registerMonitor.taUtHashMap();
    }

    // spørre hvor mange HashMap-er den inneholder
    public int hentAntallHashM() {

        return registerMonitor.hentAntallHashM();
    }

    // les inn en fil og legger til UNIKE subsekvenser i en HashMap
    // returnerer HashMap
    public HashMap<String, Subsekvens> lesInnFil(String filnavn) {
        laas.lock();
        try {
            return registerMonitor.lesInnFil(filnavn);
        } finally {
            laas.unlock();
        }
    }

    HashMap<String, Subsekvens> slaaSammenHashM(HashMap<String, Subsekvens> hashM1,
            HashMap<String, Subsekvens> hashM2) {

        return registerMonitor.slaaSammenHashM(hashM1, hashM2);
    }
}
