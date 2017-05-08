package motion;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import com.aldebaran.qi.helper.proxies.ALTracker;
import utillities.Utts;

/**
 * Created by JNaoHSBO on 05.04.2017.
 */
public class Follow {

    ALMemory memory;
    ALTextToSpeech tts;
    long frontTactilSubscriptionId;
    private static boolean b = true;

    public void run(Session session) throws Exception {

        memory = new ALMemory(session);
        tts = new ALTextToSpeech(session);
        frontTactilSubscriptionId = 0;

        ALTracker a = new ALTracker(session);
        ALMotion suche = new ALMotion(Utts.getSESSION());

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
                                Utts.AppStop();
                                memory.unsubscribeToEvent(frontTactilSubscriptionId);
                            }
                        }
                    });
        }
    }
}
