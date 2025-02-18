package main;

import hilonMask.*;

public class Main {
	
	public static void main(String[] args) {
//		hilonMask miniHilonMask = new hilonMask("Tesla");
//		hilonMask choeteHilonMask = new hilonMask("SpaceX");
//		
//		miniHilonMask.setPriority(Thread.MIN_PRIORITY);
//		choeteHilonMask.setPriority(Thread.MAX_PRIORITY);
//		
//		miniHilonMask.start();
//		choeteHilonMask.start();
//		
		hilonMuskRunnable hilonMaskRunnable = new hilonMuskRunnable();
		
		hilonMaskRunnable.ejecutarHilo();
		hilonMaskRunnable.run();
	}
}
