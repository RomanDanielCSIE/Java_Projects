package seminar.seminar7.g1055;

import java.util.ArrayList;
import java.util.List;

public class Intrare extends Thread {

    private Muzeu muzeu;
    private int n;
    private int idVizitator=1;

    public Intrare(Muzeu muzeu, int n) {
        this.muzeu = muzeu;
        this.n = n;
    }

    @Override
    public void run() {
        while (!muzeu.isStop()){
            int nrVizitatori = (int) (Math.random()*n) + 1;
            List<Integer> grup = new ArrayList<>();
            for (int i=0;i<nrVizitatori;i++){
                grup.add(idVizitator+i);
            }
            idVizitator+=nrVizitatori;
            muzeu.intrare(grup);
        }
    }
}
