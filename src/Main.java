//Klasse für die Spracherkennung
import audio.WordRecognizedEvent;
//Klasse für verschiedene kleine Methoden wie z.B. AppStart();
import utillities.Uts;

public class Main {
    public static void main(String[] args) throws Exception {
        //Applikation wird gestartet
        Uts.AppStart();
        System.out.println("Successfully connected to the robot");

        //Starten der Spracherkennung
        WordRecognizedEvent wordRecognizedEvent = new WordRecognizedEvent();
        wordRecognizedEvent.run(Uts.getAPP().session());
        Uts.getAPP().run();

    }
}
