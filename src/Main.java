import audio.WordRecognizedEvent;
import com.aldebaran.qi.Session;
import motion.Position;
import sensors.FrontTactilTouchedEvent;
import sun.management.Sensor;
import utillities.Uts;


/**
 * Created by Lisa on 03.04.2017.
 */
public class Main {
    private static boolean b = true;

    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        FrontTactilTouchedEvent frontTactilTouchedEvent = new FrontTactilTouchedEvent();
        frontTactilTouchedEvent.FrontTactil(Uts.getSESSION());
        Uts.getAPP().run();
    }
}
