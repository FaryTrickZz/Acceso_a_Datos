package Naufragos;

public class PruebaRescate implements Runnable {

    private Naufrago nf = new Naufrago((int) (Math.random() * (1000 - 800) + 800));

    public static void main(String[] args) {
        PruebaRescate pruebaRescateRunnable = new PruebaRescate();

        Balsa balsa1 = new Balsa(pruebaRescateRunnable, (int) (Math.random() * (40 - 20) + 20));
        Balsa balsa2 = new Balsa(pruebaRescateRunnable, (int) (Math.random() * (50 - 30) + 30));
        Balsa balsa3 = new Balsa(pruebaRescateRunnable, (int) (Math.random() * (60 - 40) + 40));

        balsa1.setName("Balsa 1");
        balsa2.setName("Balsa 2");
        balsa3.setName("Balsa 3");

        balsa1.start();
        balsa2.start();
        balsa3.start();
    }

    /*
     * este método sólo se ejecuta mientras queden náufragos, llama al siguiente método
     * y además almacena la cantidad de plazas para cada balsa.
     */
    @Override
    public void run() {
        while(nf.getCantidadNaufragos() >= 0) {

            // A cada balsa le asignamos un numero de plazas para los rescates
            int numeroPlazas = ((Balsa) Thread.currentThread()).getNumPlazas();

            rescatar(numeroPlazas);
        }
    }

    /*
     * método sincronizado para evitar problemas de concurrencia si las 3 balsas
     * intentan rescatar a la vez a los náufragos, con esto sólo permitimos el pasado
     * a una de ellas para que el número de náufragos restante se actualice correctamente.
     */
    private synchronized void rescatar(int cantidadPlazas) {
        try{
            if(nf.getCantidadNaufragos()>=0){
                // mostramos el número de náufragos restantes
                System.out.println("\nNaufragos restantes " + nf.getCantidadNaufragos());

                // mostramos la balsa actual y la cantidad de plazas de esta
                System.out.println("La balsa " + Thread.currentThread().getName() + " tiene capacidad para " + cantidadPlazas);

                //Cada balsa tardará un segundo en recgoer los nauffragos
                Thread.sleep((1000));

                // actualizamos el número de náufragos
                nf.rescatesRestantes(cantidadPlazas);
            }
        } catch(Exception e) {
                e.printStackTrace();

            }
        }
    }

