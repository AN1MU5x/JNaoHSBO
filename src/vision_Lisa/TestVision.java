package vision_Lisa;

import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import utillities.Utts;

/**
 * Created by Lisa on 30.04.2017.
 */
public class TestVision {
    private static Vision_1 m_v1 = new Vision_1();
    private static HumanTracked m_ht1 = new HumanTracked();
    private static boolean m_xEnd = false;
    private static ALFaceDetection a;

    public static void main(String[] args) throws Exception
    {
        //Roboter verbinden
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");

        //HumanTracked starten
        m_ht1.run(Utts.getAPP().session());
        Utts.getAPP().run();

        do {
            //Warte auf HumanTracked
            if(m_ht1.xHumanTracked) {
                a = new ALFaceDetection(Utts.getAPP().session());
                a.setTrackingEnabled(true);
                //Gesichtserkennung starten
                m_ht1.xHumanTracked = false;
                m_v1.run(Utts.getAPP().session());
                Utts.getAPP().run();
            }
            Thread.sleep(10);
        }while(!m_xEnd);
    }
}
