package contador;

public class Main {

	public static void main(String[] args) {
		
		Contador cont1 = new Contador("Hilo1");
		Contador cont2 = new Contador("Hilo2");
		Contador cont3 = new Contador("Hilo3");
		
		Thread hilo1 = new Thread(cont1);
		Thread hilo2 = new Thread(cont2);
		Thread hilo3 = new Thread(cont3);
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		try {
			hilo1.join();
			hilo2.join();
			hilo3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
