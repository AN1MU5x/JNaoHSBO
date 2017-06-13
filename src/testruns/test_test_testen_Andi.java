package testruns;

import audio.WordRecognizedEvent;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import utillities.Uts;

/**
 * Created by Daniel on 16.05.2017.
 */
public class test_test_testen_Andi {
    public static void main( String[] args )
    {
        Uts.AppStart();
        System.out.println("Successfully connected to the robot");

        WordRecognizedEvent wordRecognizedEvent = new WordRecognizedEvent();
        try {
            wordRecognizedEvent.run(Uts.getAPP().session());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uts.getAPP().run();
    }
}
