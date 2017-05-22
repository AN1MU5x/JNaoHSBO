package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import com.aldebaran.qi.helper.proxies.ALTracker;
import com.aldebaran.qi.helper.proxies.ALVideoDevice;
import utillities.Uts;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
       /* alMemory = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);

        alBasicAwarenessID = alMemory.subscribeToEvent(
                "ALTracker/trackEvent", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Hallo");
                    }
                });*/
        String subscriberID="subscriberID";
        //List<Object> o=new List<Object>();

        boolean die=true;
        System.out.println("VOr");
        while(die) {
            ALVideoDevice d = new ALVideoDevice(Uts.getSESSION());
            d.subscribeCamera("Test", 0, 2, 0, 5);
            subscriberID = d.subscribe(subscriberID, 2, 0, 5);

            //Object o = d.getImageLocal(subscriberID);
            List<Object> o = (List<Object>)d.getImageRemote(subscriberID);
            System.out.println(o);

            d.releaseImage(subscriberID);
            System.out.println(d.releaseImage(subscriberID));
            ByteBuffer buffer = (ByteBuffer)o.get(6);

            Thread.sleep(5000);
            System.out.println("in dem warten ");
            d.unsubscribe(subscriberID);
            Thread.sleep(15000);
        }
    }
}
