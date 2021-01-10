package Lab4_Java;

public class Punctaj implements Cloneable{
    private int didactic, cercetare, vizibilitate;

    public Punctaj() {
    }

    public Punctaj(int didactic, int cercetare, int vizibilitate) {
        this.didactic = didactic;
        this.cercetare = cercetare;
        this.vizibilitate = vizibilitate;
    }

    public int getDidactic() {
        return didactic;
    }

    public void setDidactic(int didactic) {
        this.didactic = didactic;
    }

    public int getCercetare() {
        return cercetare;
    }

    public void setCercetare(int cercetare) {
        this.cercetare = cercetare;
    }

    public int getVizibilitate() {
        return vizibilitate;
    }

    public void setVizibilitate(int vizibilitate) {
        this.vizibilitate = vizibilitate;
    }

    @Override
    public String toString() {
        return "Punctaj{" +didactic +
                "," + cercetare +
                "," + vizibilitate +
                '}';
    }

    public boolean evaluat(){
        return (didactic>20)&&(cercetare>10)&&(vizibilitate>5)?true:false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
