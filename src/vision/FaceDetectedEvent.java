package vision;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import motion.Folgen;
import motion.Position;
import java.util.ArrayList;

public class FaceDetectedEvent {

   public static ALMemory alMemory1;
   private ALTextToSpeech alTextToSpeech;
   private ALFaceDetection alFaceDetection;
   public static long faceID = 0;
   private int ihilf = 0;
   Folgen f ;

   public void run(Session session) throws Exception {

        alMemory1 = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);
        alFaceDetection = new ALFaceDetection(session);
        f = new Folgen();
        alFaceDetection.subscribe("Face", 500, 0.0f);

        faceID = alMemory1.subscribeToEvent(
                "FaceDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        ihilf++;

                        System.out.println("Face detected");
                        ArrayList faceDetected = (ArrayList) o;

                        if (faceDetected.size() > 0) {

                            ArrayList faceInfoList = (ArrayList) (faceDetected.get(1));

                            if (faceInfoList.size() > 1) {

                                ArrayList faceInfo = (ArrayList) (faceInfoList.get(0));
                                ArrayList extraInfo = (ArrayList) (faceInfo.get(1));
                                String faceLabel = (String) (extraInfo.get(2));
                                System.out.println(faceLabel);

                                //Wer bin ich
                                if (WordRecognizedEvent.iFunktion == 1 && ihilf < 20) {
                                    if (faceLabel.equals("Koch")) {

                                        ihilf = 0;
                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Herr " + faceLabel);

                                    }
                                    if (faceLabel.equals("Brabender")) {

                                        ihilf = 0;
                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Frau " + faceLabel);

                                    }
                                    if (faceLabel.equals("Stefan") | faceLabel.equals("Iskar") | faceLabel.equals("Andi") | faceLabel.equals("Lisa")) {

                                        ihilf = 0;
                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say(faceLabel);

                                    }
                                } else if (WordRecognizedEvent.iFunktion == 1 && (ihilf >= 20)) {

                                    alMemory1.unsubscribeToEvent(faceID);
                                    alFaceDetection.unsubscribe("Face");
                                    alTextToSpeech.say("Keine Ahnung");
                                    ihilf=0;

                                }

                                //Hallo
                                else if (WordRecognizedEvent.iFunktion == 2 && ihilf < 20) {

                                    if (faceLabel.equals("Koch")) {

                                        ihilf = 0;
                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Hallo Herr " + faceLabel);

                                        try {
                                            Position.winken(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    if (faceLabel.equals("Brabender")) {

                                        ihilf = 0;
                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Hallo Frau " + faceLabel);

                                        try {
                                            Position.winken(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    if (faceLabel.equals("Stefan") | faceLabel.equals("Iskar") | faceLabel.equals("Andi")| faceLabel.equals("Lisa")) {

                                        ihilf = 0;
                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Hallo " + faceLabel);

                                        try {
                                            Position.winken(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } else if (WordRecognizedEvent.iFunktion == 2 && ihilf >= 20) {

                                    ihilf = 0;
                                    alMemory1.unsubscribeToEvent(faceID);
                                    alFaceDetection.unsubscribe("Face");
                                    alTextToSpeech.say("Hallo");

                                }
                                if (((WordRecognizedEvent.iFunktion == 4) | (Folgen.prob==true)) && WordRecognizedEvent.iFunktion==4) {

                                    if (faceLabel.equals("Andi")) {

                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Folgen.folgenan=true;
                                        Folgen.suche=true;

                                        try {
                                            f.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                                if (((WordRecognizedEvent.iFunktion == 5) | (Folgen.prob==true)) && WordRecognizedEvent.iFunktion==5) {

                                    if (faceLabel.equals("Stefan")) {

                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Folgen.folgenan=true;
                                        Folgen.suche=true;

                                        try {
                                            f.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                                if (((WordRecognizedEvent.iFunktion == 6) | (Folgen.prob==true)) && WordRecognizedEvent.iFunktion==6) {

                                    if (faceLabel.equals("Iskar")) {

                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Folgen.folgenan = true;
                                        Folgen.suche = true;

                                        try {
                                            f.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                                if (((WordRecognizedEvent.iFunktion == 7) | (Folgen.prob==true)) && WordRecognizedEvent.iFunktion==7) {

                                    if (faceLabel.equals("Lisa")) {

                                        alMemory1.unsubscribeToEvent(faceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Folgen.folgenan=true;
                                        Folgen.suche=true;

                                        try {
                                            f.run(session);
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