public class LeseTrad implements Runnable{
    String filnavn;
    // Monitor1 monitor;
    Monitor2 monitor;

    public LeseTrad(String filnavn, Monitor2 monitor) {
        this.filnavn = filnavn;
        this.monitor = monitor;
        // this.monitor2=monitor2;
    }


    @Override
    public void run(){
        monitor.settInnNyHashM(monitor.lesInnFil(filnavn));

    }
}
