# SchereSteinPapier
Dieses Projekt ist ein simples Schere-Stein-Papier-Spiel in Java, welches mittels Sockets eine Server-Client-Verbindung herstellt. Somit kann dieses Spiel per Verbindung von 2 Personen an unterschiedlichen Rechnern gegeneinander gespielt werden. Es soll die Grundlagen des Networkings in Java zeigen, und somit ist meine Implementierung auf den Multiplayer im lokalen Netzwerk beschrängt. Ich gehe in der README später noch kurz drauf ein, wie man es zu einem "vollständigem" Onlinespiel implementieren kann.

## Version 0.1
Die erste funktionstüchtige Version. Sie beinhaltet 3 Java Klassen, Server, GameManager und Client. 

#### Server.java
Der Server akzeptiert die Verbindung von 2 Spieler und startet den Spielablauf in der Klasse GameManager. So wird in der main ein Server auf dem gegebenem Port, in diesem Fall die 6666.

Mit der Methode "accept()" wird auf eine Verbindung für den aktuellen Port gewartet. Bis diese nicht erstellt wurde, geht es im Code auch nicht weiter. Sind beide Verbindungen erstellt, sprich beide Spieler verbunden, werden die beiden Socket Objekte "player1" und "player2" in eine neue Instanz der Klasse GameManager übergeben. Mit diesen beiden wird nun im GameManager die Funktion "gameStart()" aufgerufen, welche genau das macht, was Name sagt. Ist das Spiel vorbei, wird der Serversocket geschlossen.

#### Client.java
Der Client ist die Klasse des Spielers, das Gegenstück zum Server. Diese ermöglicht es dem Spieler Eingaben zu tätigen und die Verbindung herzustellen.

Mit der Klasse _Socket_ wird die Verbindung zum Server per Hostname/IP und dem Port hergestellt. Dirch die beiden Methoden ".getInputStream()" und ".getOutputStream()" kriegt der Client bzw. Spieler die Möglichkeit, Daten zum Server zu senden (Output) und zu erhalten (Input). Der Input des Servers kann bei Client angezeigt werden, zb. durch "System.out.println(in.nextLine());" Am Ende werden auch hier alle genutzten Resourcen geschlossen und stehen wieder zu verfügung.

#### GameManager.java
Der GameManager verwaltet das eigentliche Spiel. Auch hier werden wieder Output- und Inputstreams verwendet, in1 und out1 für player1 und das gleiche für player2. Es werden die Moves abgefragt und das Ergebnis als String ausgegeben. Die Methode "decideWinner()" gibt also nru einen String zurück, es werden Scores in Form von Punkten oder vergleichbaren Gespeichert oder vergeben. Wie immer werden auch hier die verwendeten Resourcen geschlossen.





