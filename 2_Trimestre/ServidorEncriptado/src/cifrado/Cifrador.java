package cifrado;
 
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
 
public class Cifrador {
	private String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ" + "0123456789 ";
	private String alfabetoCifrado;
 
	/*
	 * Dada una cadena como * "ABC" y un número (p.ej 2) * devuelve la cadena rotada
	 * a la izq * tantas veces como indique el numero, * en este caso "CAB"
	 */public String rotar(String cad, int numVeces) {
		char[] resultado = new char[cad.length()];
		for (int i = 0; i < cad.length(); i++) {
			int posParaExtraer = (i + numVeces) % cad.length();
			resultado[i] = cad.charAt(posParaExtraer);
		}
		String cadResultado = String.copyValueOf(resultado);
		return cadResultado;
	}
 
	 public String cifrar(String mensaje, String clave) {
         StringBuilder mensajeCifrado = new StringBuilder();
         int desplazamiento = clave.length() % alfabeto.length();
         alfabetoCifrado = rotar(alfabeto, desplazamiento);

         for (char c : mensaje.toUpperCase().toCharArray()) {
             int index = alfabeto.indexOf(c);
             if (index != -1) {
                 mensajeCifrado.append(alfabetoCifrado.charAt(index));
             } else {
                 mensajeCifrado.append(c);
             }
         }
         return mensajeCifrado.toString();
     }

     public String descifrar(String mensajeCifrado, String clave) {
         StringBuilder mensajeDescifrado = new StringBuilder();
         int desplazamiento = clave.length() % alfabeto.length();
         alfabetoCifrado = rotar(alfabeto, desplazamiento);

         for (char c : mensajeCifrado.toUpperCase().toCharArray()) {
             int index = alfabetoCifrado.indexOf(c);
             if (index != -1) {
                 mensajeDescifrado.append(alfabeto.charAt(index));
             } else {
                 mensajeDescifrado.append(c);
             }
         }
         return mensajeDescifrado.toString();
     }
 
	public static void main(String[] args) {
		Cifrador c = new Cifrador();
		String cad = c.rotar("ABCDEFG", 2);
		System.out.println(cad);
		
		String cifrar = c.cifrar("ABCDEFG", "10");
		System.out.println(cifrar);
		
		String descifrar = c.descifrar(cifrar, "10");
		System.out.println(descifrar);
	}
}