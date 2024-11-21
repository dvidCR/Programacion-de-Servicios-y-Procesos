package clase_06112024;

class Filosofo extends Thread {
	
	private int ID;
	private Tenedor tenedorIzquierdo;
	private Tenedor tenedorDerecho;
	
	public Filosofo(int ID, Tenedor tenedorIzquierdo, Tenedor tenedorDerecho) {
		this.ID = ID;
		this.tenedorIzquierdo = tenedorIzquierdo;
		this.tenedorDerecho = tenedorDerecho;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				pensar();
				tomarTenedores();
				comer();
				soltarTenedores();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void pensar() throws InterruptedException {
		System.out.println("El filosofo " + this.ID + " esta pensando");
		Thread.sleep((long) Math.random()*1000);
	}
	
	private void tomarTenedores() {
		System.out.println("El ilosofo " + this.ID + " esta intentando coger los tenedores");
		synchronized (tenedorIzquierdo) {
			System.out.println("El filosofo " + this.ID + " ha cogido el tenedor izquierdo");
			System.out.println("El filosofo " + this.ID + " va a intentar coger el tenedor derecho");
			synchronized (tenedorDerecho) {
				System.out.println("El filosofo " + this.ID + " ha cogido el tenedor derecho");
			}
		}
		
	}
	
	private void comer() throws InterruptedException {
		System.out.println("El filosofo " + this.ID + " esta comiendo");
		Thread.sleep((long) Math.random()*1000);
	}
	
	private void soltarTenedores() {
		System.out.println("El ilosofo " + this.ID + " esta soltando los tenedores");
	}
	
}

class Tenedor {
	
}

public class filosofoDeadLock {
	public static void main(String[] args) {
		Filosofo[] filosofos = new Filosofo[5];
		Tenedor[] tenedores = new Tenedor[5];
		
		for (int i = 0; i < 5; i++) {
			tenedores[i] = new Tenedor();
		}
		
		for (int i = 0; i < 5; i++) {
			Tenedor tenedorIzquierdo = tenedores[i];
			Tenedor tenedorDerecho = tenedores[(i+1)%5];
			
			filosofos[i] = new Filosofo(i, tenedorIzquierdo, tenedorDerecho);
			filosofos[i].start();
		}
	}
}