package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int compteurClient = 0;
    private static int compteurOperations = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur calculatrice multi-thread démarré sur le port 1234...");

            while (true) { // Boucle infinie
                Socket socket = serverSocket.accept();
                compteurClient++;
                System.out.println("Nouveau client connecté");
                System.out.println(" IP : " + socket.getRemoteSocketAddress());
                System.out.println(" Client n°" + compteurClient);

                new ClientProcess(socket, compteurClient).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void incrementerCompteur() {
        compteurOperations++;
        System.out.println("Compteur global opérations : " + compteurOperations);
    }
}
