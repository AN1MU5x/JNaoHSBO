package vision_Lisa;

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
 * Created by Lisa on 07.04.2017.
 */
public class Vision_1 {
    static ALFaceDetection a;
    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        a = new ALFaceDetection(Utts.getAPP().session());
        a.setTrackingEnabled(true);
        Vision_1 vision = new Vision_1();
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
                        int i=0;
                        while(i<500&&hilf1) {
                            System.out.println("Face detected");
                            ArrayList faceDetected = (ArrayList) o;
                            ArrayList faceInfoList = (ArrayList) (faceDetected.get(1));
                            ArrayList faceInfo = (ArrayList) (faceInfoList.get(0));
                            ArrayList extraInfo = (ArrayList) (faceInfo.get(1));
                            String faceLabel = (String) (extraInfo.get(2));

                            Thread.sleep(4000);
                            if (!faceLabel.equals("") && hilf1) {
                                System.out.println(faceLabel);
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
                            i++;
                        }
                        if(hilf1){
                            hilf1=false;
                            alTextToSpeech.say("Hallo");
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
                });
    }

}
