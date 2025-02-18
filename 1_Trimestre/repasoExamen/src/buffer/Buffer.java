package buffer;

public class Buffer {
	
	private Integer value = null; // El valor almacenado en el buffer. Nulo si está vacío.

    // Método para que el productor ponga un valor en el buffer.
    public synchronized void put(int newValue) throws InterruptedException {
        while (value != null) { // Espera si el buffer ya tiene un valor.
            wait();
        }
        value = newValue;
        System.out.println("Productor produjo: " + value);
        notifyAll(); // Notifica a los hilos que están esperando.
    }

    // Método para que el consumidor obtenga el valor del buffer.
    public synchronized int get() throws InterruptedException {
        while (value == null) { // Espera si el buffer está vacío.
            wait();
        }
        int result = value;
        value = null; // Vacía el buffer.
        System.out.println("Consumidor consumió: " + result);
        notifyAll(); // Notifica a los hilos que están esperando.
        return result;
    }
}
