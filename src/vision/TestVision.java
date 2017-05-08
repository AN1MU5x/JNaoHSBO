package vision;

import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import utillities.Utts;

/**
 * Created by Lisa on 30.04.2017.
 */
public class TestVision {
    private static FaceDetectedEvent m_v1 = new FaceDetectedEvent();
    private static HumanTrackedEvent m_ht1 = new HumanTrackedEvent();
    private static boolean m_xEnd = false;
    private static ALFaceDetection a;

    public static void main(String[] args) throws Exception
    {
            //Roboter verbinden
            Utts.AppStart();
            System.out.println("Successfully connected to the robot");

            //WordRecognizion
            m_ht1.run(Utts.getAPP().session());
            Utts.getAPP().run();

            do {
                //Warte auf HumanTrackedEvent

            } while (!m_xEnd);
    }
}
