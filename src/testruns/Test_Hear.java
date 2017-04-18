package testruns;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMemory;
import utillities.TimeOut;
import utillities.Utts;



/**
 * Created by JNaoHSBO on 10.04.2017.
 */
public class Test_Hear {

    private ALMemory memory;
    private long recID;
    private static boolean reced;

    public static void main(String[] args) throws Exception{
        Utts.AppStart();
        Test_Hear heard = new Test_Hear();
        heard.run(Utts.getAPP().session());
        TimeOut.make(10000).start();
        Utts.getAPP().run();
        if(reced){
            System.out.println("Reaction loaded!");
            testruns.Test_Reaction.main(null);
        }

    }



    public void run(Session session) throws Exception {
        System.out.println("START");

        System.out.println("TimeOut started");
        memory = new ALMemory(session);
        recID = memory.subscribeToEvent(
                "SpeechDetected", arg0 -> {
                    try {
                        Utts.talk("Did you talk to me?");
                        reced = true;
                        TimeOut.avoid();
                        Utts.getAPP().stop();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });


    }
}
