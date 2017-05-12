package audio;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import motion.Position;
import sensors.TactilTouchedEvent;
import utillities.Uts;
import vision.FaceDetectedEvent;
import java.util.ArrayList;

//stop Funktion
//Reboot Sprachmodul

public class WordRecognizedEvent {

    //Funktions Trigger, false = 0, Funktion true > 0
    public static int iFunktion = 0;
    private static boolean bLocked;
    private static ALMemory alMemory;
    private static long wordID=0;
    private ArrayList recWord;
    private static ALFaceDetection alFaceDetection;
    private static ALSpeechRecognition alSpeechRecognition;
    //Dialog Trigger, false = 0, Dialog true > 0
    private int iDialog = 0;

    public void run(Session session) throws Exception {
        recWord = new ArrayList<String>();
        alMemory = new ALMemory(session);
        alSpeechRecognition = new ALSpeechRecognition(Uts.getSESSION());
        alFaceDetection = new ALFaceDetection(Uts.getSESSION());
        //Setzt Gesichtsverfolgung
        alFaceDetection.setTrackingEnabled(true);

        ArrayList<String> vocabulary = new ArrayList();
        vocabulary.add("hallo");//iFunktion=2
        vocabulary.add("hi");//iFunktion=2
        vocabulary.add("hey");//iFunktion=2
        vocabulary.add("wer bin ich");//iFunktion=1
        vocabulary.add("setz dich hin");
        vocabulary.add("hinsetzen");
        vocabulary.add("stell dich hin");
        vocabulary.add("hinstellen");
        vocabulary.add("aufstehen");
        vocabulary.add("folge mir");//iFunktion=4
        vocabulary.add("folgen");//iFunktion=4
        vocabulary.add("geh in die hocke");
        vocabulary.add("geht in die hocke");
        vocabulary.add("Hocken");
        vocabulary.add("winke winke");
        vocabulary.add("Winken");
        vocabulary.add("komm zu mir");
        vocabulary.add("wie geht es dir");//iDialog=1
        vocabulary.add("gut");
        vocabulary.add("schlecht");
        vocabulary.add("nicht so gut");
        vocabulary.add("geht so");
        vocabulary.add("leg dich hin");//iDialog=2
        vocabulary.add("hinlegen");//iDialog=2
        vocabulary.add("Bauch");//iFunktion=3
        vocabulary.add("Rücken");
        vocabulary.add("such andi");

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
                        iFunktion = 0;
                        bLocked = false;

                        if(probability>0.5) {
                            if (word.equals("<...> wer bin ich <...>")&&!bLocked) {
                                bLocked = true;
                                iFunktion = 1;
                                FaceDetectedEvent vision = new FaceDetectedEvent();
                                try {
                                    vision.run(Uts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Uts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);
                            }
                            else if(word.equals("<...> hallo <...>")&&!bLocked||word.equals("<...> hi <...>")&&!bLocked||word.equals("<...> hey <...>")&&!bLocked){
                                bLocked = true;
                                iFunktion = 2;
                                FaceDetectedEvent vision = new FaceDetectedEvent();
                                try {
                                    vision.run(Uts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Uts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);
                            }
                            else if(word.equals("<...> setz dich hin <...>")&&!bLocked||word.equals("<...> hinsetzen <...>")&&!bLocked){
                                bLocked = true;
                                try {
                                    Position.sitzen();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> stell dich hin <...>")&&!bLocked||word.equals("<...> hinstellen <...>")&&!bLocked||word.equals("<...> aufstehen <...>")&&!bLocked){
                                bLocked = true;
                                try {
                                    Position.stehen();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> folge mir <...>")&&!bLocked||word.equals("<...> folgen <...>")&&!bLocked){
                                //Funktion noch in Bearbeitung
                                bLocked = true;

                            }
                            else if(word.equals("<...> geh in die hocke <...>")&&!bLocked||word.equals("<...> geht in die hocke <...>")&&!bLocked||word.equals("<...> Hocken <...>")&&!bLocked){
                                bLocked = true;
                                try {
                                    Position.hocke();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> winke winke <...>")&&!bLocked||word.equals("<...> Winken <...>")&&!bLocked){
                                bLocked = true;
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> komm zu mir <...>")&&!bLocked){
                               //Funktion noch nicht vorhanden
                                bLocked = true;
                            }
                            else if(word.equals("<...> wie geht es dir <...>")&&!bLocked){
                                bLocked = true;
                                try {
                                    Uts.talk(" Gut und dir");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                iDialog = 1;
                            }
                            else if(word.equals("<...> gut <...>")&&iDialog==1&&!bLocked){
                                bLocked = true;
                                iDialog = 0;
                                try {
                                    Uts.talk(" Das freut mich");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> geht so <...>")&&iDialog==1&&!bLocked||word.equals("<...> nicht so gut <...>")&&iDialog==1&&!bLocked||word.equals("<...> schlecht <...>")&&iDialog==1&&!bLocked){
                                bLocked = true;
                                iDialog = 0;
                                try {
                                    Uts.talk(" Hmm das ist aber nicht so schön ");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> leg dich hin <...>")&&!bLocked||word.equals("<...> hinlegen <...>")&&!bLocked){
                                bLocked = true;
                                try {
                                    Uts.talk("Soll ich mich auf den Bauch oder auf den Rücken legen?");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                iDialog = 2;
                            }
                            else if(word.equals("<...> Bauch <...>")&&iDialog==2&&!bLocked){
                                bLocked = true;
                                iDialog = 0;
                                //Noch zu bearbeiten
                                try {
                                    Position.liegenBauch();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                iFunktion = 3;
                                TactilTouchedEvent tactilTouchedEvent = new TactilTouchedEvent();
                                try {
                                    tactilTouchedEvent.run(Uts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Uts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);
                            }
                            else if(word.equals("<...> Rücken <...>")&&iDialog==2&&!bLocked){
                                bLocked = true;
                                iDialog = 0;
                                try {
                                    Position.liegenRuecken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> such andi <...>")&&!bLocked){
                                //Funktion noch in Bearbeitung
                                bLocked = true;
                                iFunktion = 4;
                                FaceDetectedEvent vision = new FaceDetectedEvent();
                                try {
                                    vision.run(Uts.getAPP().session());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Uts.getAPP().run();
                                alMemory.unsubscribeToEvent(wordID);

                            }
                        }
                    }
                });

    }
}

