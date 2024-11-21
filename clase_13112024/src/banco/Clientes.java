package banco;

public class Clientes  extends Thread {
	
	Banco banco = null;
	
	public Clientes(Banco bank) {
		this.banco = bank;
	}
	
	@Override
	public void run() {
		System.out.println("El cliente esta comprando...");
		banco.compra(300);
	}
}
