package vision_Lisa;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMovementDetection;
import testruns.Test_Vision;
import utillities.Utts;
/**
 * Created by Lisa on 26.04.2017.
 */
public class MovingDetection {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        MovingDetection sensor = new MovingDetection();
        sensor.run(Utts.getAPP().session());
        Utts.getAPP().run();
    }

    ALMemory alMemory;
    ALMovementDetection alMovementDetection;
    boolean hilf= true;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alMovementDetection = new ALMovementDetection(session);

        alMovementDetection.subscribe("Test",10000,0.0f);
        alMemory.subscribeToEvent(
                "MovementDetection/MovementDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Movement detected");

                    }
                });
    }
}
