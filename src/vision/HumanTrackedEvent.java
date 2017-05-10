package vision;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

/**
 * Created by Lisa on 28.04.2017.
 */
public class HumanTrackedEvent {

    ALMemory alMemory;
    ALTextToSpeech alTextToSpeech;
    long alBasicAwarenessID=0;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);

        alBasicAwarenessID = alMemory.subscribeToEvent(
                "ALBasicAwareness/HumanTrackedEvent", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("ALBasicAwareness/HumanTrackedEvent");
                        //Hier Anweisung
                        alMemory.unsubscribeToEvent(alBasicAwarenessID);
                    }
                });
    }
}
