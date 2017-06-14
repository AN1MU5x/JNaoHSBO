package testruns;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTracker;
import vision.FaceDetectedEvent;

public class Test_folge {
    public static ALTracker a;
    public static boolean suche=false;
    public static boolean folgenan=false;
    public static boolean prob=false;
    FaceDetectedEvent FDE ;
    public void run(Session session) throws Exception{

        a = new ALTracker(session);
        FDE =new FaceDetectedEvent();

        while (suche) {

            if (folgenan) {

                if ((a.isTargetLost() && WordRecognizedEvent.iFunktion==4)|(a.isTargetLost() && WordRecognizedEvent.iFunktion==6)) {

                    folgenan = false;
                    a.stopTracker();
                    FDE.run(session);
                    prob=true;
                    suche=false;

                }
                else if((folgenan && WordRecognizedEvent.iFunktion==4)|(folgenan && WordRecognizedEvent.iFunktion==6)){

                    a.track("Face");
                    a.setMode("Move");
                    prob=false;

                }

            }
        }
    }
}
