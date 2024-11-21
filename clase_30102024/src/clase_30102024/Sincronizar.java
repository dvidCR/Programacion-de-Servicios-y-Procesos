package clase_30102024;

import java.util.concurrent.atomic.AtomicInteger;

public class Sincronizar implements Runnable {
//	private int contador = 0;
    private AtomicInteger contador = new AtomicInteger(0); // Esto es un contador sincronizado

    
    /*
     * Se sincronizan ambos hilos y empiezan a sumar el contador a la par
     */
    public void incrementar() {
	     contador.incrementAndGet();
    }
    
    
//    public void incrementar() {
//    	synchronized(this) {
//    		contador++;
//    	}
//    }
    
//    public synchronized void incrementar() {
//        contador++;
//    }
    
//    public void incrementar() {
//        contador++;
//    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementar();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sincronizar cc = new Sincronizar();
        Thread hilo1 = new Thread(cc);
        Thread hilo2 = new Thread(cc);

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println("Valor final del contador: " + cc.contador);
    }
}

