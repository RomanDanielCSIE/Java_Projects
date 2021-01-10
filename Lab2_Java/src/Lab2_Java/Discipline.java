package Lab2_Java;

public enum Discipline {
    ALGEBRA(5),ANALIZA_MATEMATICA(5),PROBABILITATI(5),POO(6);
    private int credite;

    Discipline(int credite) {
        this.credite = credite;
    }

    public int getCredite() {
        return credite;
    }

    public void setCredite(int credite) {
        this.credite = credite;
    }
}
