package testruns;

import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALVisionRecognition;
import utillities.Utts;

/**
 * Created by Lisa on 05.04.2017.
 */
public class Test_Vision {

    public static void main(String[] args) throws Exception {

        Utts.AppStart();

        ALFaceDetection a = new ALFaceDetection(Utts.APP.session());
        a.setRecognitionEnabled(true);





    }

    public static boolean learnFace(String sName) throws Exception {
        ALFaceDetection oA = new ALFaceDetection(Utts.APP.session());
        if(oA.learnFace(sName)){
            return(true);
        }
        else{
            return(false);
        }
    }

}
