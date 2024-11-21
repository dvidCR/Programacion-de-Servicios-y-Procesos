package clase_20112024;

public class crearHilo extends Thread {
	
	@Override
	public void run() {
	}

}

class Main {
	public static void main(String[] args) {
		crearHilo ch = new crearHilo();
		ch.start();
	}
}
