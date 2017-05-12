package vision;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMovementDetection;
/**
 * Created by Lisa on 26.04.2017.
 */
public class MovementDetectionEvent {

    ALMemory alMemory;
    ALMovementDetection alMovementDetection;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alMovementDetection = new ALMovementDetection(session);

        alMovementDetection.subscribe("Test",10000,0.0f);
        alMemory.subscribeToEvent(
                "MovementDetection/MovementDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Movement detected");
                        //Hier Anweisung
                    }
                });
    }
}
