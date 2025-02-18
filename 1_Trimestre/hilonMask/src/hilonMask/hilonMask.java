package hilonMask;

public class hilonMask extends Thread {

	public hilonMask(String name) {
		super(name);
	}
	
	public void run() {
		for(int i = 0; i < 30; i++) {
			System.out.println(this.getName() + " el contador " + i);
			try {
				sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
