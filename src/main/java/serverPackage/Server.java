package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int compteurClient = 0;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Serveur démarré sur le port 1234...");

            while (true) {
                Socket socket = serverSocket.accept();
                compteurClient++;

                System.out.println(" Nouveau client connecté");
                System.out.println(" IP : " + socket.getRemoteSocketAddress());
                System.out.println("  Client n°" + compteurClient);

                new Clients(socket, compteurClient).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
