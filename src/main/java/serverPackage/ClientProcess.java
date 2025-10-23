package serverPackage;
import java.io.*;
import java.net.Socket;
public class ClientProcess extends Thread {
    private Socket socket;
    private int clientNumber;
    public ClientProcess(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }
    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("vous êtes le client n° " + clientNumber);
            String ligne;
            while ((ligne = in.readLine()) != null) {
                String nombre1 = "";
                String nombre2 = "";
                char op = 0;
                int i = 0;
                while (i < ligne.length() && Character.isDigit(ligne.charAt(i))) {
                    nombre1 += ligne.charAt(i);
                    i++;
                }
                if (i < ligne.length()) {
                    op = ligne.charAt(i);
                    i++;
                } else {
                    out.println("Erreur : opérateur manquant");
                    continue;
                }
                while (i < ligne.length() && Character.isDigit(ligne.charAt(i))) {
                    nombre2 += ligne.charAt(i);
                    i++;
                }
                if (nombre1.isEmpty() || nombre2.isEmpty()) {
                    out.println("Erreur : nombre manquant");
                    continue;
                }
                int a = Integer.parseInt(nombre1);
                int b = Integer.parseInt(nombre2);
                double resultat = 0;

                switch (op) {
                    case '+': resultat = a + b; break;
                    case '-': resultat = a - b; break;
                    case '*': resultat = a * b; break;
                    case '/':
                        if (b != 0) resultat = (double) a / b;
                        else {
                            out.println("Erreur : division par zéro");
                            continue;
                        }
                        break;
                    default:
                        out.println("Erreur : opérateur inconnu");
                        continue;
                }

                Server.incrementerCompteur();

                out.println("Résultat : " + resultat);
            }
        } catch (IOException e) {
            System.out.println("Client n°" + clientNumber + " déconnecté.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
