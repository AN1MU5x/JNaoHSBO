import audio.WordRecognizedEvent;
import utillities.Uts;

public class Main {
    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        System.out.println("Successfully connected to the robot");

        WordRecognizedEvent wordRecognizedEvent = new WordRecognizedEvent();
        wordRecognizedEvent.run(Uts.getAPP().session());
        Uts.getAPP().run();

    }
}
