package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import movings_Andi_Iskar.Position;
import utillities.Utts;

import java.util.ArrayList;

/**
 * Created by Lisa on 05.04.2017.
 */
public class Test_Vision {

    static ALFaceDetection a;
    public static void main(String[] args) throws Exception {
       Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        a = new ALFaceDetection(Utts.getAPP().session());
        a.setTrackingEnabled(true);
        Test_Vision vision = new Test_Vision();
        vision.run(Utts.getAPP().session());
        Utts.getAPP().run();
    }
    ALMemory alMemory;
    ALTextToSpeech alTextToSpeech;
    ALFaceDetection alFaceDetection;
   private boolean hilf1=true;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);
        alFaceDetection = new ALFaceDetection(session);

        alFaceDetection.subscribe("Test",10000,0.0f);
        alMemory.subscribeToEvent(
            "FaceDetected", new EventCallback() {
                @Override
                public void onEvent(Object o) throws InterruptedException, CallError {
                    System.out.println("Face detected");
                    ArrayList faceDetected = (ArrayList)o;
                    ArrayList faceInfoList =(ArrayList) (faceDetected.get(1));
                    ArrayList faceInfo = (ArrayList) (faceInfoList.get(0));
                    ArrayList extraInfo = (ArrayList) (faceInfo.get(1));
                    String faceLabel = (String) (extraInfo.get(2));

                    if(!faceLabel.equals("")&&hilf1) {
                        System.out.println(faceLabel);
                        if (faceLabel.equals("Lisa")) {
                            hilf1 = false;
                            alTextToSpeech.say("Hallo " + faceLabel);
                            try {
                                Position.winken();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            alFaceDetection.unsubscribe("Test");
                            a.setTrackingEnabled(false);
                            Utts.AppStop();
                        }
                        if (faceLabel.equals("Andi")) {
                            hilf1 = false;
                            alTextToSpeech.say("Hallo " + faceLabel);
                            try {
                                Position.winken();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            alFaceDetection.unsubscribe("Test");
                            a.setTrackingEnabled(false);
                            Utts.AppStop();
                        }
                        if (faceLabel.equals("Iskar")) {
                            hilf1 = false;
                            alTextToSpeech.say("Hallo " + faceLabel);
                            try {
                                Position.winken();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            alFaceDetection.unsubscribe("Test");
                            a.setTrackingEnabled(false);
                            Utts.AppStop();
                        }
                        if (faceLabel.equals("Stefan")) {
                            hilf1 = false;
                            alTextToSpeech.say("Hallo " + faceLabel);
                            try {
                                Position.winken();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            alFaceDetection.unsubscribe("Test");
                            a.setTrackingEnabled(false);
                            Utts.AppStop();
                        }
                    }
                }
            });
    }
}