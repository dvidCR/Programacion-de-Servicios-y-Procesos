package buffer;

class Consumidor extends Thread {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) { // Consume 10 valores.
                buffer.get();
                Thread.sleep(1000); // Simula tiempo de consumo.
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}