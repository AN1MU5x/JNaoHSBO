package testruns;

import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import utillities.Utts;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.CallError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andi on 03.05.2017.
 */
public class Test_Test_üTest_Andi {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        // Subscribe to selected ALMemory events
        Test_Test_üTest_Andi reactor = new Test_Test_üTest_Andi();

        reactor.run(Utts.getSESSION());

        // Run your application
        Utts.getAPP().run();

    }

    ALMemory memory;
    ALSpeechRecognition aspr;
    ALTextToSpeech tts;
    long frontTactilSubscriptionId;



    public void run(Session session) throws Exception{
        tts =new ALTextToSpeech(session);
        memory= new ALMemory(session);
        frontTactilSubscriptionId=0;


       /* memory.subscribeToEvent(
                "PeoplePerception/PeopleDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object arg0)
                            throws InterruptedException, CallError {
                        ArrayList a= (ArrayList) arg0;
                        System.out.println(a);



                    }
                });*/

       ArrayList vok = new ArrayList<String>();
        aspr= new ALSpeechRecognition(session);
        vok.add(0,"Ja");
        vok.add(1,"Nein");
        vok.add(2,"Bitte");
        System.out.println(vok);
        aspr.setLanguage("German");
        //aspr.setVocabulary(vok,true);

       /* aspr.subscribe("Test_aspr");
        Thread.sleep(20000);
        aspr.unsubscribe("Test_aspr");
       // aspr.subscribe("Test_aspr");*/


        frontTactilSubscriptionId= memory.subscribeToEvent(
                "SpeechDetected", new EventCallback<Boolean>() {
                    @Override
                    public void onEvent(Boolean arg0)
                            throws InterruptedException, CallError {

                        System.out.println("Nop");



                    }
                });





    }
}
