package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import com.aldebaran.qi.helper.proxies.ALTracker;
import utillities.Uts;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by iskar on 02.05.17.
 */
public class Test_Tracker {
    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        System.out.println("Successfully connected to the robot");
        Test_Tracker tracker = new Test_Tracker();
        tracker.run(Uts.getAPP().session());
        Uts.getAPP().run();
    }

    ALMemory alMemory;
    ALTextToSpeech alTextToSpeech;
    long alBasicAwarenessID=0;
    private boolean hilf=true;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);

        alBasicAwarenessID = alMemory.subscribeToEvent(
                "ALTracker/trackEvent", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Hallo");
                    }
                });
    }
}
