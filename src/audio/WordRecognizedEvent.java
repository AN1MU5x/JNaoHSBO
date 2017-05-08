package audio;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import motion.Position;
import utillities.Utts;
import vision.FaceDetectedEvent;
import java.util.ArrayList;

public class WordRecognizedEvent {
    public static void main(String[] args) throws Exception{
        Utts.AppStart();

        Thread.sleep(10);
        WordRecognizedEvent cWRE = new WordRecognizedEvent();
        cWRE.run(Utts.getSESSION());
        Utts.getAPP().run();
    }

    private static ALMemory alMemory;
    private static long wordID=0;
    private ArrayList recWord = new ArrayList<String>();//Ändern
    private static ALFaceDetection alFaceDetection;
    private static ALSpeechRecognition alSpeechRecognition;
    public static boolean hallo;
    public static boolean wbi;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alSpeechRecognition = new ALSpeechRecognition(Utts.getSESSION());
        alFaceDetection = new ALFaceDetection(Utts.getSESSION());
        alFaceDetection.setTrackingEnabled(true);

        ArrayList<String> vocabulary = new ArrayList();
        vocabulary.add("hallo");
        vocabulary.add("hi");
        vocabulary.add("hey");
        vocabulary.add("wer bin ich");
        vocabulary.add("setz dich hin");
        vocabulary.add("hinsetzen");
        vocabulary.add("stell dich hin");
        vocabulary.add("hinstellen");
        vocabulary.add("aufstehen");
        vocabulary.add("gut");
        vocabulary.add("schlecht");
        vocabulary.add("geht so");

        alSpeechRecognition.pause(true);
        alSpeechRecognition.setVocabulary(vocabulary,true);
        alSpeechRecognition.pause(false);

        wordID = alMemory.subscribeToEvent(
                "WordRecognized", new EventCallback() {
                    @Override
                    public void onEvent(Object arg0) throws InterruptedException, CallError {
                        recWord = (ArrayList) arg0;
                        System.out.println(recWord);
                        String word = (String) recWord.get(0);
                        System.out.println(word);
                        float probability = (float)recWord.get(1);
                        hallo = false;
                        wbi = false;

                        if(probability>0.5) {
                            if (word.equals("<...> wer bin ich <...>")) {
                                wbi = true;
                                FaceDetectedEvent vision = new FaceDetectedEvent();
                                try {
                                    vision.run(Utts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Utts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);
                            }
                            else if(word.equals("<...> hallo <...>")||word.equals("<...> hi <...>")||word.equals("<...> hey <...>")){
                                hallo = true;
                                FaceDetectedEvent vision = new FaceDetectedEvent();
                                try {
                                    vision.run(Utts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Utts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);
                            }
                            else if(word.equals("<...> setz dich hin <...>")||word.equals("<...> hinsetzen <...>")){
                                try {
                                    Position.sitzenRelax();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> stell dich hin <...>")||word.equals("<...> hinstellen <...>")||word.equals("<...> aufstehen <...>")){
                                try {
                                    Position.stehen();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> wie geht es dir <...>")){
                                try {
                                    Utts.talk("Gut und dir");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if(word.equals("<...> gut <...>")){
                                    try {
                                        Utts.talk("Das ist aber schön");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                else if(word.equals("<...> schlecht <...>")||word.equals("<...> geht so <...>")){
                                    try {
                                        Utts.talk("Das ist aber schade");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
