package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.*;
import movings_Andi_Iskar.Position;
import utillities.Utts;

import java.util.ArrayList;

/**
 * Created by Lisa on 24.04.2017.
 */
public class Test_Sensor {

    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        Test_Sensor sensor = new Test_Sensor();
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

                        if(hilf){
                            hilf=false;
                            Thread.sleep(6000);
                            Test_Vision vision = new Test_Vision();
                            try {
                                vision.run(Utts.getAPP().session());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Utts.getAPP().run();
                            alMovementDetection.unsubscribe("Test");
                            Utts.AppStop();
                        }

                    }
                });
    }
}
