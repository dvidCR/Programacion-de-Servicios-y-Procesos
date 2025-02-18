package hilonMask;

public class hilonMuskRunnable implements Runnable {

	private Thread title;

	public hilonMuskRunnable() {
		this.title = new Thread(this, "Tesla");
	}
	
	public void run() {
		for(int i = 0; i < 30; i++) {
			System.out.println(this.title.getName() + " el contador " + i);
		}
	}
	
	public void ejecutarHilo() {
		this.title.start();
	}

}