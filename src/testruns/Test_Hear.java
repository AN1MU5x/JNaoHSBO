package testruns;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import utillities.TimeOut;
import utillities.Uts;



/**
 * Created by JNaoHSBO on 10.04.2017.
 */
public class Test_Hear {

    private static TimeOut timeOut = new TimeOut();
    private ALMemory memory;
    private long recID;
    private static boolean reced;
    private static Test_Hear heard = new Test_Hear();

    public static void main(String[] args) throws Exception{

        Uts.AppStart();
        (new ALFaceDetection(Uts.getSESSION())).setTrackingEnabled(true);

        heard.run(Uts.getSESSION());
        Uts.getAPP().run();


    }



    public void run(Session session) throws Exception {
        System.out.println("START");

        memory = new ALMemory(session);
        recID = memory.subscribeToEvent(
                "SpeechDetected", arg0 -> {
                    try {
                        memory.unsubscribeToEvent(recID);
                        Uts.talk("Ja bitte?");
                        reced = true;
                        Thread.sleep(1000);
                        Test_Reaction test_reaction = new Test_Reaction();
                        test_reaction.main(null);
                        test_reaction.run(Uts.getSESSION());
                        Uts.getAPP().run();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });


    }
}
