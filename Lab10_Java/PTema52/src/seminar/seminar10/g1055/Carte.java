package seminar.seminar10.g1055;

import java.io.Serializable;

public class Carte implements Serializable {
    private String cota, titlu;
    private int anul;
    private double valoareInventar;

    public String getCota() {
        return cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getAnul() {
        return anul;
    }

    public void setAnul(int anul) {
        this.anul = anul;
    }

    public double getValoareInventar() {
        return valoareInventar;
    }

    public void setValoareInventar(double valoareInventar) {
        this.valoareInventar = valoareInventar;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "cota='" + cota + '\'' +
                ", titlu='" + titlu + '\'' +
                ", anul=" + anul +
                ", valoareInventar=" + valoareInventar +
                '}';
    }
}
