package seminar.seminar6.g1055;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Depozit {
    private String cod_iban;
    private Date data;
    private int id_client,termen;
    private double suma;

    public Depozit() {
    }

    public Depozit(String cod_iban, Date data, int id_client, int termen, double suma) {
        this.cod_iban = cod_iban;
        this.data = data;
        this.id_client = id_client;
        this.termen = termen;
        this.suma = suma;
    }

    public String getCod_iban() {
        return cod_iban;
    }

    public void setCod_iban(String cod_iban) {
        this.cod_iban = cod_iban;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getTermen() {
        return termen;
    }

    public void setTermen(int termen) {
        this.termen = termen;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return data!=null?
                cod_iban + "," + df.format(data) +"," + id_client + "," + termen + "," + suma:
                cod_iban + ", ," + id_client + "," + termen + "," + suma;
    }
}
