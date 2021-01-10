package seminar.seminar8.g1055;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main implements Closeable {
    private Connection c;

    public Main() throws Exception {
        String urlConectare = "jdbc:sqlite:s8_1055.db";
        c = DriverManager.getConnection(urlConectare);
    }

    public static void main(String[] args) {
        try (Main app = new Main()) {
            System.out.println("Conectare la baza de date.");
            app.salvare("carti.csv");
            List<Carte> lista = app.interogare("select * from CARTE");
            System.out.println("\nLista cartilor:");
            lista.forEach(System.out::println);

            //Select book by title/title-part
            String titlu="Istoria";
            List<Carte> listaDupaTitlu =
                    app.interogare("select * from CARTE where titlu like '"+titlu+"%'");
            System.out.println("\nLista cartilor al caror titlu incepe cu "+titlu);
            listaDupaTitlu.forEach(System.out::println);

            //Update table
            app.actualizare("update CARTE set pret=150 where cota=1234");
            app.actualizare("delete from CARTE where cota=1445");
            List<Carte> listaActualizata = app.interogare("select * from CARTE");
            System.out.println("\nLista actualizata a cartilor:");
            listaActualizata.forEach(System.out::println);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private void actualizare(String comanda) throws Exception{
        try(Statement s=c.createStatement()){
            s.executeUpdate(comanda);
        }
    }

    private List<Carte> interogare(String comanda) throws Exception {
        List<Carte> lista=new ArrayList<>();
        try(Statement s = c.createStatement()){
            try(ResultSet r = s.executeQuery(comanda)){
                while (r.next()){
                    Carte carte=new Carte();
                    carte.setCota(r.getInt(1));
                    carte.setTitlu(r.getString(2));
                    carte.setEditura(r.getString(3));
                    carte.setAnul(r.getInt(4));
                    carte.setPret(r.getDouble(5));
                    lista.add(carte);
                }
            }
        }
        return lista;
    }

    private void salvare(String numeFisier) throws Exception {

        //Verify existence of "CARTE" table
        try (ResultSet r = c.getMetaData().
                getTables(null, null, "CARTE", new String[]{"TABLE"})) {
            if (!r.next()) {
                try (Statement s = c.createStatement()) {
                    String comandaCreareTabela =
                            "create table CARTE (cota integer,titlu varchar(50),editura varchar(30),anul integer,pret double)";
                    s.executeUpdate(comandaCreareTabela);
                    System.out.println("Tabela CARTE a fost creata.");
                }
            } else {
                try (Statement s = c.createStatement()) {
                    s.executeUpdate("delete from CARTE");
                }
            }
        }

        //Save from text file into "CARTE" table
        try (BufferedReader in = new BufferedReader(new FileReader(numeFisier));
             Statement s = c.createStatement()) {
            in.lines().forEach(linie->{
                String[] t = linie.split(",");
                String comandaInserare = "insert into CARTE values ("+t[0].trim()+",'"+
                        t[1].trim()+"','"+t[2].trim()+"',"+t[3].trim()+","+t[4].trim()+")";
                try{
                    s.executeUpdate(comandaInserare);
                }
                catch (Exception ex){
                    System.err.println(ex);
                }
            });
            System.out.println("Inserari efectuate cu succes.");
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
