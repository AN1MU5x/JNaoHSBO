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
        Utts.AppStart();
        (new ALFaceDetection(Utts.getSESSION())).setTrackingEnabled(true);
        alSpeechRecognition = new ALSpeechRecognition(Utts.getSESSION());
        Utts.setNames(new ArrayList());
        Utts.addNames("lisa");
        Utts.addNames("andi");
        Utts.addNames("iskar");
        Utts.addNames("stefan");

        ArrayList<String> uttWords = new ArrayList<>();
        uttWords.add("stop");
        uttWords.add("gut");
        uttWords.add("akku");
        uttWords.add("ja");
        uttWords.add("nein");
        uttWords.add("schlecht");

        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("wie geht es dir?");
        System.out.println(Utts.getNames());

        for (String m: Utts.getNames()) {
            allWords.add(m);

        }
        for (String m: uttWords) {
            allWords.add(m);

        }

        for (String m: sentences){
            allWords.add(m);
        }
        alSpeechRecognition.pause(true);
        alSpeechRecognition.setVocabulary(allWords, true);
        alSpeechRecognition.pause(false);
        Test_Reaction test_reaction = new Test_Reaction();

        test_reaction.run(Utts.getSESSION());
        Utts.getAPP().run();
    }

    protected static long recID;
    private ALMemory memory;
    private ArrayList recWord = new ArrayList<String>();
    private int dialogCase =0;
    boolean stop=false;

    public void run(Session session) throws Exception {

        memory = new ALMemory(session);
        recID = memory.subscribeToEvent(
                "WordRecognized", arg0 -> {

                    alSpeechRecognition.pause(true);
                    System.out.println("PAUSED");
                    System.out.println("var stop is "+stop);
                    System.out.println("dialogCase = "+ dialogCase);
                    //getting the last word
                    recWord = (ArrayList) arg0;
                    System.out.println(recWord);
                    String word = (String)recWord.get(0);
                    if (word.charAt(0)=='<') {
                        // cut out <...> phrases
                        word = word.substring(word.indexOf('>') + 2, word.lastIndexOf('<') - 1);
                        System.out.println(word);
                    }
                    if(!stop) {
                        switch (dialogCase) {
                            case 0:
                                for (String m : Utts.getNames()) {
                                    //Talk to known people
                                    if (word.equals(m) && (float) recWord.get(1) > 0.5f) {
                                        try {
                                            Utts.talk("Hallo " + word + ", wie geht es dir?");
                                            Thread.sleep(100);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        dialogCase = 1;
                                    }
                                }if(word.equals("wie geht es dir?")&&(float)recWord.get(1)>0.5f){
                                try {
                                    Utts.talk("Mir geht es gut und wie geht es dir?");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                dialogCase = 1;
                            }
                            break;
                            case 1:
                                if (word.equals("gut")) {
                                    try {
                                        Utts.talk("Das finde ich toll!");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialogCase = 0;
                                }else if(word.equals("schlecht")){
                                    try {
                                        Utts.talk("Das ist aber schade.");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialogCase = 0;
                                }
                                break;
                        }
                        if (word.equals("stop") && (float) recWord.get(1) > 0.3f) {
                            try {
                                Utts.talk("Soll ich wirklich aufhören?");
                                Thread.sleep(20);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            stop = true;

                        } else if (word.equals("akku") && (int) recWord.get(1) > 0.5f) {
                            try {
                                Utts.talk("Mein Akku hat noch " + (new ALBattery(Utts.getSESSION())).getBatteryCharge() + "%");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else if(stop){
                        if(word.equals("ja")&&(float)recWord.get(1)>0.5f) {
                            try {
                                Utts.talk("OK, ich höre auf!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            alSpeechRecognition.pause(false);
                            memory.unsubscribeToEvent(recID);
                            Utts.AppStop();
                        }else if(word.equals("nein")&&(float)recWord.get(1)>0.5f){
                            try {
                                Utts.talk("Dann mache ich weiter!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            stop=false;
                        }
                    }
                    alSpeechRecognition.pause(false);
                    System.out.println("PAUSE END");
                });
    }
}
