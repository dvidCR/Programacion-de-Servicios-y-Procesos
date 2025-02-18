package ej3;

public class Ejercicio3 extends Thread {

    private int nombre;
    private int posicion;
    private static boolean ganador = false;

    public Ejercicio3(int nombre, int posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public synchronized void pista() {
        for (int i = 0; i < 11; i++) {
            if (i == posicion) {
                System.out.print(".");
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
    }

    @Override
    public void run() {
        for (int meta = 11; posicion < meta; ) {
            posicion++;
            pista();
            if (posicion == 10 && !ganador) {
                synchronized (Ejercicio3.class) {
                    if (!ganador) {
                        ganador = true;
                        System.out.println("Hilo " + nombre + " ha ganado");
                    }
                }
                break;
            }
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
