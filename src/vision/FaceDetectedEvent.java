package vision;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import motion.Follow;
import motion.Position;
import java.util.ArrayList;

public class FaceDetectedEvent {
   public static ALMemory alMemory1;
   public static long lFaceID = 0;

   private ALTextToSpeech alTextToSpeech;
   private ALFaceDetection alFaceDetection;
   private Follow follow ;

   //Variable zum Sperren der Funktion nach einmaligem benutzen
   private boolean bOneUse = true;

   //Variable zum zählen der Gesichtserkennung
   private int iCounter = 0;


   public void run(Session session) throws Exception {

        //Erstellen von Objekten
        alMemory1 = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);
        alFaceDetection = new ALFaceDetection(session);
        follow = new Follow();

        //Event starten
        alFaceDetection.subscribe("Face", 500, 0.0f);
        lFaceID = alMemory1.subscribeToEvent(
                "FaceDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {

                        iCounter++;
                        System.out.println(iCounter+": Face detected");

                        //Um an den Namen von gespeicherten Gesichtern zu kommen ist es nötig eine Vielzahl von Listen zu durchlaufen.
                        //Genaue Aufteilung ist beschrieben in der API.
                        ArrayList faceDetected = (ArrayList) o;

                        if (faceDetected.size() > 0) {

                            ArrayList faceInfoList = (ArrayList) (faceDetected.get(1));

                            if (faceInfoList.size() > 1) {

                                ArrayList faceInfo = (ArrayList) (faceInfoList.get(0));
                                ArrayList extraInfo = (ArrayList) (faceInfo.get(1));

                                //Name von gespeicherten Gesichtern
                                String faceLabel = (String) (extraInfo.get(2));
                                System.out.println(faceLabel);

                                //Aktion auf "Wer bin ich"
                                if (WordRecognizedEvent.iFunktion == 1 && iCounter < 20) {

                                    if (faceLabel.equals("Koch") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");

                                        //Sprachausgabe erfolgt
                                        alTextToSpeech.say("Herr " + faceLabel);
                                    }
                                    if (faceLabel.equals("Brabender") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");

                                        //Sprachausgabe erfolgt
                                        alTextToSpeech.say("Frau " + faceLabel);
                                    }
                                    if (faceLabel.equals("Stefan") && bOneUse || faceLabel.equals("Iskar") && bOneUse || faceLabel.equals("Andi") && bOneUse || faceLabel.equals("Lisa") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");

                                        //Sprachausgabe erfolgt
                                        alTextToSpeech.say(faceLabel);
                                    }
                                }
                                else if (WordRecognizedEvent.iFunktion == 1 && (iCounter >= 20) && bOneUse) {

                                    bOneUse = false;
                                    iCounter = 0;

                                    //Event wird beendet
                                    alMemory1.unsubscribeToEvent(lFaceID);
                                    alFaceDetection.unsubscribe("Face");

                                    //Sprachausgabe erfolgt
                                    alTextToSpeech.say("Keine Ahnung");
                                }

                                //Aktion auf "Hallo"
                                else if (WordRecognizedEvent.iFunktion == 2 && iCounter < 20) {

                                    if (faceLabel.equals("Koch") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");

                                        //Sprachausgabe erfolgt
                                        alTextToSpeech.say("Hallo Herr " + faceLabel);

                                        //Bewegung Winken wird ausgeführt
                                        try {
                                            Position.winken(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (faceLabel.equals("Brabender") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");

                                        //Sprachausgabe erfolgt
                                        alTextToSpeech.say("Hallo Frau " + faceLabel);

                                        //Bewegung Winken wird ausgeführt
                                        try {
                                            Position.winken(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (faceLabel.equals("Stefan") && bOneUse || faceLabel.equals("Iskar") && bOneUse || faceLabel.equals("Andi") && bOneUse|| faceLabel.equals("Lisa") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");

                                        //Sprachausgabe erfolgt
                                        alTextToSpeech.say("Hallo " + faceLabel);

                                        //Bewegung Winken wird ausgeführt
                                        try {
                                            Position.winken(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                else if (WordRecognizedEvent.iFunktion == 2 && (iCounter >= 20) && bOneUse) {

                                    bOneUse = false;
                                    iCounter = 0;

                                    //Event wird beendet
                                    alMemory1.unsubscribeToEvent(lFaceID);
                                    alFaceDetection.unsubscribe("Face");

                                    //Sprachausgabe erfolgt
                                    alTextToSpeech.say("Hallo");
                                }

                                //Aktion auf "Suche Andi"
                                if ((((WordRecognizedEvent.iFunktion == 4)||Follow.bTargetLost)) && WordRecognizedEvent.iFunktion==4) {

                                    if (faceLabel.equals("Andi")) {

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Follow.bFollowOn =true;
                                        Follow.bSearch =true;

                                        //Aktion Folgen wird ausgeführt
                                        try {
                                            follow.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                //Aktion auf "Suche Stefan"
                                if (((WordRecognizedEvent.iFunktion == 5) || (Follow.bTargetLost)) && WordRecognizedEvent.iFunktion==5) {

                                    if (faceLabel.equals("Stefan")) {

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Follow.bFollowOn =true;
                                        Follow.bSearch =true;

                                        //Aktion Folgen wird ausgeführt
                                        try {
                                            follow.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                //Aktion auf "Suche Iskar"
                                if (((WordRecognizedEvent.iFunktion == 6) || (Follow.bTargetLost)) && WordRecognizedEvent.iFunktion==6) {

                                    if (faceLabel.equals("Iskar")) {

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Follow.bFollowOn = true;
                                        Follow.bSearch = true;

                                        //Aktion Folgen wird ausgeführt
                                        try {
                                            follow.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                //Aktion auf "Suche Lisa"
                                if (((WordRecognizedEvent.iFunktion == 7) || (Follow.bTargetLost)) && WordRecognizedEvent.iFunktion==7) {

                                    if (faceLabel.equals("Lisa")) {

                                        //Event wird beendet
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Follow.bFollowOn =true;
                                        Follow.bSearch =true;

                                        //Aktion Folgen wird ausgeführt
                                        try {
                                            follow.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }
}