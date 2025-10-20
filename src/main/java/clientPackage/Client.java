package clientPackage;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Message du serveur : " + in.readLine());
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
