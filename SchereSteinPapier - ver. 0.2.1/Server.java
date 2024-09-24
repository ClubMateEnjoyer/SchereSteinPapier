import java.io.*; // Ein- und Ausgabe (Input/Output)      
import java.net.*; // Für Netzwerk-Funktionen, wie Sockets

public class Server {

    // Attribute
    private int port; // Port, auf dem der Server auf Verbindungen wartet
    private ServerSocket serverSocket; 
    private boolean isRunning; // Wenn true --> Server läuft, wenn false --> Server stopt

    // Konstruktor
    public Server(int port) {
        this.port = port; 
        this.isRunning = true; // Der Server wird beim Start als "isRunning" gesetzt
    }

    public void start() {

        System.out.println("Server startet auf Port: " + port); // Info, dass der Server auf dem Port XY startet

        try {  
            serverSocket = new ServerSocket(port); //serversocket initialisieren
            while(isRunning) {

                // Server wartet auf die Verbindung des ersten Clients bzw Spielers
                Socket player1 = serverSocket.accept(); // Blockiert das Programm bis eine Verbindung erstellt wurde
                System.out.println("Spieler 1 verbunden."); //verbindung da

                // Das gleiche nochmal...
                Socket player2 = serverSocket.accept();
                System.out.println("Spieler 2 verbunden."); 
                
                // Beide Clients bzw Spieler verbunden --> neue Instanz der GameManager Klasse mit den beiden Spielern
                GameManager game = new GameManager(player1, player2, this);
                game.startGame();  // Methode der Klasse "GameManager", spiel startet
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        
        /************************************************************************
        "finally" wird am ende des try-catch-blocks immer ausgeführt, egal
        ob der "try" Block oder der "catch" Block ausgeführt wurde.
        Hier ist es in jedem Fall wichtig, alle Resourcen wieder zu schließen.
        *************************************************************************/
        } finally {
            try {
                serverSocket.close(); // Spiel vorbei, Server wird geschlossen
                System.out.println("Spiel vorbei!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void stopServer() {
        this.isRunning = false;
    }


    public static void main(String[] args) {
        Server server = new Server(6666); 
        server.start(); // --> Server startet auf Port 6666 
    }
}