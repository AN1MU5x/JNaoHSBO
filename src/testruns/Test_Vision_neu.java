package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.*;
import utillities.Utts;

import java.util.ArrayList;


/**
 * Created by iskar on 28.04.17.
 */
public class Test_Vision_neu {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        ALTracker b  = new ALTracker(Utts.getAPP().session());
        System.out.println("Successfully connected to the robot");
        b.trackEvent("LandMark");
        Test_Vision sensor = new Test_Vision();
        sensor.run(Utts.getAPP().session());
        Utts.getAPP().run();
    }

    ALMemory alMemory;
    ALBarcodeReader alBarcodeReader;
    long alBasicAwarenessID=0;
    //ALTracker alTracker;


    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        ALTracker b  = new ALTracker(session);
        alBarcodeReader = new ALBarcodeReader(session);
        ALMotion a = new ALMotion(session);

        //alBarcodeReader.subscribe("Test",10000,0.0f);
        alBasicAwarenessID = alMemory.subscribeToEvent(
                "LandMarkDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        ArrayList codeData = (ArrayList)o;
                        System.out.println("Ausgabe Arraylist"+codeData);

                    }
                });
    }

}
