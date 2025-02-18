package ej4;

public class Main {

	public static void main(String[] args) {
		
		String u1 = "Usuario1";
		int dU1 = 500;
		
		String u2 = "Usuario2";
		int dU2 = 3000;
		
		String u3 = "Usuario 3";
		int dU3 = 10;
		
		Ejercicio4 hilo1 = new Ejercicio4(u1, dU1);
		Ejercicio4 hilo2 = new Ejercicio4(u2, dU2);
		Ejercicio4 hilo3 = new Ejercicio4(u3, dU3);		
		
		Thread t1 = new Thread(hilo1);
		Thread t2 = new Thread(hilo2);
		Thread t3 = new Thread(hilo3);
		
		t1.start();
		t2.start();
		t3.start();
	}

}
