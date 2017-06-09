//Klasse für die Spracherkennung
import audio.WordRecognizedEvent;
//Klasse für verschiedene kleine Methoden wie z.B. AppStart();
import javafx.stage.Stage;
import utillities.Surface_Thread;
import utillities.User_Surface_1;
import utillities.Uts;

public class Main extends User_Surface_1{
    public static void main(String[] args) throws Exception {
        //Benutzeroberfläche starten

        Surface_Thread thread = new Surface_Thread();
        thread.start();


        //Applikation wird gestartet
        System.out.println("Successfully connected to the robot");
        thread.join(10000);
        //Starten der Spracherkennung
        WordRecognizedEvent wordRecognizedEvent = new WordRecognizedEvent();
        wordRecognizedEvent.run(Uts.getAPP().session());
        Uts.getAPP().run();

    }
}
