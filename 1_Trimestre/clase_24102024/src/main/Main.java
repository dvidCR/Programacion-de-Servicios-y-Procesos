package main;

import clase_24102024.clase_24102024;
import clase_24102024.repasoHilos;

public abstract class Main implements Runnable {

	public static void main(String[] args) throws InterruptedException {
//		repasoHilos rh = new repasoHilos("Hola buenas");
//		
//		rh.run();
		
		Thread h1 = new Thread(new clase_24102024());
		h1.start();
		System.out.println("Estado despues del start " + h1.getState());
		Thread.sleep(500);
		System.out.println("Estado despues del sleep " + h1.getState());
		Thread.sleep(1000);
		System.out.println("Estado despues del wait " + h1.getState());
		h1.join();
		System.out.println("Estado terminando" + h1.getState());
	}

}
