package clase_09012025;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ServerNIO {
	
	private static SocketAddress ipCliente;
	
public static void main(String[] args) {
        int port = 8080;

        try {
            Selector selector = Selector.open(); // Crear el selector
            ServerSocketChannel serverChannel = ServerSocketChannel.open(); // Crear el canal del servidor
            serverChannel.bind(new InetSocketAddress(port)); // Asociar al puerto
            serverChannel.configureBlocking(false); // Configurar como no bloqueante
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // Registrar el canal para aceptar conexiones

            System.out.println("Servidor de chat escuchando en el puerto " + port + "...");

            while (true) {
                selector.select(); // Esperar eventos
                Set<SelectionKey> keys = selector.selectedKeys(); // Obtener las claves seleccionadas
                Iterator<SelectionKey> iterator = keys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        // Aceptar nueva conexión
                        SocketChannel clientChannel = serverChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        ipCliente = clientChannel.getRemoteAddress();
                        System.out.println("Cliente conectado: " + ipCliente);
                    } else if (key.isReadable()) {
                        // Leer mensaje del cliente
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(256);
                        int bytesRead = clientChannel.read(buffer);

                        if (bytesRead == -1) {
                            System.out.println("Cliente desconectado: " + clientChannel.getRemoteAddress());
                            clientChannel.close();
                            key.cancel();
                            continue;
                        }

                        buffer.flip();
                        String message = new String(buffer.array(), 0, buffer.limit());
                        System.out.println("Mensaje recibido: " + message.trim());

                        // Difundir el mensaje a otros clientes
                        broadcastMessage(selector, clientChannel, message.trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcastMessage(Selector selector, SocketChannel sender, String message) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != sender) {
                SocketChannel clientChannel = (SocketChannel) channel;
                ByteBuffer buffer = ByteBuffer.wrap((ipCliente + ": " + message + "\n").getBytes());
                clientChannel.write(buffer);
            }
        }
    }
}