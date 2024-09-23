import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    //attribute
    private String host; //hostname/ip des servers
    private int port; //der auf dem server verwendete port
    Socket socket; //socket für serververbindung
    Scanner in; //input vom server    
    PrintWriter out; //output des clients an den server
    Scanner userInput; //erhalten des inputs vom client

    //konstruktor
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            socket = new Socket(host, port); 
            in = new Scanner(socket.getInputStream()); //vom server
            out = new PrintWriter(socket.getOutputStream(), true); //zum server 
            userInput = new Scanner(System.in); //eingabe des spielers

            System.out.println("Verbunden mit Server auf Port: " + port);

            //nachricht vom server anzeigen
            System.out.println(in.nextLine());
            
            
            //move eingeben
            String spielerEingabe = userInput.nextLine(); 
            out.println(spielerEingabe); //an server senden
            
            //ergebnis vom server erhalten und ausgeben
            System.out.println(in.nextLine());
            
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            //wenn fertig, schließe alles in der methode "closeResources()"
            closeResources();
        }
    }

    //methode für resourcen schließen
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
        Client player = new Client("localhost", 6666);
        player.start();
    }
}
