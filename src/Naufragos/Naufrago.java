package Naufragos;

public class Naufrago {
    private int cantidadNaufragos = 0;

    public int getCantidadNaufragos() {
        return cantidadNaufragos;
    }

    public void setCantidadNaufragos(int cantidadNaufragos) {
        this.cantidadNaufragos = cantidadNaufragos;
    }

    public Naufrago(){

    }

    public Naufrago(int cantidadNaufragos){
        this.cantidadNaufragos = cantidadNaufragos;
    }

    // método para actualizar el número de náufragos
    public void rescatesRestantes(int rescatados){
        cantidadNaufragos -= rescatados;


    }
}