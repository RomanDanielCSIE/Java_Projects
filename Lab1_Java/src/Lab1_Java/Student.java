package Lab1_Java;

import java.util.Arrays;
import java.util.Date;

public class Student extends Persoana {
    private int grupa,anul;
    private Discipline[] discipline;
    private int[] note;

    public Student() {
    }

    public Student(String name, String address, Date dateOfBirth,
                   int grupa, int anul, Discipline[] discipline, int[] note) {
        super(name, address, dateOfBirth);
        this.grupa = grupa;
        this.anul = anul;
        this.discipline = discipline;
        this.note = note;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public int getAnul() {
        return anul;
    }

    public void setAnul(int anul) {
        this.anul = anul;
    }

    public Discipline[] getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline[] discipline) {
        this.discipline = discipline;
    }

    public int[] getnNote() {
        return note;
    }

    public void setNote(int[] note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Student{" + grupa + "," + anul + "\n" + Arrays.toString(discipline) +
                "\n" + Arrays.toString(note) +
                "} " + super.toString();
    }

    @Override
    public boolean evaluare() {
        for (int nota:note){
            if (nota<5){
                return false;
            }
        }
        return true;
    }
}
