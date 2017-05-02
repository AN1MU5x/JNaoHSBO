package vision_Lisa;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import testruns.Test_Sensor;
import utillities.Utts;

/**
 * Created by Lisa on 28.04.2017.
 */
public class HumanTracked {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        HumanTracked sensor = new HumanTracked();
        sensor.run(Utts.getAPP().session());
        Utts.getAPP().run();
    }

    ALMemory alMemory;
    ALTextToSpeech alTextToSpeech;
    long alBasicAwarenessID=0;
    private boolean hilf=true;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);

        alBasicAwarenessID = alMemory.subscribeToEvent(
                "ALBasicAwareness/HumanTracked", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("ALBasicAwareness/HumanTracked");
                        if(hilf){
                            hilf=false;
                            Thread.sleep(3500);
                            Vision_1 vision = new Vision_1();
                            try {
                                vision.run(Utts.getAPP().session());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Utts.getAPP().run();
                            alMemory.unsubscribeToEvent(alBasicAwarenessID);
                            Utts.AppStop();
                        }
                    }
                });
    }
}
