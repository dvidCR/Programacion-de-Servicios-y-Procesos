package cifradoSSL;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;
 
public class ClienteSSL {
    private static final String ALMACEN_CLAVES = "./servidorMario/clavescliente";
    private static final String CONTRASENA = "123456";
    private static final String SERVIDOR = "192.168.11.249";
    private static final int PUERTO = 12345;
 
    public static void main(String[] args) {
        try {
            // Cargar el almacén de claves del cliente
            KeyStore keyStore = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(ALMACEN_CLAVES);
            keyStore.load(fis, CONTRASENA.toCharArray());
 
            // Crear el gestor de confianza
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
 
            // Crear el contexto SSL
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
 
            // Crear socket SSL
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            SSLSocket socket = (SSLSocket) socketFactory.createSocket(SERVIDOR, PUERTO);
 
            System.out.println("Conexión segura establecida con el servidor.");
 
            // Comunicación con el servidor
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
            salida.println("Hola, servidor seguro!");
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);
 
            socket.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}