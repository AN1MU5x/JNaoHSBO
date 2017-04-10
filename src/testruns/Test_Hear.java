package testruns;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMemory;
import utillities.Utts;

import java.util.ArrayList;

/**
 * Created by JNaoHSBO on 10.04.2017.
 */
public class Test_Hear {

    ALMemory memory;
    long recID;

    public static void main(String[] args) throws Exception{
        Utts.AppStart();
        Test_Hear heard = new Test_Hear();
        heard.run(Utts.APP.session());
        Utts.APP.run();
    }

    public void run(Session session) throws Exception {

        memory = new ALMemory(session);
        recID = memory.subscribeToEvent(
                "SpeechDetected", arg0 -> {
                    try {
                        Utts.talk("Do you talk to me?");
                        Utts.APP.stop();
                        Test_Reaction.main(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
