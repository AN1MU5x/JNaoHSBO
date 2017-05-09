import audio.WordRecognizedEvent;
import com.aldebaran.qi.Session;
import motion.Position;
import sensors.TactilTouchedEvent;
import sun.management.Sensor;
import utillities.Uts;


/**
 * Created by Lisa on 03.04.2017.
 */
public class Main {
    private static boolean b = true;

    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        TactilTouchedEvent tactilTouchedEvent = new TactilTouchedEvent();
        tactilTouchedEvent.FrontTactil(Uts.getSESSION());
        tactilTouchedEvent.RearTactil(Uts.getSESSION());
        tactilTouchedEvent.MiddleTactil(Uts.getSESSION());
        Uts.getAPP().run();
    }
}
