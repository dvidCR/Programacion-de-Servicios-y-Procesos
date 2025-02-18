package cadenaDeCaracteres;

public class Cadena {
	
	private static String abecedario = "abcdef";
	
	public String generar(String texto, int num) {
		StringBuffer cadenaCifrada = new StringBuffer();
		
		for (int i = 0; i < texto.length(); i++) {
			int pos = abecedario.indexOf(texto.charAt(i));
			if (pos != 0) {
				cadenaCifrada.append(abecedario.charAt(pos - num));				
			} else {
				cadenaCifrada.append(abecedario.charAt(abecedario.length() - num));		
			}
		}
		
		return cadenaCifrada.toString();
	}
}
