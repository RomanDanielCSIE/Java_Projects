package Lab1_Java;

import java.util.Date;

public abstract class Persoana implements Evaluare {
    private String nume,adresa;
    private Date dataNasterii;

    public Persoana() {
    }

    public Persoana(String nume, String adresa, Date dataNasterii) {
        this.nume = nume;
        this.adresa = adresa;
        this.dataNasterii = dataNasterii;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    @Override
    public String toString() {
        return "Persoana{" + nume + "," + adresa + "," + dataNasterii + '}';
    }
}
