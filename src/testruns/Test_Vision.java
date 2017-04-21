package testruns;

import com.aldebaran.qi.Application;
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
    boolean lhilf=true;
    boolean ahilf=true;
    boolean ihilf=true;
    boolean shilf=true;

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

                    if(!faceLabel.equals("")){
                        System.out.println(faceLabel);
                        if(lhilf){
                            if (faceLabel.equals("Lisa")){
                                lhilf=false;
                                alTextToSpeech.say("Hallo " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if(ahilf){
                            if (faceLabel.equals("Andi")){
                                ahilf=false;
                                alTextToSpeech.say("Hallo " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if(ihilf){
                            if (faceLabel.equals("Iskar")){
                                ihilf=false;
                                alTextToSpeech.say("Hallo " + faceLabel);
                                try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if(shilf){
                            if (faceLabel.equals("Stefan")){
                                shilf=false;
                                alTextToSpeech.say("Hallo " + faceLabel);try {
                                    Position.winken();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else{
                            alFaceDetection.unsubscribe("Test");
                            a.setTrackingEnabled(false);
                        }
                    }
                }
            });
    }
}