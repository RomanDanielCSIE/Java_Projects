package Lab2_Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        try {
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
        }
        catch (Exception ex){
            System.err.println(ex);
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

    private static void citire(){
        try(BufferedReader in = new BufferedReader(new FileReader("students.csv"))){
            String linie;
            while ( (linie=in.readLine())!=null ){
                String[] t = linie.split(",");

            }

        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

}
