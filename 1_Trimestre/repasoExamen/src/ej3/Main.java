package ej3;

public class Main {

	public static void main(String[] args) {
		
		Ejercicio3 hilo1 = new Ejercicio3(1, 0);
		Ejercicio3 hilo2 = new Ejercicio3(2, 0);
		Ejercicio3 hilo3 = new Ejercicio3(3, 0);
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
	}

}
