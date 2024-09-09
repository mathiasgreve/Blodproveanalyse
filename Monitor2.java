import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor2 {
    SubsekvensRegister register;
    private static Lock laas = new ReentrantLock();
    Condition ikkeTo = laas.newCondition();
    String id;

    // konstruktor
    public Monitor2(SubsekvensRegister register, String id) {
        this.register = register;
        this.id = id;
    }

    // metoden må vente inni monitoren dersom registeret ikke inneholder to hashmaps
    SubsekvensRegister hentUtTo() throws InterruptedException{
        laas.lock();
        SubsekvensRegister hashBeholder = new SubsekvensRegister();

        try {
            while (register.hentAntallHashM() < 2) {
                if (register.hentAntallHashM() == 1) {
                    Thread.currentThread().interrupt();
                    return null;
                }
                ikkeTo.await();
            }

            if (register.hentAntallHashM() > 1) {
                // ta ut to hashmaps fra registeret og legg inn i midlertidig beohlder
                hashBeholder.settInnNyHashM(register.taUtHashMap());
                hashBeholder.settInnNyHashM(register.taUtHashMap());

                return hashBeholder;
            }

        } catch (InterruptedException ie) {
            System.out.println("feil");
        } finally {
            laas.unlock();
        }
        return null;
    }

    public int hentAntallHashM() {
        laas.lock();
        try {
            return register.hentAntallHashM();
        } finally {
            laas.unlock();
        }
    }

    public void settInnFlettet(SubsekvensRegister toSubsekvensHM) {
        laas.lock();
        try {

            // setter inn en ny HM som består av de to HM som ble tatt, ut slått sammen
            register.settInnNyHashM(
                    register.slaaSammenHashM(toSubsekvensHM.taUtHashMap(), toSubsekvensHM.taUtHashMap()));

            ikkeTo.signal();
        }
        // catch (InterruptedException ie) {
        // System.out.println("Feil Monitor2 settInnFlettet");
        // System.exit(1);
        // }
        finally {
            laas.unlock();
        }
    }

    public void settInnNyHashM(HashMap<String, Subsekvens> hashmap) {

        laas.lock();
        try {
            register.settInnNyHashM(hashmap);
            ikkeTo.signalAll();
        } finally {
            laas.unlock();
        }
    }

    // les inn en fil og legger til UNIKE subsekvenser i en HashMap
    // returnerer HashMap
    public HashMap<String, Subsekvens> lesInnFil(String filnavn) {
        laas.lock();
        try {
            return register.lesInnFil(filnavn);
        } finally {
            laas.unlock();
        }
    }

    HashMap<String, Subsekvens> slaaSammenHashM(HashMap<String, Subsekvens> hashM1,
            HashMap<String, Subsekvens> hashM2) {
        laas.lock();
        try {
            return register.slaaSammenHashM(hashM1, hashM2);
        } finally {
            laas.unlock();
        }
    }
}
