package seminar.seminar5.g1055;

import seminar.seminar4.g1055.MainS4;
import seminar.seminar4.g1055.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        MainS4 mainS4 = new MainS4();
        List<Student> lista = mainS4.citire();
        mainS4.afisareLista(lista, "Lista initiala:");


        //Save list into array
        Student[] vectorLista = lista.toArray(new Student[lista.size()]);

//        Parcurgere lista prin foreach si Consumer/labda/referinta la functie
        //List element parsing with foreach and Consumer/lambda/function reference
        System.out.println("\nShow list (foreach,Consumer):");
        lista.forEach(new Consumer<Student>() {
            @Override
            public void accept(Student student) {
                System.out.println(student);
            }
        });

/*
Lambda function syntax:
        (parameters) -> {code from the method}
*/

        System.out.println("\nAfisare lista (foreach,lambda):");
        lista.forEach(student -> System.out.println(student));


        System.out.println("\nAfisare lista (foreach,referinta la functie):");
        lista.forEach(System.out::println);

        //Filer by predicate
        lista.removeIf(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getGrupa() == 1055;
            }
        });
        mainS4.afisareLista(lista, "\nFiltrare dupa grupa (1055):");

        //Restore list from array
        lista.clear();
        for (Student student : vectorLista) {
            lista.add(student);
        }

        lista.removeIf(student -> student.getGrupa() == 1056);
        mainS4.afisareLista(lista, "\nFiltrare dupa grupa folosind expresie lambda (1056):");



        //Create a list of addresses from list of students using collect
        List<String> listaAdreselor = lista.stream().map(s -> s.getAdresa()).collect(Collectors.toList());
        System.out.println("\nLista adreselor:");
        listaAdreselor.forEach(System.out::println);

        //Create a Map using "cnp" as key and students as values using collect
        Map<Long, Student> mapStud = lista.stream().collect(Collectors.toMap(s -> s.getCnp(), s -> s));

        //Show Map structure
        System.out.println("\nStructura Map (chei:cnp,valori:studenti):");
        for (Long cnp : mapStud.keySet()) {
            System.out.println(cnp + ":\n" + mapStud.get(cnp));
        }

/*
Code for anonymous class instantiation:

        new Interface(){code anonymous class}
        or
        new Superclass(parameters){code anonymous class}
*/

        //Create a Map with "cnp" as key and anonymous classes as values which include the address,name and average
        Map<Long, ?> map2 = lista.stream().collect(Collectors.toMap(s -> s.getCnp(),
                s -> new Object() {
                    @Override
                    public String toString() {
                        return s.getAdresa() + "," + s.getNume() + "," + s.medie();
                    }
                }));

        System.out.println("\nAfisare Map (chei:cnp, valori:(sdresa+nume+media)):");
        for (Long cnp : map2.keySet()) {
            System.out.println(cnp + ":\n" + map2.get(cnp));
        }

        //Create a Map with "cnp" as key and values from the local class created for collection
        //The class used for collecting
        class InfoStud {
            String nume;
            double media;

            InfoStud(String nume, double media) {
                this.media = media;
                this.nume = nume;
            }

            @Override
            public String toString() {
                return nume + "," + media;
            }
        }

        Map<Long, InfoStud> map3 = lista.parallelStream().
                collect(Collectors.toMap(student -> student.getCnp(),
                        student -> new InfoStud(student.getNume(), student.medie())));
        System.out.println("\nAfisare Map (chei:cnp, valori:InfoStud):");
        for (Long cnp : map3.keySet()) {
            System.out.println(cnp + ":\n" + map3.get(cnp));
        }
    }

}
