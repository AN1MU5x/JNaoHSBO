package audio;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import motion.Position;
import utillities.Uts;
import vision.FaceDetectedEvent;
import java.util.ArrayList;

public class WordRecognizedEvent {
    public static void main(String[] args) throws Exception{
        Uts.AppStart();

        Thread.sleep(10);
        WordRecognizedEvent cWRE = new WordRecognizedEvent();
        cWRE.run(Uts.getSESSION());
        Uts.getAPP().run();
    }

    private static ALMemory alMemory;
    private static long wordID=0;
    private ArrayList recWord;
    private static ALFaceDetection alFaceDetection;
    private static ALSpeechRecognition alSpeechRecognition;
    public static boolean hallo;
    public static boolean wbi;

    public void run(Session session) throws Exception {
        recWord = new ArrayList<String>();
        alMemory = new ALMemory(session);
        alSpeechRecognition = new ALSpeechRecognition(Uts.getSESSION());
        alFaceDetection = new ALFaceDetection(Uts.getSESSION());
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
        vocabulary.add("folge mir");
        vocabulary.add("folgen");
        vocabulary.add("geh in die hocke");
        vocabulary.add("geht in die hocke");
        vocabulary.add("winke winke");

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
                                    vision.run(Uts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Uts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);
                            }
                            else if(word.equals("<...> hallo <...>")||word.equals("<...> hi <...>")||word.equals("<...> hey <...>")){
                                hallo = true;
                                FaceDetectedEvent vision = new FaceDetectedEvent();
                                try {
                                    vision.run(Uts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Uts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);
                            }
                            else if(word.equals("<...> setz dich hin <...>")||word.equals("<...> hinsetzen <...>")){
                                try {
                                    Position.sitzen();
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
                            else if(word.equals("<...> folge mir <...>")||word.equals("<...> folgen <...>")){
                                /*Follow follow = new Follow();
                                try {
                                    follow.run(Uts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Uts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);*/
                                try {
                                    Position.follow();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> geh in die hocke <...>")||word.equals("<...> geht in die hocke <...>")){
                                try {
                                    Position.hocke();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> winke winke <...>")){
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
                });
    }
}

