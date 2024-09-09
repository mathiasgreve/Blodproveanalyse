// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.ArrayList;
// import java.util.Scanner;
// import java.util.concurrent.CountDownLatch;

// public class Oblig5Del2 {
//     public static void main(String[] args) {
//         // SubsekvensRegister register = ;
//         Monitor1 monitor = new Monitor1(new SubsekvensRegister());

        

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
//                 // System.out.println("Feil: for kort bokstavsekvens. Stopper programmet/innlesning");
//                 System.out.println(npe);
//                 System.exit(1);
//             }
//         }
//         sc.close();

//         //oppretter trader som leser inn filer
//         Thread[] trader = new Thread[filer.size()];
//         // CountDownLatch minBarriere = new CountDownLatch(filer.size());
//         int teller = 0;
//         for(String fil : filer){
//             System.out.println("Filnavn: "+fil);
//             // monitor.settInnNyHashM(monitor.lesInnFil(args[0]+"/"+fil));
//             trader[teller]=new Thread(new LeseTrad(args[0]+"/"+fil, monitor));
//             trader[teller].start();
//             teller++;
//         }

//         for(int i = 0;i<trader.length;i++){
//             try{
//                 trader[i].join();
//             } catch (InterruptedException ie){
//                 System.out.println(ie);
//             }
            
//         }

//         System.out.println("Antall hashmap i subsekvensregister "+monitor.hentAntallHashM());

//         while(monitor.hentAntallHashM()>1){
//             monitor.settInnNyHashM(monitor.slaaSammenHashM(monitor.taUtHashMap(),monitor.taUtHashMap()));
//         }

//         System.out.println("Antall hashmap i subsekvensregister etter sammenlaaing: "+monitor.hentAntallHashM());

        
//         String storstSekv="";
//         int storstAnt = 0;
//         for(String s : monitor.registerMonitor.hashBeholder.get(0).keySet()){
//             if(monitor.registerMonitor.hashBeholder.get(0).get(s).hentAntallForekomster() > storstAnt ){
//                 storstSekv = s;
//                 storstAnt = monitor.registerMonitor.hashBeholder.get(0).get(s).hentAntallForekomster();
//             }
//         }
//         System.out.println("--Subsekvens med hoeyest antall forekomster--\n"+storstSekv+": "+storstAnt);
//         // monitor.hashBeholder.get(0).forEach((key, value) -> System.out.println(value));


//     }
// }
