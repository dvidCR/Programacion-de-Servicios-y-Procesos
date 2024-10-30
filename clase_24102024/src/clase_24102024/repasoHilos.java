package clase_24102024;

public class repasoHilos extends Thread{
	
	public repasoHilos(String name) {
		super(name);
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.getName() + " se ha repetido " + i + " vez/ces");
			try {
				sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
