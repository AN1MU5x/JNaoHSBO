package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.*;
import motion.Position;
import utillities.Utts;
import vision.FaceDetectedEvent;

import java.util.ArrayList;

/**
 * Created by Lisa on 05.04.2017.
 */
public class Test_Speak{

    public static void main(String[] args) throws Exception{

        Utts.AppStart();

        Thread.sleep(10);
        Test_Speak speak = new Test_Speak();
        speak.run(Utts.getSESSION());
        Utts.getAPP().run();
    }

    private static ALMemory alMemory;
    private static long wordID=0;
    private ArrayList recWord = new ArrayList<String>();
    private static ALFaceDetection alFaceDetection;
    private static ALSpeechRecognition alSpeechRecognition;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alSpeechRecognition = new ALSpeechRecognition(Utts.getSESSION());
        alFaceDetection = new ALFaceDetection(Utts.getSESSION());
        alFaceDetection.setTrackingEnabled(true);

        ArrayList<String> vocabulary = new ArrayList();
        vocabulary.add("hallo");
        vocabulary.add("wer bin ich");
        vocabulary.add("setz dich hin");
        vocabulary.add("stell dich hin");

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
                        float wahrscheinlichkeit = (float)recWord.get(1);

                        if(wahrscheinlichkeit>0.5) {
                            if (word.equals("<...> wer bin ich <...>")) {
                                FaceDetectedEvent vision = new FaceDetectedEvent();
                                try {
                                    vision.run(Utts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Utts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);
                            }
                            else if(word.equals("<...> hallo <...>")){
                                try {
                                    Utts.talk("Hallo");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> setz dich hin <...>")){
                                try {
                                    Position.sitzenRelax();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> stell dich hin <...>")){
                                try {
                                    Position.stehen();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }

}
