package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.*;
import movings_Andi_Iskar.Position;
import utillities.Utts;
import vision_Lisa.Vision_1;

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
    ALTextToSpeech alTextToSpeech;
    long alBasicAwarenessID=0;
    private boolean hilf1=true;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);

        alBasicAwarenessID = alMemory.subscribeToEvent(
                "ALBasicAwareness/HumanTracked", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("ALBasicAwareness/HumanTracked");
                    }
                });
    }
}
