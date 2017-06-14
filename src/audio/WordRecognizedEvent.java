package audio;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
//Klasse für alle Bewegungen
import motion.Folgen;
import motion.Position;
//Klasse für den Kopfsensor
import sensors.TactilTouchedEvent;
//Klasse für verschiedene kleine Methoden wie z.B. AppStart();
import testruns.Test_folge;
import utillities.Uts;
//Klasse für die Gesichtserkennung
import vision.FaceDetectedEvent;
import java.util.ArrayList;

//Aufgaben:
//stop Funktion
//folge mir
//komm zu mir
//Bauch
//such andi
//such lisa
//such iskar
//such stefan
//
public class WordRecognizedEvent {

    //Setzt Funktionen, false = 0 und true > 0
    public static int iFunktion ;
    //Boolean Variable zum sperren der Funktionen gegeneinander
    private boolean bLocked;
    public static  ALMemory alMemory;
    private ArrayList recWord;
    private  ALFaceDetection alFaceDetection;
    private  ALSpeechRecognition alSpeechRecognition;
    private  FaceDetectedEvent FDE;
    //Setzt die Dialoge, false = 0 und true > 0
    private int iDialog = 0;


    public void run(Session session) throws Exception {

        recWord = new ArrayList<String>();
        alMemory = new ALMemory(session);
        FDE=new FaceDetectedEvent();

        alSpeechRecognition = new ALSpeechRecognition(session);

        //Spracheinstellung
        alSpeechRecognition.setLanguage("German");
        alSpeechRecognition.subscribe("Word",1000,0.0f);

        //Wörterbibliothek
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
        vocabulary.add("such andi");//iFunktion=4
        vocabulary.add("such stefan");//iFunktion=5
        vocabulary.add("such iskar");//iFunktion=6
        vocabulary.add("such lisa");//iFunktion=7
        vocabulary.add("suche stop");

        alSpeechRecognition.pause(true);
        alSpeechRecognition.setVocabulary(vocabulary,true);
        alSpeechRecognition.pause(false);

        alMemory.subscribeToEvent(
                "WordRecognized", new EventCallback() {
                    @Override
                    public void onEvent(Object arg0) throws InterruptedException, CallError {

                        recWord = (ArrayList) arg0;
                        System.out.println(recWord);
                        String word=(String) recWord.get(0);
                        System.out.println(word);
                        float probability = (float)recWord.get(1);
                        bLocked = false;

                        if(probability>0.5) {
                            if (word.equals("<...> wer bin ich <...>") && !bLocked) {
                                bLocked = true;
                                iFunktion = 1;

                                try {
                                    FDE.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if((word.equals("<...> hallo <...>") && !bLocked) | (word.equals("<...> hi <...>") && !bLocked) | (word.equals("<...> hey <...>") && !bLocked)){

                                bLocked = true;
                                iFunktion = 2;

                                try {
                                    FDE.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if((word.equals("<...> setz dich hin <...>") && !bLocked) | (word.equals("<...> hinsetzen <...>") && !bLocked)){

                                bLocked = true;

                                try {
                                    Position.sitzen(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if((word.equals("<...> stell dich hin <...>") && !bLocked) | (word.equals("<...> hinstellen <...>") && !bLocked) | (word.equals("<...> aufstehen <...>") && !bLocked)){

                                bLocked = true;

                                try {
                                    Position.stehen(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if((word.equals("<...> geh in die hocke <...>") && !bLocked) | (word.equals("<...> geht in die hocke <...>") && !bLocked) | (word.equals("<...> Hocken <...>") && !bLocked)){

                                bLocked = true;

                                try {
                                    Position.hocke(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if((word.equals("<...> winke winke <...>") && !bLocked) | (word.equals("<...> Winken <...>") && !bLocked)){

                                bLocked = true;

                                try {
                                    Position.winken(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if(word.equals("<...> komm zu mir <...>") && !bLocked){

                                //Funktion noch in Bearbeitung
                                bLocked = true;

                            }
                            else if(word.equals("<...> wie geht es dir <...>") && !bLocked){

                                bLocked = true;

                                try {
                                    Uts.talk(" Gut und dir");
                                } catch (Exception e) {
                                    System.err.println("Fehler bei wie geht es dir.");
                                }

                                iDialog = 1;

                            }
                            else if(word.equals("<...> gut <...>") && iDialog==1 && !bLocked){

                                bLocked = true;
                                iDialog = 0;

                                try {
                                    Uts.talk(" Das freut mich");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if((word.equals("<...> geht so <...>") && iDialog==1 && !bLocked) | (word.equals("<...> nicht so gut <...>") && iDialog==1 && !bLocked) | (word.equals("<...> schlecht <...>") && iDialog==1 && !bLocked)){

                                bLocked = true;
                                iDialog = 0;

                                try {
                                    Uts.talk(" Hmm das ist aber nicht so schön ");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if((word.equals("<...> leg dich hin <...>") && !bLocked) | (word.equals("<...> hinlegen <...>") && !bLocked)){

                                bLocked = true;

                                try {
                                    Uts.talk("Soll ich mich auf den Bauch oder auf den Rücken legen?");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                iDialog = 2;

                            }
                            else if(word.equals("<...> Bauch <...>") && iDialog==2 && !bLocked){

                                bLocked = true;
                                iDialog = 0;

                                try {
                                    Position.liegenBauch(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                iFunktion = 3;
                                TactilTouchedEvent tactilTouchedEvent = new TactilTouchedEvent();

                                try {
                                    tactilTouchedEvent.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if(word.equals("<...> Rücken <...>") && iDialog==2 && !bLocked){

                                bLocked = true;
                                iDialog = 0;

                                try {
                                    Position.liegenRuecken(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if(word.equals("<...> such andi <...>") && !bLocked){

                                bLocked = true;
                                iFunktion = 4;

                                try {
                                    FDE.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if(word.equals("<...> such stefan <...>") && !bLocked) {

                                bLocked = true;
                                iFunktion = 5;

                                try {
                                    FDE.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if(word.equals("<...> such iskar <...>") && !bLocked) {

                                bLocked = true;
                                iFunktion = 6;

                                try {
                                    FDE.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if(word.equals("<...> such lisa <...>") && !bLocked) {

                                bLocked = true;
                                iFunktion = 7;

                                try {
                                    FDE.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if(word.equals("<...> suche stop <...>")) {

                                Folgen.a.stopTracker();
                                FaceDetectedEvent.alMemory1.unsubscribeToEvent(FaceDetectedEvent.faceID);
                                FaceDetectedEvent.alMemory1.unsubscribeAllEvents();
                                Folgen.suche=false;
                                Folgen.folgenan=false;
                                Folgen.prob=false;
                                iFunktion=0;

                                try {
                                    Uts.talk("Ich folge nicht mehr");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                });
    }

}


