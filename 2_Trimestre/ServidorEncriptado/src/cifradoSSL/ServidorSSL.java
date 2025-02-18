package cifradoSSL;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;
 
public class ServidorSSL {
    private static final int PUERTO = 12345;
    private static final String ALMACEN_CLAVES = "./servidor/clavesservidor";
    private static final String CONTRASENA = "123456";
 
    public static void main(String[] args) {
        try {
            // Cargar el almacén de claves
            KeyStore keyStore = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(ALMACEN_CLAVES);
            keyStore.load(fis, CONTRASENA.toCharArray());
 
            // Crear un gestor de claves
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, CONTRASENA.toCharArray());
 
            // Crear el contexto SSL
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
 
            // Crear el servidor seguro
            SSLServerSocketFactory socketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(PUERTO);
 
            System.out.println("Servidor SSL escuchando en el puerto " + PUERTO);
 
            while (true) {
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                System.out.println("Cliente conectado de forma segura.");
 
                // Comunicación con el cliente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
 
                String mensaje = entrada.readLine();
                System.out.println("Mensaje recibido: " + mensaje);
                salida.println("Mensaje recibido correctamente");
 
                socket.close();
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}