import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameManager {

    // Attribute
    private Socket player1;
    private Socket player2;
    private Server server; // Referenz zum Server, um die methode "stopServer()" aufrufen zu können, eine Methode der Server Klasse

    // Input und Output für die beiden Spieler
    Scanner in1;
    PrintWriter out1;
    Scanner in2;
    PrintWriter out2;

    // Konstruktor
    public GameManager(Socket player1, Socket player2, Server server) {
        this.player1 = player1;
        this.player2 = player2;
        this.server = server;
    }

    public void startGame() {
        try {
            in1 = new Scanner(player1.getInputStream());
            out1 = new PrintWriter(player1.getOutputStream(), true);
            in2 = new Scanner(player2.getInputStream());
            out2 = new PrintWriter(player2.getOutputStream(), true);

            // Output bei dem jeweiligen Client(Spieler)
            out1.println("Wähle: Schere, Stein oder Papier (tippe 'ende' um Spiel abzubrechen):");
            out2.println("Wähle: Schere, Stein oder Papier (tippe 'ende' um Spiel abzubrechen):");

            // Input des jeweiligen Clients(Spieler) abfragen
            String movePlayer1 = in1.nextLine().toLowerCase();
            String movePlayer2 = in2.nextLine().toLowerCase();

            // Wenn einer der moves "ende" ist, bricht das spiel ab
            if (movePlayer1.equals("ende") || movePlayer2.equals("ende")) {
                out1.println("Ein Spieler hat das Spiel beendet.");
                out2.println("Ein Spieler hat das Spiel beendet.");
                server.stopServer(); // Server stoppen, ohne die Spiellogik "decideWinner" aufzurufen.
            } else {
                String result = decideWinner(movePlayer1, movePlayer2);
                out1.println("Du hast " + movePlayer1 + " gewählt. Gegner hat " + movePlayer2 + " gewählt. " + result);
                out2.println("Du hast " + movePlayer2 + " gewählt. Gegner hat " + movePlayer1 + " gewählt. " + result);

                // Spiel vorbei, server stoppen
                server.stopServer();
            }
            

            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            // Methoden zum schließen der Resourcen wird aufgerufen
            closeResources();
        }
    }

    // Methode für Resourcen schließen
    private void closeResources() {
        try {
            in1.close();
            out1.close();
            in2.close();
            out2.close();
            player1.close();
            player2.close();            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Vergleichen der Eingaben und Zurückgeben des Ergebnisses als String
    private String decideWinner(String movePlayer1, String movePlayer2) {
        if (movePlayer1.equals(movePlayer2)) {
            return "Unentschieden!";
        }
        if (movePlayer1.equals("schere")) {
            if (movePlayer2.equals("papier")) {
                return "Spieler 1 gewinnt!";
            } else {
                return "Spieler 2 gewinnt!";
            }
        } else if (movePlayer1.equals("stein")) {
            if (movePlayer2.equals("schere")) {
                return "Spieler 1 gewinnt!";
            } else {
                return "Spieler 2 gewinnt!";
            }
        } else if (movePlayer1.equals("papier")) {
            if (movePlayer2.equals("stein")) {
                return "Spieler 1 gewinnt!";
            } else {
                return "Spieler 2 gewinnt!";
            }
        } else {
            return "Ungültige Eingabe";
        }        
    }
}
