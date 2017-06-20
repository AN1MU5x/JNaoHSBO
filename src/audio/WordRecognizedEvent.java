package audio;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import motion.Follow;
import motion.Position;
import sensors.TactilTouchedEvent;
import utillities.Uts;
import vision.FaceDetectedEvent;
import java.util.ArrayList;

public class WordRecognizedEvent {

    public static  ALMemory alMemory;
    private ArrayList recWord;
    private ALFaceDetection alFaceDetection;
    private ALSpeechRecognition alSpeechRecognition;

    //iFunktion setzt Wert auf false = 0 und true > 0
    public static int iFunktion ;

    //Boolean Variable zum sperren der Funktionen gegeneinander
    private boolean bLocked;

    //Setzt die Dialoge, false = 0 und true > 0
    private int iDialog = 0;

    public void run(Session session) throws Exception {

        //Erzeugen von Objekten

        //ArrayList für verstandene Wörter + Genauigkeit beim verstehen
        recWord = new ArrayList<String>();

        //ALMemory ist ein zentraler Speicher, der verwendet wird um alle wichtigen Informationen bezüglich der Hardwarekonfiguration des Roboters zu speichern
        alMemory = new ALMemory(session);

        //Das Modul ALSpeechRecognition gibt dem Roboter die Möglichkeit vordefinierte Wörter oder Phrasen in mehreren Sprachen zu erkennen
        alSpeechRecognition = new ALSpeechRecognition(session);

        //ALFaceDetection ist ein Vision-Modul, welches dem Roboter ermöglicht Gesichter zu erkennen.
        alFaceDetection = new ALFaceDetection(session);

        //Suche nach Gesichtern wird eingeschaltet
        alFaceDetection.setTrackingEnabled(true);

        //Spracheinstellung
        alSpeechRecognition.setLanguage("German");
        alSpeechRecognition.subscribe("Word",1000,0.0f);

        //Array-Liste für Deutsche Wörterbibliothek
        ArrayList<String> vocabulary = new ArrayList();
        vocabulary.add("hallo");                //iFunktion=2
        vocabulary.add("hi");                   //iFunktion=2
        vocabulary.add("hey");                  //iFunktion=2
        vocabulary.add("wer bin ich");          //iFunktion=1
        vocabulary.add("setz dich hin");
        vocabulary.add("hinsetzen");
        vocabulary.add("stell dich hin");
        vocabulary.add("hinstellen");
        vocabulary.add("aufstehen");
        vocabulary.add("folge mir");
        vocabulary.add("folgen");
        vocabulary.add("geh in die hocke");
        vocabulary.add("geht in die hocke");
        vocabulary.add("Hocken");
        vocabulary.add("winke winke");
        vocabulary.add("Winken");
        vocabulary.add("komm zu mir");
        vocabulary.add("wie geht es dir");     //iDialog=1
        vocabulary.add("gut");
        vocabulary.add("schlecht");
        vocabulary.add("nicht so gut");
        vocabulary.add("geht so");
        vocabulary.add("leg dich hin");         //iDialog=2
        vocabulary.add("hinlegen");             //iDialog=2
        vocabulary.add("Bauch");                //iFunktion=3
        vocabulary.add("Rücken");
        vocabulary.add("such andi");            //iFunktion=4
        vocabulary.add("such stefan");          //iFunktion=5
        vocabulary.add("such iskar");           //iFunktion=6
        vocabulary.add("such lisa");            //iFunktion=7
        vocabulary.add("suche stop");
        vocabulary.add("Vorstellen");
        vocabulary.add("wie heißt du");

        //Speechrec auf pause setzen um die Wörterbibliothek einzubinden
        alSpeechRecognition.pause(true);
        alSpeechRecognition.setVocabulary(vocabulary,true);
        alSpeechRecognition.pause(false);

        alMemory.subscribeToEvent(
                "WordRecognized", new EventCallback() {
                    @Override
                    public void onEvent(Object arg0) throws InterruptedException, CallError {

                        recWord = (ArrayList) arg0;

                        //Ausgabe der verstandenen Wörter + Genauigkeit beim verstehen
                        System.out.println(recWord);

                        //Abspeichern verstandener Wörter in einem String
                        String word=(String) recWord.get(0);

                        //Ausgabe der verstandenen Wörter, zur Kontrolle am Computer
                        System.out.println(word);

                        //Genauigkeit der verstandenen Wörter, Ausgabe zur Kontrolle am Computer
                        float probability = (float)recWord.get(1);

                        //Boolean Variable zum sperren der Funktionen gegeneinander; hier aufgehoben
                        bLocked = false;

                        if(probability>0.5) {

                            //Verstandenes Wort vergleichen mit Wörterbib & Überprüfung ob nichts gesperrt ist
                            if (word.equals("<...> wer bin ich <...>") && !bLocked) {

                                //Boolean Variable zum sperren der Funktionen
                                bLocked = true;

                                //iFunktion wird ein Wert zugeteilt, damit das FaceDetectedEvent mit der Funktion arbeiten kann
                                iFunktion = 1;

                                //Event um mit gespeicherten Gesichtern zu arbeiten, die der Roboter erkennt
                                FaceDetectedEvent faceDetectedEvent = new FaceDetectedEvent();

                                try {

                                    //Aufrufen der Methode
                                    faceDetectedEvent.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if((word.equals("<...> hallo <...>") && !bLocked) || (word.equals("<...> hi <...>") && !bLocked) || (word.equals("<...> hey <...>") && !bLocked)){

                                bLocked = true;
                                iFunktion = 2;
                                FaceDetectedEvent faceDetectedEvent = new FaceDetectedEvent();
                                try {
                                    faceDetectedEvent.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if((word.equals("<...> setz dich hin <...>") && !bLocked) || (word.equals("<...> hinsetzen <...>") && !bLocked)){

                                bLocked = true;
                                try {
                                    Position.sitzen(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if((word.equals("<...> stell dich hin <...>") && !bLocked) || (word.equals("<...> hinstellen <...>") && !bLocked) || (word.equals("<...> aufstehen <...>") && !bLocked)){

                                bLocked = true;
                                try {
                                    Position.stehen(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if((word.equals("<...> geh in die hocke <...>") && !bLocked) || (word.equals("<...> geht in die hocke <...>") && !bLocked) || (word.equals("<...> Hocken <...>") && !bLocked)){

                                bLocked = true;
                                try {
                                    Position.hocke(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if((word.equals("<...> winke winke <...>") && !bLocked) || (word.equals("<...> Winken <...>") && !bLocked)){

                                bLocked = true;
                                try {
                                    Position.winken(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> komm zu mir <...>") && !bLocked){

                                bLocked = true;
                                try {
                                    Position.come(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else if(word.equals("<...> folgen <...>") && !bLocked||word.equals("<...> folge mir <...>") && !bLocked){

                                bLocked = true;
                                try {
                                    Position.follow(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            //Gespräch mit weiterführenden Dialog
                            else if(word.equals("<...> wie geht es dir <...>") && !bLocked){

                                bLocked = true;
                                try {
                                    Uts.talk(" Gut und dir");
                                } catch (Exception e) {
                                    System.err.println("Fehler bei wie geht es dir.");
                                }

                                //Für den weiterführenden Dialog
                                iDialog = 1;
                            }
                            else if(word.equals("<...> gut <...>") && iDialog==1 && !bLocked){

                                bLocked = true;

                                //Dialog wieder auf 0 setzen um wieder von vorne zu beginnen
                                iDialog = 0;
                                try {
                                    Uts.talk(" Das freut mich");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if((word.equals("<...> geht so <...>") && iDialog==1 && !bLocked) || (word.equals("<...> nicht so gut <...>") && iDialog==1 && !bLocked) || (word.equals("<...> schlecht <...>") && iDialog==1 && !bLocked)){

                                bLocked = true;
                                iDialog = 0;
                                try {
                                    Uts.talk(" Hmm das ist aber nicht so schön ");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if((word.equals("<...> leg dich hin <...>") && !bLocked) || (word.equals("<...> hinlegen <...>") && !bLocked)){

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

                                //Event für die Sensoren auf dem Kopf, die erkennen welcher Sensor berührt worden ist
                                TactilTouchedEvent tactilTouchedEvent = new TactilTouchedEvent();

                                try {

                                    //Aufrufen der Methode
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
                                iDialog = 3;
                                FaceDetectedEvent faceDetectedEvent = new FaceDetectedEvent();
                                Follow.bTargetLost= true;
                                try {
                                    Uts.talk("Ich suche jetzt Andi");
                                    faceDetectedEvent.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> such stefan <...>") && !bLocked) {

                                bLocked = true;
                                iFunktion = 5;
                                iDialog = 3;
                                FaceDetectedEvent faceDetectedEvent = new FaceDetectedEvent();
                                try {
                                    faceDetectedEvent.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> such iskar <...>") && !bLocked) {

                                bLocked = true;
                                iFunktion = 6;
                                iDialog = 3;
                                FaceDetectedEvent faceDetectedEvent = new FaceDetectedEvent();
                                try {
                                    faceDetectedEvent.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> such lisa <...>") && !bLocked) {

                                bLocked = true;
                                iFunktion = 7;
                                iDialog = 3;
                                FaceDetectedEvent faceDetectedEvent = new FaceDetectedEvent();
                                try {
                                    faceDetectedEvent.run(session);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> suche stop <...>") && iDialog == 3) {

                                //Varibalen werden auf ihre Ursprungswerte gesetzt
                                iFunktion=0;
                                Follow.bFollowOn =false;
                                Follow.bSearch =false;
                                Follow.bTargetLost =false;
                                Follow.alTracker.setMode("Head");

                                //Die Globale Variable alTracker wird gestoppt, damit der Roboter nicht mehr folgt
                                Follow.alTracker.stopTracker();

                                //Varibalen werden auf ihre Ursprungswerte gesetzt
                               // Follow.bSearch =false;
                                //Follow.bFollowOn =false;
                               // Follow.bTargetLost =false;
                               // iFunktion=0;

                                try {
                                    Uts.talk("Ich folge nicht mehr");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //Beenden des Events
                                FaceDetectedEvent.alMemory1.unsubscribeToEvent(FaceDetectedEvent.lFaceID);
                                FaceDetectedEvent.alMemory1.unsubscribeAllEvents();
                            }
                            else if(word.equals("<...> Vorstellen <...>") && !bLocked) {

                                bLocked = true;
                                try {
                                    Uts.talk("Hallo ich bin Emma");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else if(word.equals("<...> wie heißt du <...>") && !bLocked) {

                                bLocked = true;
                                try {
                                    Uts.talk("Mein Name ist Emma");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }

}


