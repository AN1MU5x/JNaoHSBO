package sensors;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSonar;

public class SonarDetectedEvent {

    ALMemory alMemory;
    ALSonar alSonar;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alSonar = new ALSonar(session);

        alSonar.subscribe("Test",10000,0.0f);
        alMemory.subscribeToEvent(
                "SonarLeftDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Sonar Left Detected");
                        //Hier Anweisung

                    }
                });
        alMemory.subscribeToEvent(
                "SonarRightDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Sonar Right Detected");
                        //Hier Anweisung

                    }
                });
    }
}
