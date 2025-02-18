package clase_30102024;

public class ProductorConsumidorDemo {
    public static void main(String[] args) {
        Cola cola = new Cola(5);
        Thread productorThread = new Thread(new Productor(cola));
        Thread consumidorThread = new Thread(new Consumidor(cola));

        productorThread.start();
        consumidorThread.start();
    }
}