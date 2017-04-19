package testruns;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import java.util.ArrayList;

/**
 * Created by Lisa on 05.04.2017.
 */
public class Test_Vision {

    public static void main(String[] args) throws Exception {
        String robotUrl = "tcp://Emma.local:9559";
        Application application = new Application(args, robotUrl);
        application.start();
        System.out.println("Successfully connected to the robot");
        Test_Vision vision = new Test_Vision();
        vision.run(application.session());
        application.run();
    }
    ALMemory alMemory;
    ALTextToSpeech alTextToSpeech;
    ALFaceDetection alFaceDetection;

    public void run(Session session) throws Exception {

        alMemory = new ALMemory(session);
        alTextToSpeech = new ALTextToSpeech(session);
        alFaceDetection = new ALFaceDetection(session);

        alFaceDetection.subscribe("Test",500,0.0f);
        alMemory.subscribeToEvent(
            "FaceDetected", new EventCallback() {
                @Override
                public void onEvent(Object o) throws InterruptedException, CallError {
                    System.out.println("Face detected");
                    ArrayList facedetected = (ArrayList)o;



                }
            });
    }
}