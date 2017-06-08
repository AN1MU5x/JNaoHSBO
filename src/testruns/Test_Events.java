package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.*;
import java.util.ArrayList;
import static utillities.Uts.*;
import motion.Position;

/**
 * Created by JNaoHSBO on 26.04.2017.
 */
public class Test_Events {


    private ALSpeechRecognition alSpeechRecognition;
    private ArrayList<String> allWords;
    private ALMemory memory1, memory2;
    private ArrayList recWord = new ArrayList<>();
    private int dialogCase =0;
    private boolean stop=false;
    private ALMemory alMemory;
    private ALTextToSpeech alTextToSpeech;
    private ALFaceDetection alFaceDetection;
    private boolean hilf1=true;
    private ALFaceDetection a;
    private boolean returnValue;
    private long recID;

    public Test_Events(ALMemory memory1, ALMemory memory2, ALSpeechRecognition speechRecognition, ALFaceDetection faceDetection, ArrayList wordList) throws InterruptedException, CallError {
        this.memory1 = memory1;
        this.memory2 = memory2;
        this.alSpeechRecognition = speechRecognition;
        this.alFaceDetection = faceDetection;
        this.allWords = wordList;
        alSpeechRecognition.pause(true);
        alSpeechRecognition.setVocabulary(allWords, true);
        alSpeechRecognition.pause(false);
    }


    void reaction()throws Exception{

        recID = memory1.subscribeToEvent(
                "WordRecognized", arg0 -> {

                    alSpeechRecognition.pause(true);
                    returnValue = false;
                    System.out.println("PAUSED");
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
                                        System.out.println("dialogCase = "+ dialogCase);
                                    }
                                }
                                if(word.equals("wie geht es dir?")&&(float)recWord.get(1)>0.5f){
                                    try {
                                        talk("Mir geht es gut und wie geht es dir?");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialogCase = 1;
                                    System.out.println("dialogCase = "+ dialogCase);
                                }else if(word.equals("hallo")&&(float)recWord.get(1)>0.5f){
                                    try {
                                        Position.winken();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }else if(word.equals("wer bin ich?")&&(float)recWord.get(1)>0.5f){
                                    System.out.println("FaceRec started");
                                    returnValue = true;
                                    memory1.raiseEvent("ComboLoop", returnValue);
                                    memory1.unsubscribeToEvent(recID);
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
                                    System.out.println("dialogCase = "+ dialogCase);
                                }else if(word.equals("schlecht")){
                                    try {
                                        talk("Das ist aber schade.");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialogCase = 0;
                                    System.out.println("dialogCase = "+ dialogCase);
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
                            System.out.println("var stop is "+stop);

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
                            memory1.unsubscribeToEvent(recID);
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
                            System.out.println("var stop is "+stop);
                        }
                    }
                    Thread.sleep(50);
                    alSpeechRecognition.pause(false);
                    memory1.raiseEvent("ComboLoop", returnValue);
                });

    }

    void see() throws Exception {
        alTextToSpeech = new ALTextToSpeech(getSESSION());

        alFaceDetection.subscribe("Test",10000,0.0f);
        memory2.subscribeToEvent(
                "FaceDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        int i=0;
                        while(i<1000&&hilf1) {
                            System.out.println("Face detected");
                            ArrayList faceDetected = (ArrayList) o;
                            ArrayList faceInfoList = (ArrayList) (faceDetected.get(1));
                            ArrayList faceInfo = (ArrayList) (faceInfoList.get(0));
                            ArrayList extraInfo = (ArrayList) (faceInfo.get(1));
                            String faceLabel = (String) (extraInfo.get(2));

                            Thread.sleep(4000);
                            if (!faceLabel.equals("") && hilf1) {
                                System.out.println(faceLabel);
                                hilf1 = false;
                                alTextToSpeech.say("Hallo " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Thread.sleep(50);
                                memory2.raiseEvent("ComboLoop", false);
                                alFaceDetection.unsubscribe("Test");
                                a.setTrackingEnabled(false);
                            }
                            i++;
                        }
                        if(hilf1){
                            hilf1=false;
                            alTextToSpeech.say("Hallo");
                            try {
                                Position.winken();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Thread.sleep(50);
                            memory2.raiseEvent("ComboLoop", false);
                            alFaceDetection.unsubscribe("Test");
                            a.setTrackingEnabled(false);
                        }

                    }

                });
    }
}
