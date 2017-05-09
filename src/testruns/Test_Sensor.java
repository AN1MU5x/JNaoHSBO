package testruns;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.*;
import utillities.Uts;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;


/**
 * Created by Lisa on 24.04.2017.
 */
public class Test_Sensor {

    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        System.out.println("Successfully connected to the robot");
        Test_Sensor sensor = new Test_Sensor();
        sensor.run(Uts.getAPP().session());
        Uts.getAPP().run();
    }

    ALMemory memory;
    ALTextToSpeech tts;
    long frontTactilSubscriptionId;
    private static boolean b = true;

    public void run(Session session) throws Exception {

        memory = new ALMemory(session);
        tts = new ALTextToSpeech(session);
        frontTactilSubscriptionId = 0;

        ALTracker a = new ALTracker(session);
        ALMotion suche = new ALMotion(Uts.getSESSION());

        while (b) {
            a.track("Face");
            a.setMode("Move");
            System.out.println(a.isSearchEnabled());
            Thread.sleep(1000);
            System.out.println(a.getTargetPosition().size());

            //Event, welches auf die Berührung des FrontTactile reagiert
            frontTactilSubscriptionId = memory.subscribeToEvent(
                    "FrontTactilTouched", new EventCallback<Float>() {
                        @Override
                        public void onEvent(Float arg0)
                                throws InterruptedException, CallError {
                            // 1 --> Sensor wurde gedrückt
                            if (arg0 > 0 && b) {
                                //Beenden der Schleife
                                b = false;
                                tts.say("Ok ich folge dir nicht mehr");
                                //Von hier wird die App beendet
                                Uts.AppStop();
                                memory.unsubscribeToEvent(frontTactilSubscriptionId);
                            }
                        }
                    });
        }
    }
}

//System.out.println(a.getTargetPosition());
//a.track("Face");

//Thread.sleep(5000);
//System.out.println(a.isSearchEnabled());
//System.out.println(a.getTargetPosition().get(0));