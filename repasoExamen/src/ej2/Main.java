package ej2;

public class Main {

	public static void main(String[] args) {
		int v1 = 500;
		int v2 = 1000;
		int v3 = 2000;
		
		Ejercicio2 hilo1 = new Ejercicio2(v1, "Hilo1 ");
		Ejercicio2 hilo2 = new Ejercicio2(v2, "Hilo2 ");
		Ejercicio2 hilo3 = new Ejercicio2(v3, "Hilo3 ");
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
	}

}
