package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.*;
import movings_Andi_Iskar.Position;
import java.util.ArrayList;

import static testruns.Test_Combo.getAllWords;
import static utillities.Utts.*;

/**
 * Created by JNaoHSBO on 26.04.2017.
 */
public class Test_Events {


    private static ALSpeechRecognition alSpeechRecognition;
    private static ArrayList<String> allWords = new ArrayList<>();
    protected static long recID;
    private static ALMemory memory;
    private static ArrayList recWord = new ArrayList<>();
    private static int dialogCase =0;
    private static boolean stop=false;
    private static ALMemory alMemory;
    private static ALTextToSpeech alTextToSpeech;
    private static ALFaceDetection alFaceDetection;
    private static boolean hilf1=true;
    private static ALFaceDetection a;
    private static boolean returnValue;


    public static void reaction()throws Exception{
        alSpeechRecognition  = new ALSpeechRecognition(getSESSION());
        memory = new ALMemory(getSESSION());
        alSpeechRecognition.subscribe("TestRec");
        alSpeechRecognition.pause(true);
        alSpeechRecognition.setVocabulary(getAllWords(), true);
        alSpeechRecognition.pause(false);

        recID = memory.subscribeToEvent(
                "WordRecognized", arg0 -> {

                    alSpeechRecognition.pause(true);
                    returnValue = false;
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
                                for (String m : getNames()) {
                                    //Talk to known people
                                    if (word.equals(m) && (float) recWord.get(1) > 0.5f) {
                                        try {
                                            talk("Hallo " + word + ", wie geht es dir?");
                                            Thread.sleep(100);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        dialogCase = 1;
                                    }
                                }
                                if(word.equals("wie geht es dir?")&&(float)recWord.get(1)>0.5f){
                                    try {
                                        talk("Mir geht es gut und wie geht es dir?");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialogCase = 1;
                                }else if(word.equals("hallo")&&(float)recWord.get(1)>0.5f){
                                    try {
                                        Position.winken();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }else if(word.equals("wer bin ich?")&&(float)recWord.get(1)>0.5f){
                                    System.out.println("FaceRec started");
                                    returnValue = true;
                                }
                                break;
                            case 1:
                                if (word.equals("gut")) {
                                    try {
                                        talk("Das finde ich toll!");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialogCase = 0;
                                }else if(word.equals("schlecht")){
                                    try {
                                        talk("Das ist aber schade.");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialogCase = 0;
                                }
                                break;
                        }
                        if (word.equals("stop") && (float) recWord.get(1) > 0.3f) {
                            try {
                                talk("Soll ich wirklich aufhören?");
                                Thread.sleep(20);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            stop = true;

                        } else if (word.equals("akku") && (float) recWord.get(1) > 0.5f) {
                            try {
                                talk("Mein Akku hat noch " + (new ALBattery(getSESSION())).getBatteryCharge() + "%");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        alSpeechRecognition.pause(false);
                        System.out.println("PAUSE END");
                    }else if(stop){
                        if(word.equals("ja")&&(float)recWord.get(1)>0.5f) {
                            try {
                                talk("OK, ich höre auf!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            alSpeechRecognition.pause(false);
                            alSpeechRecognition.unsubscribe("TestRec");
                            memory.unsubscribeToEvent(recID);
                            AppStop();
                        }else if(word.equals("nein")&&(float)recWord.get(1)>0.5f){
                            try {
                                talk("Dann mache ich weiter!");
                                alSpeechRecognition.pause(false);
                                System.out.println("PAUSE END");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            stop=false;
                        }
                    }
                    alSpeechRecognition.pause(false);
                    memory.raiseEvent("ComboLoop", returnValue);
                });

    }

    public static void see() throws Exception {
        alMemory = new ALMemory(getSESSION());
        alTextToSpeech = new ALTextToSpeech(getSESSION());
        alFaceDetection = new ALFaceDetection(getSESSION());

        alFaceDetection.subscribe("Test",10000,0.0f);
        alMemory.subscribeToEvent(
                "FaceDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Face detected");
                        ArrayList faceDetected = (ArrayList)o;
                        ArrayList faceInfoList =(ArrayList) (faceDetected.get(1));
                        ArrayList faceInfo = (ArrayList) (faceInfoList.get(0));
                        ArrayList extraInfo = (ArrayList) (faceInfo.get(1));
                        String faceLabel = (String) (extraInfo.get(2));

                        if(!faceLabel.equals("")&&hilf1) {
                            System.out.println(faceLabel);
                            if (faceLabel.equals("Lisa")) {
                                hilf1 = false;
                                alTextToSpeech.say("Hallo " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                alFaceDetection.unsubscribe("Test");
                                a.setTrackingEnabled(false);
                                memory.raiseEvent("ComboLoop", false);
                                return ;
                            }
                            if (faceLabel.equals("Andi")) {
                                hilf1 = false;
                                alTextToSpeech.say("Hallo " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                alFaceDetection.unsubscribe("Test");
                                a.setTrackingEnabled(false);

                            }
                            if (faceLabel.equals("Iskar")) {
                                hilf1 = false;
                                alTextToSpeech.say("Hallo " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                alFaceDetection.unsubscribe("Test");
                                a.setTrackingEnabled(false);

                            }
                            if (faceLabel.equals("Stefan")) {
                                hilf1 = false;
                                alTextToSpeech.say("Hallo " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                alFaceDetection.unsubscribe("Test");
                                a.setTrackingEnabled(false);
                            }
                        }
                        memory.raiseEvent("ComboLoop", false);
                    }
                });
    }
}
