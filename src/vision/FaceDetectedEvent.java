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
   private boolean bOneUse = true;
   private int iCounter = 0;

   Follow follow ;

   public void run(Session session) throws Exception {

        alMemory1 = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);
        alFaceDetection = new ALFaceDetection(session);
        follow = new Follow();
        alFaceDetection.subscribe("Face", 500, 0.0f);

        lFaceID = alMemory1.subscribeToEvent(
                "FaceDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {

                        iCounter++;
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
                                if (WordRecognizedEvent.iFunktion == 1 && iCounter < 20) {

                                    if (faceLabel.equals("Koch") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Herr " + faceLabel);
                                    }
                                    if (faceLabel.equals("Brabender") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Frau " + faceLabel);
                                    }
                                    if (faceLabel.equals("Stefan") && bOneUse || faceLabel.equals("Iskar") && bOneUse || faceLabel.equals("Andi") && bOneUse || faceLabel.equals("Lisa") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say(faceLabel);
                                    }
                                }
                                else if (WordRecognizedEvent.iFunktion == 1 && (iCounter >= 20)) {

                                    iCounter = 0;
                                    alMemory1.unsubscribeToEvent(lFaceID);
                                    alFaceDetection.unsubscribe("Face");
                                    alTextToSpeech.say("Keine Ahnung");
                                }

                                //Hallo
                                else if (WordRecognizedEvent.iFunktion == 2 && iCounter < 20) {

                                    if (faceLabel.equals("Koch") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Hallo Herr " + faceLabel);
                                        try {
                                            Position.winken(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (faceLabel.equals("Brabender") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Hallo Frau " + faceLabel);
                                        try {
                                            Position.winken(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (faceLabel.equals("Stefan") && bOneUse || faceLabel.equals("Iskar") && bOneUse || faceLabel.equals("Andi") && bOneUse|| faceLabel.equals("Lisa") && bOneUse) {

                                        bOneUse = false;
                                        iCounter = 0;
                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        alTextToSpeech.say("Hallo " + faceLabel);
                                        try {
                                            Position.winken(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                else if (WordRecognizedEvent.iFunktion == 2 && iCounter >= 20) {

                                    iCounter = 0;
                                    alMemory1.unsubscribeToEvent(lFaceID);
                                    alFaceDetection.unsubscribe("Face");
                                    alTextToSpeech.say("Hallo");
                                }

                                //Suche Andi
                                if (((WordRecognizedEvent.iFunktion == 4) || (Follow.bTargetLost ==true)) && WordRecognizedEvent.iFunktion==4) {

                                    if (faceLabel.equals("Andi")) {

                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Follow.bFollowOn =true;
                                        Follow.bSearch =true;
                                        try {
                                            follow.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                //Suche Stefan
                                if (((WordRecognizedEvent.iFunktion == 5) || (Follow.bTargetLost ==true)) && WordRecognizedEvent.iFunktion==5) {

                                    if (faceLabel.equals("Stefan")) {

                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Follow.bFollowOn =true;
                                        Follow.bSearch =true;
                                        try {
                                            follow.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                //Suche Iskar
                                if (((WordRecognizedEvent.iFunktion == 6) || (Follow.bTargetLost ==true)) && WordRecognizedEvent.iFunktion==6) {

                                    if (faceLabel.equals("Iskar")) {

                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Follow.bFollowOn = true;
                                        Follow.bSearch = true;
                                        try {
                                            follow.run(session);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                //Suche Lisa
                                if (((WordRecognizedEvent.iFunktion == 7) || (Follow.bTargetLost ==true)) && WordRecognizedEvent.iFunktion==7) {

                                    if (faceLabel.equals("Lisa")) {

                                        alMemory1.unsubscribeToEvent(lFaceID);
                                        alFaceDetection.unsubscribe("Face");
                                        Follow.bFollowOn =true;
                                        Follow.bSearch =true;
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