package Naufragos;

public class Balsa extends Thread {
    private int numPlazas;

    public Balsa() {
        this.numPlazas = 0;
    }

    public Balsa(Runnable runnable, int numPlazas) {
        super(runnable);
        this.numPlazas = numPlazas;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }
}