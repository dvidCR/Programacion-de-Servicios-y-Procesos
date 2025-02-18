package buffer;

class Productor extends Thread {
    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) { // Produce 10 valores.
                buffer.put(i);
                Thread.sleep(500); // Simula tiempo de producción.
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}