package ej1;

public class Ejercicio1 extends Thread {
	
	String mensaje;
	
	public Ejercicio1(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(mensaje + (i + 1) + " vez");
			try {
				Thread.sleep((long) Math.random() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
