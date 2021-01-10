package seminar.seminar7.g1055;

public class Main {
    public static void main(String[] args) {
        try{
            System.out.println("Start main ...");
            Muzeu muzeu = new Muzeu(30);
            Intrare intrare = new Intrare(muzeu,5);
            Iesire iesire = new Iesire(muzeu,3);
            intrare.start();
            iesire.start();
            Thread.sleep(100);
            muzeu.setStop(true);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }
}
