package testruns;

import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import motion.Position;
import utillities.Uts;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.CallError;

import java.util.ArrayList;

/**
 * Created by Andi on 03.05.2017.
 */
public class Test_Test_üTest_Andi {
    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        System.out.println("Successfully connected to the robot");
        // Subscribe to selected ALMemory events
        Test_Test_üTest_Andi reactor = new Test_Test_üTest_Andi();

        //neu
        //Position position=new Position(Utts.getSESSION());
       // position.sitzen();
     //   position.sitzen(Utts.getSESSION());

        reactor.run(Uts.getSESSION());

        // Run your application
        Uts.getAPP().run();

    }

    ALMemory memory;
    ALSpeechRecognition aspr;
    ALTextToSpeech tts;
    long frontTactilSubscriptionId;
    boolean x=false;

    public void run(Session session) throws Exception{
        tts =new ALTextToSpeech(session);
        memory= new ALMemory(session);
        frontTactilSubscriptionId=0;

       ArrayList vok = new ArrayList<String>();
        aspr= new ALSpeechRecognition(session);
        vok.add(0,"ja");
        vok.add(1,"nein");
        vok.add(2,"bitte");
        vok.add(3,"wie");
        vok.add(4,"geht");
        vok.add(5,"es");
        vok.add(6,"dir");
        vok.add(7,"wie geht es dir?");
        vok.add(8,"mir geht es gut");
        vok.add(9,"setz dich hin ");

        System.out.println(vok);
        aspr.setLanguage("German");

        aspr.pause(true);
        aspr.setVocabulary(vok,true);
        aspr.pause(false);

         memory.subscribeToEvent(
                "SpeechDetected", new EventCallback<Integer>() {
                    @Override
                    public void onEvent(Integer arg0)
                            throws InterruptedException, CallError {
                        if(arg0==1){
                            System.out.println("Sie versteht");
                            x=true;
                        }
                    }
                });
        frontTactilSubscriptionId=memory.subscribeToEvent(
                "WordRecognized", new EventCallback() {
                    @Override
                    public void onEvent(Object arg0)
                            throws InterruptedException, CallError {

                        ArrayList a= new ArrayList();
                        a=(ArrayList)arg0;
                        System.out.println(a);
                        System.out.println(a.get(1));
                        float vers= (float)a.get(1);
                        String wort = (String) a.get(0);
                        System.out.println(wort);
                        if(vers>0.5){
                            if(wort.equals("<...> wie geht es dir? <...>")){
                                tts.say("Mir gehts gut und dir");
                            }
                            if(wort.equals("<...> mir geht es gut <...>")){
                                tts.say("Das ist schön");
                            }
                            if(wort.equals("<...> setz dich hin <...>")){
                                try {
                                    Position.winken(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                });
        System.out.println("Hallo");
    }
}

       /* memory.subscribeToEvent(
                "PeoplePerception/PeopleDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object arg0)
                            throws InterruptedException, CallError {
                        ArrayList a= (ArrayList) arg0;
                        System.out.println(a);
                    }
                });*/

        /* memory.subscribeToEvent(
                "WordRecognizedAndGrammar", new EventCallback() {
                    @Override
                    public void onEvent(Object arg0)
                            throws InterruptedException, CallError {
                        ArrayList a= new ArrayList();
                        a=(ArrayList)arg0;
                        System.out.println(a);
                        System.out.println(a.get(1));
                        float vers= (float)a.get(1);
                        String wort = (String) a.get(0);
                        System.out.println(wort);
                        if(vers>0.5){
                            tts.say("Habe es verstanden in grammer");
                        }
                        // tts.say();
                    }
                });*/