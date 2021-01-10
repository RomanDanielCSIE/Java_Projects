package Lab1_Java;

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
                    format.parse("05.05.1968"),"DICE",
                    new Punctaj(20,20,20));
            System.out.println(profesor);
            String evaluareStudent = student.evaluare()?"Promovat":"Nepromovat";
            System.out.println("evaluare student "+student.getNume()+":"+evaluareStudent);
            String evaluareProvesor = profesor.evaluare()?"Indeplineste criteriile":"Nu indeplineste criteriile";
            System.out.println("evaluare profesor "+profesor.getNume()+":"+evaluareProvesor);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }
}
