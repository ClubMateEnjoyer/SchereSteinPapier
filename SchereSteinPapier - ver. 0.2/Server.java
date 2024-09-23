import java.io.*;
import java.net.*;

public class Server {

    //attribute
    private int port; //port des servers
    private ServerSocket serverSocket; 
    private boolean isRunning; //wenn true, server läuft, wenn false, stopt der server

    //konstruktor
    public Server(int port) {
        this.port = port;
        this.isRunning = true;
    }

    public void start() {

        System.out.println("Server startet auf Port: " + port);

        try {  
            serverSocket = new ServerSocket(port); //serversocket initialisieren
            while(isRunning) {
                Socket player1 = serverSocket.accept(); //wartet auf erste verbindung 
                System.out.println("Spieler 1 verbunden."); //verbindung da
                Socket player2 = serverSocket.accept(); //das gleiche nochmal...
                System.out.println("Spieler 2 verbunden."); 
                
                GameManager game = new GameManager(player1, player2, this);//sobald beide spieler verbunden sind, wird das spiel gestartet in der klasse GameManager
                game.startGame();  //methode der klasse "GameManager", spiel startet
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        
        /************************************************************************
        "finally" wird am ende des try-catch-blocks immer ausgeführt
        da dieser so oder so ausgeführt wird, können auch hier fehler auftreten
        deshalb der weitere try-catch-block
        *************************************************************************/
        } finally {
            try {
                serverSocket.close(); //spiel vorbei, server wird geschlossen
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
        server.start(); // --> server startet auf port 6666 
    }
}