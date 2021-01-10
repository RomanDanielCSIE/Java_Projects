package Lab4_Java;

import java.util.Date;

public class Profesor extends Persoana implements Cloneable{
    private String departament;
    private Punctaj punctaj;

    public Profesor() {
    }

    public Profesor(String nume, String adresa, Date dataNasterii,long cnp,
                    String departament, Punctaj punctaj) {
        super(nume, adresa, dataNasterii,cnp);
        this.departament = departament;
        this.punctaj = punctaj;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public Punctaj getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(Punctaj punctaj) {
        this.punctaj = punctaj;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                departament + "," + punctaj +
                "} " + super.toString();
    }

    @Override
    public boolean evaluare() {
        return punctaj.evaluat();
    }

    @Override
    public double medie() {
        return punctaj.getDidactic()*0.3+punctaj.getCercetare()*0.5+
                punctaj.getVizibilitate()*0.2;
    }

    @Override
    public int compareTo(Persoana o) {
        if(medie()<o.medie()){
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Profesor clona = (Profesor) super.clone();
        clona.setPunctaj((Punctaj) punctaj.clone());
        return clona;
    }
}
