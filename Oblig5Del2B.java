// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.ArrayList;
// import java.util.Scanner;

// public class Oblig5Del2B {
//     public static void main(String[] args) {
//         SubsekvensRegister register = new SubsekvensRegister();
//         // Monitor1 monitor = new Monitor1(register);
//         Monitor2 monitor2 = new Monitor2(register);

//         // leter i /metadata.csv etter filnavn for innlesning
//         // putter filnavn i arraylist: 'filer'
//         ArrayList<String> filer = new ArrayList<>();
//         File metadata = new File(args[0] + "/metadata.csv");
//         Scanner sc = null;
//         try {
//             sc = new Scanner(metadata);
//         } catch (FileNotFoundException fnf) {
//             System.out.println(fnf);
//         }

//         while (sc.hasNextLine()) {
//             try {
//                 filer.add(sc.nextLine());
//             } catch (NullPointerException npe) {
//                 System.out.println(npe);
//                 System.exit(1);
//             }
//         }
//         sc.close();

//         // leser inn filer
//         Thread[] trader = new Thread[filer.size()];
//         int teller = 0;
//         for (String fil : filer) {
//             System.out.println("Filnavn: " + fil);
//             trader[teller] = new Thread(new LeseTrad(args[0] + "/" + fil, monitor2));
//             trader[teller].start();
//             teller++;
//         }

//         for (int i = 0; i < trader.length; i++) {
//             try {
//                 trader[i].join();
//             } catch (InterruptedException ie) {
//                 System.out.println(ie);
//             }

//         }

//         System.out.println("Antall hashmap i subsekvensregister " + monitor2.hentAntallHashM());

//         final int ANTALL_FLETTETRADER = 8;
//         Thread[] traderFlette = new Thread[ANTALL_FLETTETRADER];
//         for (int i = 0; i < ANTALL_FLETTETRADER; i++) {
//             traderFlette[i] = new Thread(new FletteTrad(monitor2, register));
//             traderFlette[i].start();
//         }

//         for (int i = 0; i < traderFlette.length; i++) {
//             try {
//                 traderFlette[i].join();
//             } catch (InterruptedException ie) {
//                 System.out.println(ie);
//             }

//         }

//         System.out.println("Antall hashmap i subsekvensregister etter sammenlaaing: " + register.hentAntallHashM());

//         String storstSekv = "";
//         int storstAnt = 0;
//         for (String s : register.hashBeholder.get(0).keySet()) {
//             if (register.hashBeholder.get(0).get(s).hentAntallForekomster() > storstAnt) {
//                 storstSekv = s;
//                 storstAnt = register.hashBeholder.get(0).get(s).hentAntallForekomster();
//             }
//         }
//         System.out.println("--Subsekvens med hoeyest antall forekomster--\n" + storstSekv + ": " + storstAnt);

//     }
// }
