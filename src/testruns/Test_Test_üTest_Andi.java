package testruns;

import com.aldebaran.qi.helper.proxies.ALVideoDevice;
import utillities.Uts;

/**
 * Created by Andi on 03.05.2017.
 */
/*
*/

public class Test_Test_üTest_Andi {

    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        System.out.println("Successfully connected to the robot");
        //Test_Tracker tracker = new Test_Tracker();
        //tracker.run(Uts.getAPP().session());
        //Uts.getAPP().run();
        String subscriberID="subscriberID";

        boolean die=true;
        System.out.println("VOr");
        while(die) {
            ALVideoDevice d = new ALVideoDevice(Uts.getSESSION());
            d.subscribeCamera("Test", 0, 2, 0, 5);
            subscriberID = d.subscribe(subscriberID, 2, 0, 5);

            Object o = d.getImageLocal(subscriberID);
            System.out.println(o);

            d.releaseImage(subscriberID);
            System.out.println(d.releaseImage(subscriberID));

            Thread.sleep(15000);
            d.unsubscribe(subscriberID);
        }

    }
}