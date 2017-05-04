package testruns;

import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import movings_Andi_Iskar.Position;
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

        //neu
        //Position position=new Position(Utts.getSESSION());
       // position.sitzen();
     //   position.sitzen(Utts.getSESSION());

        reactor.run(Utts.getSESSION());

        // Run your application
        Utts.getAPP().run();

    }

    ALMemory memory;
    ALSpeechRecognition aspr;
    ALTextToSpeech tts;
    long frontTactilSubscriptionId;
    boolean x=false;
    Position position;

    public void run(Session session) throws Exception{
        tts =new ALTextToSpeech(session);
        memory= new ALMemory(session);
        frontTactilSubscriptionId=0;
        position= new Position(session);


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




       /* aspr.subscribe("Test_aspr");
        Thread.sleep(20000);
        aspr.unsubscribe("Test_aspr");*/
        //aspr.subscribe("Test_aspr",500,0.f);


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
                        aspr.pause(true);
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
                                    position.sitzen();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }




                        }
                        aspr.pause(false);
                        //Thread.sleep(1000);
                       // tts.say();




                    }
                });
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
        System.out.println("Hallo");





    }
}
