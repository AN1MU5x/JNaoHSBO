package testruns;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import utillities.Utts;



/**
 * Created by Lisa on 05.04.2017.
 */
public class Test_Vision{

    public static void main(String[] args)throws Exception{

        Utts.AppStart();
        ALFaceDetection a = new ALFaceDetection(Utts.APP.session());
        a.setTrackingEnabled(true);

        Test_Vision test_vision = new Test_Vision();
        test_vision.run(Utts.APP.session());
        Utts.APP.run();

    }

    ALMemory memory;
    long recID;

    public void run(Session session) throws Exception {
        memory = new ALMemory(session);
        recID = memory.subscribeToEvent("FaceDetected", args0 ->{

        });
    }
}