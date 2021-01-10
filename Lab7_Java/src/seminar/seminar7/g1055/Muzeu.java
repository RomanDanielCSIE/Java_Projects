package seminar.seminar7.g1055;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Muzeu {
    private ArrayDeque<Integer> vizitatori = new ArrayDeque<>();
    private int v; //Capacitatea
    private boolean stop;

    public Muzeu(int v) {
        this.v = v;
    }

    public synchronized void intrare(List<Integer> noiVizitatori){
        while (vizitatori.size()+noiVizitatori.size()>v){
            try{
                wait();
            }
            catch (InterruptedException ex){}
        }
        for (int i:noiVizitatori){
            vizitatori.addFirst(i);
        }
        System.out.println("\nAu intrat vizitatorii:"+noiVizitatori);
        System.out.println("\nMuzeu: "+vizitatori.size()+","+vizitatori);
        notifyAll();
    }

    public synchronized void iesire(int m){
        while (vizitatori.size()==0){
            try{
                wait();
            }
            catch (InterruptedException ex){}
        }
        int numarVizitatori = vizitatori.size();
        int numarIesiti = numarVizitatori<m?numarVizitatori:m;
        List<Integer> iesiti = new ArrayList<>();
        for (int i=0;i<numarIesiti;i++){
            iesiti.add(vizitatori.pollLast());
        }
        System.out.println("\nVizitatori iesiti:"+iesiti);
        System.out.println("\nMuzeu: "+vizitatori.size()+","+vizitatori);
        notifyAll();
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
