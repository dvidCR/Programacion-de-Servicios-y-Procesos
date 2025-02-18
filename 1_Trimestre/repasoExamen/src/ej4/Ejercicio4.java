package ej4;

public class Ejercicio4 implements Runnable {
    private int saldo = 10000;
    private String nombre;
    private int cantidad;

    public Ejercicio4(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public synchronized void anadir() {
        saldo += cantidad;
        System.out.println(nombre + " ha añadido " + cantidad + "€");
        System.out.println("Saldo actual: " + saldo);
    }

    public synchronized void retirar() {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println(nombre + " ha retirado " + cantidad + "€");
        } else {
            System.out.println(nombre + " intentó retirar más dinero del disponible.");
        }
        System.out.println("Saldo actual: " + saldo);
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            retirar();

            Thread.sleep(1000);
            anadir();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
