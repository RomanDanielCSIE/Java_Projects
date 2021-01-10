package Lab1_Java;

import java.util.Date;

public class Profesor extends Persoana {
    private String departament;
    private Punctaj punctaj;

    public Profesor() {
    }

    public Profesor(String nume, String adresa, Date dataNasterii,
                    String departament, Punctaj punctaj) {
        super(nume, adresa, dataNasterii);
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
}
