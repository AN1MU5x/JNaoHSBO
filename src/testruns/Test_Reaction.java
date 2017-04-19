package testruns;


import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.*;
import utillities.Utts;


import java.util.ArrayList;


/**
 * Created by JNaoHSBO on 05.04.2017.
 */
public class Test_Reaction{

    private static ALSpeechRecognition alSpeechRecognition;
    private static ArrayList<String> allWords = new ArrayList<>();


    public static void main(String[] args) throws Exception{
/*
        alSpeechRecognition.pause(true);
        alSpeechRecognition.setVocabulary(names, true);
        alSpeechRecognition.pause(false);
*/
       // Utts.AppStart();
        (new ALFaceDetection(Utts.getSESSION())).setTrackingEnabled(true);
        alSpeechRecognition = new ALSpeechRecognition(Utts.getSESSION());
        Utts.setNames(new ArrayList());
        Utts.addNames("lisa");
        Utts.addNames("andi");
        Utts.addNames("iskar");
        Utts.addNames("stefan");

        ArrayList<String> uttWords = new ArrayList<>();
        uttWords.add("stop");
        uttWords.add("akku");
        System.out.println(Utts.getNames());

        for (String m: Utts.getNames()) {
            allWords.add(m);

        }
        for (String m: uttWords) {
            allWords.add(m);

        }

        Test_Reaction test_reaction = new Test_Reaction();

        test_reaction.run(Utts.getSESSION());
        Utts.getAPP().run();
    }

    protected static long recID;
    private ALMemory memory;
    private ArrayList recWord = new ArrayList<String>();

    public void run(Session session) throws Exception {

        memory = new ALMemory(session);
        recID = memory.subscribeToEvent(
                "WordRecognized", arg0 -> {

                    alSpeechRecognition.pause(true);
                    alSpeechRecognition.setVocabulary(allWords , true);
                    //getting the last word
                    recWord = (ArrayList) arg0;
                    System.out.println(recWord);
                    String word = (String)recWord.get(0);
                    if (word.charAt(0)=='<') {
                        // cut out <...> phrases
                        word = word.substring(word.indexOf('>') + 2, word.lastIndexOf('<') - 1);
                        System.out.println(word);
                    }

                    for (String m: Utts.getNames()) {
                        //Talk to known people
                        if(word.equals(m)&&(float)recWord.get(1)>0.5f){
                            try {
                                Utts.talk("Hallo " + word + ", wie geht es dir?");
                                Thread.sleep(100);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }


                    }if(word.equals("stop")&&(float)recWord.get(1)>0.3f){
                        try {
                            Utts.talk("OK ich höre auf!");
                            Thread.sleep(20);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        alSpeechRecognition.pause(false);
                        memory.unsubscribeToEvent(recID);
                        Utts.AppStop();
                    }else if(word.equals("akku")){
                        try {
                            Utts.talk("Mein Akku hat noch "+(new ALBattery(Utts.getSESSION())).getBatteryCharge()+"%");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    alSpeechRecognition.pause(false);
                });
    }
}
