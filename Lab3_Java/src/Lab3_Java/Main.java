package Lab3_Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
/*
            Student student = new Student();
            student.setNume("Ionescu Diana");
            student.setAdresa("Brasov, str. 1 Decembrie");
            SimpleDateFormat format = new SimpleDateFormat("MM.dd.yyyy");
            student.setDataNasterii(format.parse("10.10.2000"));
            student.setAnul(2);student.setGrupa(1055);
            student.setDiscipline(new Discipline[]{Discipline.ALGEBRA,Discipline.POO});
            student.setNote(new int[]{10,10});
            System.out.println(student);
            Profesor profesor = new Profesor("Popa Adrian","Bucuresti, str. Brasov, nr. 16",
                    format.parse("05.05.1968"),1680504123654L,"DICE",
                    new Punctaj(20,20,20));
            System.out.println(profesor);
            String evaluareStudent = student.evaluare()?"Promovat":"Nepromovat";
            System.out.println("Evaluare student "+student.getNume()+":"+evaluareStudent);
            String evaluareProvesor = profesor.evaluare()?"Indeplineste criteriile":"Nu indeplineste criteriile";
            System.out.println("Evaluare profesor "+profesor.getNume()+":"+evaluareProvesor);
            clonare(student);
            clonare(profesor);
*/
            List<Student> lista = citire();
//            Afisare lista
            afisareLista(lista,"Lista initiala:");
//            Sortarea lista dupa medii
            Collections.sort(lista);
            afisareLista(lista,"Lista studentilor dupa medie:");
//            Sortare dupa nume
            Comparator<Student> comparatorStudenti =new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getNume().compareTo(o2.getNume());
                }
            };
            Collections.sort(lista,comparatorStudenti);
            afisareLista(lista,"Lista studentilor sortata lexicografic:");
            Collections.sort(lista, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getDataNasterii().compareTo(o2.getDataNasterii());
                }
            });
            afisareLista(lista,"Sortarea dupa data nasterii:");
//            Selectie dupa cnp
            long cnp = 6020401587412L;
            Student studentCautat = new Student();
            studentCautat.setCnp(cnp);
            if(lista.contains(studentCautat)){
                Student s = lista.get(lista.indexOf(studentCautat));
                System.out.println("\nStudentul cautat:\n"+s);
            } else {
                System.out.println("Nu am gasit student cu cnp ="+cnp);
            }
//            Tema: cautare student dupa medie prin binarySearch (Collections)
            printareMedii(lista);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private static void afisareLista(List<Student> lista,String mesaj){
        if (lista==null){
            return;
        }
        System.out.println(mesaj);
        for (Student s:lista){
            System.out.println(s);
        }
    }

    private static void clonare(Persoana persoana){
        try {
            if(persoana instanceof Student) {
                Student student=(Student)persoana;
                Student clona = (Student) student.clone();
                student.getNote()[0] = 5;
                System.out.println("\nObiect original:\n" + student);
                System.out.println("\nObiect clona:\n" + clona);
            } else {
                Profesor profesor=(Profesor)persoana;
                Profesor clona = (Profesor)profesor.clone();
                profesor.getPunctaj().setDidactic(10);
                System.out.println("\nObiect original:\n" + profesor);
                System.out.println("\nObiect clona:\n" + clona);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private static List<Student> citire(){
        List<Student> lista=null;
        try(BufferedReader in = new BufferedReader(new FileReader("studenti.csv"))){
            lista = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String linie;
            while ( (linie=in.readLine())!=null ){
                Student student = new Student();
                String[] t = linie.split(",");
                student.setCnp(Long.parseLong(t[0].trim()));
                student.setNume(t[1].trim());
                student.setAdresa(t[2].trim());
                student.setDataNasterii(format.parse(t[3].trim()));
                student.setGrupa(Integer.parseInt(t[4].trim()));
                student.setAnul(Integer.parseInt(t[5].trim()));
                t = in.readLine().split(",");
                int n = t.length/2;
                int[] note = new int[n];
                Discipline[] discipline = new Discipline[n];
                for(int i=0;i<n;i++){
                    discipline[i] = Discipline.valueOf(t[i*2].trim().toUpperCase());
                    note[i] = Integer.parseInt(t[i*2+1].trim());
                }
                student.setNote(note);
                student.setDiscipline(discipline);
                lista.add(student);
//                System.out.println(student);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        return lista;
    }

    private static void printareMedii(List<Student> lista){
        if (lista==null){
            return;
        }
        try(PrintWriter out = new PrintWriter("Medii.csv")){
            DecimalFormat formatMedie = new DecimalFormat("#0.00");
            for (Student s:lista){
                out.println(s.getCnp()+","+s.getNume()+","+
                        s.getGrupa()+","+s.getAnul()+","+ formatMedie.format(s.medie()));
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }
}
