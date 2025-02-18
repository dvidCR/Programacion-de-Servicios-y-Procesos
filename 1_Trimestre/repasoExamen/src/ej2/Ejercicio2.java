package ej2;

public class Ejercicio2 extends Thread {
	
	private int velocidad;
	private String nombre;
	
	public Ejercicio2(int velocidad, String nombre) {
		this.velocidad = velocidad;
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		for (int i = 1; i < 11; i++) {
			System.out.println(nombre + i);
			try {
				Thread.sleep(velocidad);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
