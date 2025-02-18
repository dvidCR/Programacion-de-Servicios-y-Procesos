package contador;

public class Contador implements Runnable {
	
	private static int contador = 0;
	private String nombre;
	
	public Contador(String nombre) {
		this.nombre = nombre;
	}
	
	public synchronized void sumar() {
		contador++;
		System.out.println(nombre + ": " + contador);
	}
	
	@Override
	public void run() {
		while(contador < 100) {
			sumar();
		}
		
	}
	
}
