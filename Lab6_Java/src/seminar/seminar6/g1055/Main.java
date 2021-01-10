package seminar.seminar6.g1055;


import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private List<Depozit> depozite = new ArrayList<>();
    private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        Main app = new Main();
        app.citireDepozite("clienti.csv");
        List<Depozit> listaDepozite = app.getDepozite();
        app.afisare(listaDepozite,"Lista initiala:");

        //Sort deposits by sum
        List<Depozit> listaDepSortata = listaDepozite.stream(). //Intoarce multime de depozite
                sorted((d1,d2)->d1.getSuma()<d2.getSuma()?-1:1). //Intoarce multime sortata de depozite
                collect(Collectors.toList()); // Colectare multime in lista
        app.afisare(listaDepSortata,"\nLista depozitelor sortate dupa suma:");

        //List of a client's deposits
        int client=100;
        List<Depozit> listaClient = listaDepozite.stream(). //Intoarce multime de depozite
                filter(depozit -> depozit.getId_client()==client). //Eliminare din multime a elementelor ce nu trec testul
                collect(Collectors.toList());
        app.afisare(listaClient,"\nLista depozitelor clientului cu id-ul "+client+":");

        //List of deposits opened in a certain period
        try {
            Date data1 = df.parse("01.02.2020"),data2 = df.parse("01.03.2020");
            List<Depozit> listaInterval = listaDepozite.stream()
                    .filter( depozit -> depozit.getData().getTime()>=data1.getTime() && depozit.getData().before(data2))
                    .collect(Collectors.toList());
            app.afisare(listaInterval,"\nLista depozitelor din intervalul ["+df.format(data1)+" - "+
                    df.format(data2)+"):");
        }
        catch (Exception ex){
            System.err.println(ex);
        }

        //Group deposits after date in a Map<Date,List<Depozit>>
        Map<Date,List<Depozit>> situatiaPeDate = listaDepozite.stream()
                .collect(Collectors.groupingBy(depozit -> depozit.getData()));
        System.out.println("\nSituatia depozitelor pe data deschiderii:");
        situatiaPeDate.keySet().forEach(data->System.out.println(df.format(data)+":"+situatiaPeDate.get(data)));


        //Calculation of total sum from a client's each deposit in a structure Map<Integer,Double>
        Map<Integer,Double> sumePeClient = listaDepozite.stream()
                .collect(Collectors.groupingBy(
                        depozit -> depozit.getId_client(),
                        Collectors.summingDouble(depozit -> depozit.getSuma())));
        System.out.println("\nSuma depozitelor pe clienti:");
        sumePeClient.keySet().forEach(id->System.out.println(id+":"+sumePeClient.get(id)));


        //Collecting iban codes
        List<String> coduriIban = listaDepozite.parallelStream().map(depozit -> depozit.getCod_iban()).
                collect(Collectors.toList());
        app.afisare(coduriIban,"\nLista codurilor iban:");

    }

    private void afisare(List<?> lista, String mesaj){
        System.out.println(mesaj);
        lista.forEach(System.out::println);
    }

    public void citireDepozite(String numeFisier){
        depozite.clear();
        try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
            in.lines().forEach(linie -> {
                Depozit depozit=new Depozit();
                String[] t = linie.split(",");
                depozit.setCod_iban(t[0].trim());
                try{
                    depozit.setData(df.parse(t[1].trim()));
                }
                catch (Exception ex){
                    System.err.println(ex);
                }
                depozit.setId_client( Integer.parseInt(t[2].trim()) );
                depozit.setTermen(Integer.parseInt(t[3].trim()));
                depozit.setSuma(Double.parseDouble(t[4].trim()));
                depozite.add(depozit);
            });

        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public List<Depozit> getDepozite() {
        return depozite;
    }
}
