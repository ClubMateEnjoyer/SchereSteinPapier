# SchereSteinPapier
Dieses Projekt ist ein simples Schere-Stein-Papier-Spiel in Java, welches mittels Sockets eine Server-Client-Verbindung herstellt. Somit kann dieses Spiel per Verbindung von 2 Personen an unterschiedlichen Rechnern gegeneinander gespielt werden. Es soll die Grundlagen des Networkings in Java zeigen, und somit ist meine Implementierung auf den Multiplayer im lokalen Netzwerk beschrängt. Man könnte den Server auch per Cloud-Service hosten und somit zu einem "vollwertigem" Online-Spiel umfunktionieren, darauf wurde hier aber verzichtet.

## Version 0.1
Die erste funktionstüchtige Version. Sie beinhaltet 3 Java Klassen, Server, GameManager und Client. 

#### Server.java
Der Server akzeptiert die Verbindung von 2 Spieler und startet den Spielablauf in der Klasse GameManager. So wird in der main ein Server auf dem gegebenem Port, in diesem Fall die 6666.

Mit der Methode "accept()" wird auf eine Verbindung für den aktuellen Socket gewartet. Bis diese nicht erstellt wurde, geht es im Code auch nicht weiter. Sind beide Verbindungen erstellt, sprich beide Spieler verbunden, werden die beiden Socket Objekte "player1" und "player2" in eine neue Instanz der Klasse GameManager übergeben. Mit diesen beiden wird nun im GameManager die Funktion "gameStart()" aufgerufen, welche genau das macht, was Name sagt. Ist das Spiel vorbei, wird der Serversocket geschlossen.

#### Client.java
Der Client ist die Klasse des Spielers, das Gegenstück zum Server. Diese ermöglicht es dem Spieler Eingaben zu tätigen und die Verbindung herzustellen.

Mit der Klasse _Socket_ wird die Verbindung zum Server per Hostname/IP und dem Port hergestellt. Dirch die beiden Methoden ".getInputStream()" und ".getOutputStream()" kriegt der Client bzw. Spieler die Möglichkeit, Daten zum Server zu senden (Output) und zu erhalten (Input). Der Input des Servers kann bei Client angezeigt werden, zb. durch "System.out.println(in.nextLine());" Am Ende werden auch hier alle genutzten Resourcen geschlossen und stehen wieder zu verfügung.

#### GameManager.java
Der GameManager verwaltet das eigentliche Spiel. Auch hier werden wieder Output- und Inputstreams verwendet, in1 und out1 für player1 und das gleiche für player2. Es werden die Moves abgefragt und das Ergebnis als String ausgegeben. Die Methode "decideWinner()" gibt also nru einen String zurück, es werden Scores in Form von Punkten oder vergleichbaren Gespeichert oder vergeben. Wie immer werden auch hier die verwendeten Resourcen geschlossen.

## Zusammenfassung
Es gibt die 3 Klassen Server, Client und GameManger. Der Server akzeptiert genau 2 Spieler, startet das Spiel im Spielmanager, dieser gibt das Ergebnis als String zurück und alle Verbindungen und Resourcen werden wieder geschlossen.

## Demonstration per Video
Relativ unprofessionel, aber ich hoffe es ist alles deutlich :) <br\>
https://youtu.be/uibVCJJv2Us


### Geplante Verbesserungen / Änderungen
* ~~Möglichkeit für Spieler, das Spiel abzubrechen~~ --> in Version 0.2 geändert
* ~~Möglichkeit für Spieler, den Hostname und den Port selber einzugeben~~ --> in Version 0.2 geändert
* Möglichkeit auf sofortiges nochmal spielen
* ~~Genauere Kommentare~~ --> in Version 0.2.1 geändert
* Bessere Validierung des Inputs
* Genauere Fehlerbehandlung
* Spielstatistiken
* Multithreading

## Installation
Öffne das Projekt entweder in IntelliJ, VSCode, oder jeder anderen IDE. Führe nun _zuerst_ die **Server.java** aus, _danach_ **Client.java**. Führen den Client **2x** aus, da es erst losgeht, wenn 2 Spieler verbunden sind.

Wenn du es über das Netzwerk mit anderen Spielen willst, gib in der main der Client-Klasse anstelle von "localhost" die lokale IP deines Rechners ein, welcher die Server.java ausführt. Diese findest du indem du im Terminal "ipconfig" eingibst, sie sieht in der Regel etwa so aus: 192.168.178.XXX. Trage diese als String ein.

Oder

Im Terminal (!!diese Methode funktioniert in der Version 0.1 nur auf einem einzigen Gerät und nicht über mehrere, da die IP nicht im terminal geändert werden kann udn somit auf localhost beschrängt ist!!)
1. Lade das Repository runter und navigiere im Terminal zu diesem Verzeichnis. 
![image](https://github.com/user-attachments/assets/bc612308-670d-41d9-89de-7547536f37c7)

2. Kompeliere die Datein durch den Befehl "javac *.java".
![image](https://github.com/user-attachments/assets/3fad39bb-d942-4286-8c95-c484d09f0ba7)

3. Starte den Server durch den Befehl "java Server".
![image](https://github.com/user-attachments/assets/183dd45b-fa31-4cca-9cbb-260205c44bea)

4. Öffne nun 2 weitere Terminale, navigiere ins gleiche Verzeichnis und gib den Befehl "java Client" ein.
![image](https://github.com/user-attachments/assets/34074397-c768-446a-89b2-eaa1c1fb2c38)

![image](https://github.com/user-attachments/assets/401174b0-ca47-41c9-804c-d70b92e78797)



## Version 0.2
Die Spieler haben nun jeweils die Möglichkeit, durch die Eingabe "ende" anstelle eines Moves, das Spiel zu beenden, ohne dass die Spiellogik ausgeführt wird. Dies geschieht durch die neu hinzugefügten Boolean-Wert "isRunning". Sobald "isRunning" false wird, endet der Server und somit auch das Spiel.

Die zweite Änderung ist die Möglichkeit für den Client, vor dem Verbinden den Hostname sowie den Port selber einzugeben. Somit ist diese Version auch über mehrere Geräte Spielbar, da der Code nicht auf den localhost beschränkt ist. 

### Version 0.2.1
Die Kommentare wurde auf Grammatik und Rechtschreibung geprüft, einige wurden hinzugefügt und das Programm sollte nun leichter verständlich sein anhand der Kommentare.











