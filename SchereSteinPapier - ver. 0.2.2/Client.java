import java.io.*; // Ein- und Ausgabe (Input/Output)
import java.net.*; // Für Netzwerk-Funktionen, wie Sockets
import java.util.Scanner;

public class Client {

    // Attribute
    private String host; // Hostname bzw. IP des Host als String
    private int port; // Der Port, über den der Client mit dem Server spricht
    Socket socket; // Socket-Objekt für Serververbindung
    Scanner in; // Daten vom Server lesen    
    PrintWriter out; // Daten an Server senden
    Scanner userInput; // Erhalten des Inputs vom Client im Terminal

    // Konstruktor mit Hostname und Port des Servers
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            socket = new Socket(host, port); // Verbindung zum Server
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true); 
            userInput = new Scanner(System.in); // Eingabe des Spielers im Terminal

            System.out.println("Verbunden mit Server auf Port: " + port);

            // Nach der erfolgreichen Verbindung --> Nachricht vom Server empfangen
            System.out.println(in.nextLine());
            
            
            // Spieler wird aufgefordert Eingabe zu tätigen
            String playerInput = userInput.nextLine(); 
            out.println(playerInput); // -> An Server senden
            
            // Ergebnis vom Server ausgeben
            System.out.println(in.nextLine());
            
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            // Wenn fertig, schließe alles in der Methode "closeResources()"
            closeResources();
        }
    }

    // Methode für Resourcen schließen
    private void closeResources() {
        try {
            socket.close();
            in.close();
            out.close();
            userInput.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        //Scanner fürs abfragen der IP und des Ports
        Scanner scanner = new Scanner(System.in); 

        System.out.println("Host Name oder IP eingeben:");
        String hostname = scanner.nextLine(); 

        System.out.println("Port eingeben:");
        int portNum = scanner.nextInt(); 

        Client player = new Client(hostname, portNum);
        player.start();

        // Scanner schließen
        scanner.close();
    }
}
