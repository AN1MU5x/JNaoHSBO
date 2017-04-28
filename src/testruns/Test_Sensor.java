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
    long alTrackedID=0;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);

        alTrackedID = alMemory.subscribeToEvent(
                "ALLandMarkDetection", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("LandMark");
                    }
                });
    }
}
