package testruns;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import utillities.Utts;



/**
 * Created by Lisa on 05.04.2017.
 */
public class Test_Vision{
    public static void main(String[] args)throws Exception {

        String robotUrl = "tcp://Emma.local:9559";
        Application application = new Application(args, robotUrl);
        application.start();
        System.out.println("Successfully connected to the robot");

        ALFaceDetection a = new ALFaceDetection(Utts.APP.session());
        a.setRecognitionEnabled(true);




    }

    ALMemory memory;
    ALFaceDetection fd;
    long faceDetectedID;

    public void run(Session session) throws Exception {

        memory = new ALMemory(session);
        fd = new ALFaceDetection(session);
        faceDetectedID = 0;

        faceDetectedID = memory.subscribeToEvent(
                "FaceDetected", new EventCallback<Float>() {
                    @Override
                    public void onEvent(Float arg0) throws InterruptedException, CallError {

                        if (arg0 > 0) {
                            try {
                                Utts.talk("Hello");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}