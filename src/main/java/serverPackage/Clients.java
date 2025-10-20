package serverPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Clients extends Thread {
    private int compteurClient;
    private Socket socket;

    public Clients(Socket socket, int compteurClient) {
        this.socket = socket;
        this.compteurClient = compteurClient;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("vous êtes le client n° " + compteurClient);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
