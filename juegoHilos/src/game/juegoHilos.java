package game;

import java.util.Random;

public class juegoHilos extends Thread{

	public static final int META = 100;
	private String name = "";
	private int posicion;
	
	public juegoHilos(String name) {
		this.name = name;
	}
	
	public void run() {
		Random random = new Random();
		while(posicion < META) {
			int avance = random.nextInt(10);
			posicion+=avance;
			
			mostrarPosicion();
			
			try {
				sleep(random.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void mostrarPosicion() {
		StringBuilder hipodromo = new StringBuilder();
		
		for(int i = 0; i < META; i++) {
			if (i == posicion) {
				hipodromo.append(this.name);
			} else {
				hipodromo.append("-");
			}
			
		System.out.println(hipodromo.toString());
		}
	}
	
}
