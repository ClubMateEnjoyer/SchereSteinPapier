import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameManager {

    //attribute
    private Socket player1;
    private Socket player2;
    Scanner in1;
    PrintWriter out1;
    Scanner in2;
    PrintWriter out2;

    //konstruktor
    public GameManager(Socket player1, Socket player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startGame() {
        try {
            in1 = new Scanner(player1.getInputStream());
            out1 = new PrintWriter(player1.getOutputStream(), true);
            in2 = new Scanner(player2.getInputStream());
            out2 = new PrintWriter(player2.getOutputStream(), true);

            //output bei dem jeweiligen client(spieler)
            out1.println("Wähle: Schere, Stein oder Papier:");
            out2.println("Wähle: Schere, Stein oder Papier:");

            //input des jeweiligen clients(spieler) abfragen
            String movePlayer1 = in1.nextLine().toLowerCase();
            String movePlayer2 = in2.nextLine().toLowerCase();

            String result = decideWinner(movePlayer1, movePlayer2);
            out1.println("Du hast " + movePlayer1 + " gewählt. Gegner hat " + movePlayer2 + " gewählt. " + result);
            out2.println("Du hast " + movePlayer2 + " gewählt. Gegner hat " + movePlayer1 + " gewählt. " + result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            //methoden zum schließen der resourcen wird aufgerufen
            closeResources();
        }
    }

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

    //vergleichen der eingaben und zurückgeben des ergebnisses
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
