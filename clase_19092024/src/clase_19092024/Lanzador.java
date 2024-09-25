package clase_19092024;

public class Lanzador {
	
	public void ejecutar(String ruta) {
		ProcessBuilder pb;
		try {
			pb = new ProcessBuilder("java", ruta);
			Process st = pb.start();
			System.out.println(st);
			System.out.println(st.info());
			st.destroy();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}