package motion;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTracker;
import vision.FaceDetectedEvent;

public class Follow {
    public static ALTracker alTracker;
    public static boolean bSearch = false;
    public static boolean bFollowOn = false;
    public static boolean bTargetLost = false;
    private FaceDetectedEvent faceDetectedEvent;

    public void run(Session session) throws Exception{

        alTracker = new ALTracker(session);
        faceDetectedEvent =new FaceDetectedEvent();

        while (bSearch) {
            if (bFollowOn) {
                if ((alTracker.isTargetLost() && WordRecognizedEvent.iFunktion==4) || (alTracker.isTargetLost() && WordRecognizedEvent.iFunktion==5) || (alTracker.isTargetLost() && WordRecognizedEvent.iFunktion==6) || (alTracker.isTargetLost() && WordRecognizedEvent.iFunktion==7)) {
                    bFollowOn = false;
                    alTracker.stopTracker();
                    faceDetectedEvent.run(session);
                    bTargetLost =true;
                    bSearch =false;
                }
                else if((bFollowOn && WordRecognizedEvent.iFunktion==4) | (bFollowOn && WordRecognizedEvent.iFunktion==5) | (bFollowOn && WordRecognizedEvent.iFunktion==6) | (bFollowOn && WordRecognizedEvent.iFunktion==7)){
                    alTracker.track("Face");
                    alTracker.setMode("Move");
                    bTargetLost =false;
                }

            }
        }
    }
}