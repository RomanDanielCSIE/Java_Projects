package Lab2_Java;

import java.util.Date;

public abstract class Persoana implements Evaluare,Comparable<Persoana> {
    private String nume,adresa;
    private Date dataNasterii;
    private long cnp;

    public Persoana() {
    }

    public Persoana(String nume, String adresa, Date dataNasterii,long cnp) {
        this.nume = nume;
        this.adresa = adresa;
        this.dataNasterii = dataNasterii;
        this.cnp=cnp;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
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
        return "Persoana{" + nume + "," + adresa + "," + dataNasterii +","+cnp+ '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persoana persoana = (Persoana) o;

        return cnp == persoana.cnp;
    }

    @Override
    public int hashCode() {
        return (int) (cnp ^ (cnp >>> 32));
    }
}
