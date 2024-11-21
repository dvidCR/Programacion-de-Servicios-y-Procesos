package clase_06112024;


class FilosofoCena extends Thread {
    private int id;
    private Tenedor tenedorIzquierdo;
    private Tenedor tenedorDerecho;

    public FilosofoCena(int id, Tenedor tenedorIzquierdo, Tenedor tenedorDerecho) {
        this.id = id;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
    }

    public FilosofoCena(int i, Tenedores tenedorIzquierdo2, Tenedores tenedorDerecho2) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void run() {
        try {
            while (true) {
                pensar();
                if (id % 2 == 0) {
                    // Filósofos pares primero toman el tenedor izquierdo
                    tomarTenedores(tenedorIzquierdo, tenedorDerecho);
                } else {
                    // Filósofos impares primero toman el tenedor derecho
                    tomarTenedores(tenedorDerecho, tenedorIzquierdo);
                }
                comer();
                soltarTenedores();
            }
        } catch (InterruptedException e) {
            System.out.println("Filósofo " + id + " ha sido interrumpido.");
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void tomarTenedores(Tenedor primero, Tenedor segundo) {
        synchronized (primero) {
            System.out.println("Filósofo " + id + " tomó el primer tenedor.");
            synchronized (segundo) {
                System.out.println("Filósofo " + id + " tomó el segundo tenedor.");
            }
        }
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comiendo.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void soltarTenedores() {
        System.out.println("Filósofo " + id + " soltó ambos tenedores.");
    }
}

class Tenedores {}

public class filosofoDeadLock_Bien {
    public static void main(String[] args) {
        int numeroFilosofos = 5;
        FilosofoCena[] filosofos = new FilosofoCena[numeroFilosofos];
        Tenedores[] tenedores = new Tenedores[numeroFilosofos];

        for (int i = 0; i < numeroFilosofos; i++) {
            tenedores[i] = new Tenedores();
        }

        for (int i = 0; i < numeroFilosofos; i++) {
        	Tenedores tenedorIzquierdo = tenedores[i];
        	Tenedores tenedorDerecho = tenedores[(i + 1) % numeroFilosofos];

            filosofos[i] = new FilosofoCena(i, tenedorIzquierdo, tenedorDerecho);
            filosofos[i].start();
        }
    }
}