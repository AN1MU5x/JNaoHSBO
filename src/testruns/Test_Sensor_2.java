package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMovementDetection;
import com.aldebaran.qi.helper.proxies.ALSonar;
import utillities.Utts;

/**
 * Created by Lisa on 27.04.2017.
 */
public class Test_Sensor_2 {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        Test_Sensor_2 sensor = new Test_Sensor_2();
        sensor.run(Utts.getAPP().session());
        Utts.getAPP().run();
    }

    ALMemory alMemory;
    ALSonar alSonar;
    boolean hilf= true;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alSonar = new ALSonar(session);

        alSonar.subscribe("Test",10000,0.0f);
        alMemory.subscribeToEvent(
                "SonarLeftDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Sonar detected");


                    }
                });
    }
}
