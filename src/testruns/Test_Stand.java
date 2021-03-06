package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALPeoplePerception;
import com.aldebaran.qi.helper.proxies.ALSittingPeopleDetection;
import motion.Position;
import utillities.Uts;

/**
 * Created by iskar on 08.05.17.
 */
public class Test_Stand {
    public static void main(String[] args) throws Exception{

        Uts.AppStart();

        Thread.sleep(10);
        Test_Stand s = new Test_Stand();
        s.run(Uts.getSESSION());
        Uts.getAPP().run();
    }

    ALMemory memory;
    ALSittingPeopleDetection alSittingPeopleDetection;

    public void run(Session session) throws Exception {
        memory = new ALMemory(session);
        alSittingPeopleDetection = new ALSittingPeopleDetection(session);
        alSittingPeopleDetection.subscribe("test",10000,0.0f);
        memory.subscribeToEvent("SittingPeopleDetection/PersonSittingDown", new EventCallback() {
            @Override
            public void onEvent(Object o) throws InterruptedException, CallError {
                System.out.println("Erkannt");
                try {
                    Uts.talk("Na gut, unterhalten wir uns");
                    Position.sitzenRelax(session);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
