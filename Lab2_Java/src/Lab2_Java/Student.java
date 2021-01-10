package Lab2_Java;

import java.util.Arrays;
import java.util.Date;

public class Student extends Persoana implements Cloneable {
    private int grupa,anul;
    private Discipline[] discipline;
    private int[] note;

    public Student() {
    }

    public Student(String nume, String adresa, Date dataNasterii, long cnp,
                   int grupa, int anul, Discipline[] discipline, int[] note) throws Exception {
        super(nume, adresa, dataNasterii,cnp);
        this.grupa = grupa;
        if(anul<1||anul>3){
            throw new Exception("An invalid!");
        }
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

    public void setAnul(int anul) throws Exception {
        if(anul<1||anul>3){
            throw new Exception("An invalid!");
        }
        this.anul = anul;
    }

    public Discipline[] getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline[] discipline) {
        this.discipline = discipline;
    }

    public int[] getNote() {
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

    @Override
    public double medie() {
        double m=0;
        for(int v:note){
            m+=v;
        }
        return m/note.length;
    }

    @Override
    public int compareTo(Persoana o) {
        if(medie()<o.medie()){
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student clona = (Student) super.clone();
        int[] note = new int[this.note.length];
        for (int i=0;i<note.length;i++){
            note[i] = this.note[i];
        }
        clona.setNote(note);
        clona.setDiscipline(Arrays.copyOf(this.discipline,this.discipline.length));
        return clona;
    }
}
