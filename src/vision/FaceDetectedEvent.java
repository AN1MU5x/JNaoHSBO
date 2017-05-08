package vision;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import motion.Position;

import java.util.ArrayList;
/**
 * Created by Lisa on 07.04.2017.
 */
public class FaceDetectedEvent {

    ALMemory alMemory;
    ALTextToSpeech alTextToSpeech;
    ALFaceDetection alFaceDetection;
    private boolean hilf1=true;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);
        alFaceDetection = new ALFaceDetection(session);

        alFaceDetection.subscribe("Test",1,0.0f);
        alMemory.subscribeToEvent(
                "FaceDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        System.out.println("Face detected");
                        ArrayList faceDetected = (ArrayList) o;
                        ArrayList faceInfoList = (ArrayList) (faceDetected.get(1));
                        ArrayList faceInfo = (ArrayList) (faceInfoList.get(0));
                        ArrayList extraInfo = (ArrayList) (faceInfo.get(1));
                        String faceLabel = (String) (extraInfo.get(2));
                        System.out.println(faceLabel);
                        if(WordRecognizedEvent.wbi) {
                            if (faceLabel.equals("Koch") && hilf1) {
                                hilf1 = false;
                                alTextToSpeech.say("Herr " + faceLabel);
                                alFaceDetection.unsubscribe("Test");
                            }
                            if (faceLabel.equals("Brabender") && hilf1) {
                                hilf1 = false;
                                alTextToSpeech.say("Frau " + faceLabel);
                                alFaceDetection.unsubscribe("Test");
                            }
                            if (faceLabel.equals("Stefan") && hilf1 || faceLabel.equals("Iskar") && hilf1 || faceLabel.equals("Andi") && hilf1 || faceLabel.equals("Lisa") && hilf1) {
                                hilf1 = false;
                                alTextToSpeech.say(faceLabel);
                                alFaceDetection.unsubscribe("Test");
                            }
                        }
                        else if(WordRecognizedEvent.hallo){
                            if (faceLabel.equals("Koch") && hilf1) {
                                hilf1 = false;
                                alTextToSpeech.say("Hallo Herr " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                alFaceDetection.unsubscribe("Test");
                            }
                            if (faceLabel.equals("Brabender") && hilf1) {
                                hilf1 = false;
                                alTextToSpeech.say("Hallo Frau " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                alFaceDetection.unsubscribe("Test");
                            }
                            if (faceLabel.equals("Stefan") && hilf1 || faceLabel.equals("Iskar") && hilf1 || faceLabel.equals("Andi") && hilf1 || faceLabel.equals("Lisa") && hilf1) {
                                hilf1 = false;
                                alTextToSpeech.say("Hallo "+faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                alFaceDetection.unsubscribe("Test");
                            }
                        }
                    }
                });
    }
}
