package testruns;


import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.*;
import motion.Position;
import utillities.Uts;
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
        Uts.AppStart();
        (new ALFaceDetection(Uts.getSESSION())).setTrackingEnabled(true);
        alSpeechRecognition = new ALSpeechRecognition(Uts.getSESSION());
        Uts.setNames(new ArrayList());
        Uts.addNames("lisa");
        Uts.addNames("andi");
        Uts.addNames("iskar");
        Uts.addNames("stefan");

        ArrayList<String> uttWords = new ArrayList<>();
        uttWords.add("stop");
        uttWords.add("gut");
        uttWords.add("akku");
        uttWords.add("ja");
        uttWords.add("nein");
        uttWords.add("schlecht");
        uttWords.add("hallo");

        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("wie geht es dir?");
        System.out.println(Uts.getNames());

        for (String m: Uts.getNames()) {
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

        Thread.sleep(10);
        Test_Reaction test_reaction = new Test_Reaction();

        test_reaction.run(Uts.getSESSION());
        Uts.getAPP().run();
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
                                for (String m : Uts.getNames()) {
                                    //Talk to known people
                                    if (word.equals(m) && (float) recWord.get(1) > 0.5f) {
                                        try {
                                            Uts.talk("Hallo " + word + ", wie geht es dir?");
                                            Thread.sleep(100);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        dialogCase = 1;
                                    }
                                }if(word.equals("wie geht es dir?")&&(float)recWord.get(1)>0.5f){
                                try {
                                    Uts.talk("Mir geht es gut und wie geht es dir?");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                dialogCase = 1;
                                }else if(word.equals("hallo")&&(float)recWord.get(1)>0.5f){
                                    try {
                                        Position.winken(session);
                                    } catch (Exception e) {
                                       e.printStackTrace();
                                    }
                                }
                            break;
                            case 1:
                                if (word.equals("gut")) {
                                    try {
                                        Uts.talk("Das finde ich toll!");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialogCase = 0;
                                }else if(word.equals("schlecht")){
                                    try {
                                        Uts.talk("Das ist aber schade.");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialogCase = 0;
                                }
                                break;
                        }
                        if (word.equals("stop") && (float) recWord.get(1) > 0.3f) {
                            try {
                                Uts.talk("Soll ich wirklich aufhören?");
                                Thread.sleep(20);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            stop = true;

                        } else if (word.equals("akku") && (int) recWord.get(1) > 0.5f) {
                            try {
                                Uts.talk("Mein Akku hat noch " + (new ALBattery(Uts.getSESSION())).getBatteryCharge() + "%");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else if(stop){
                        if(word.equals("ja")&&(float)recWord.get(1)>0.5f) {
                            try {
                                Uts.talk("OK, ich höre auf!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            alSpeechRecognition.pause(false);
                            memory.unsubscribeToEvent(recID);
                            Uts.AppStop();
                        }else if(word.equals("nein")&&(float)recWord.get(1)>0.5f){
                            try {
                                Uts.talk("Dann mache ich weiter!");
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
