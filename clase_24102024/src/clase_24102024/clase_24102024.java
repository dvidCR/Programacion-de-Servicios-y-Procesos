package clase_24102024;

public class clase_24102024 extends Thread {
	
	public clase_24102024() {
		
	}
	
	public void run() {
		try {
			sleep(1000);
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
