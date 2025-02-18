package mian;

import game.juegoHilos;

public class Main {

	public static void main(String[] args) {
		juegoHilos caballo1 = new juegoHilos("C1");
		juegoHilos caballo2 = new juegoHilos("C2");
		
		caballo1.start();
		caballo2.start();

	}

}
