package banco;

public class Banco extends Thread {
	
	// Recurso compartido
	int saldo = 600;
	
	public Banco() {
		
	}
	
	public synchronized void compra(int precio) {
		if (this.saldo > 0) {
			System.out.println("Estan tramitando compra...");
			this.saldo = saldo - precio;
			System.out.println("El saldo es: " + this.saldo);
		}
	}
	
	public static void main(String[] args) {
		Banco bbva = new Banco();
		Clientes cliente1 = new Clientes(bbva);
		Clientes cliente2 = new Clientes(bbva);
		Clientes cliente3 = new Clientes(bbva);
		
		cliente1.start();
		cliente2.start();
		cliente3.start();
	}
}