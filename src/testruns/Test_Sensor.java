package testruns;

import com.aldebaran.qi.helper.proxies.ALBattery;
import utillities.Utts;

/**
 * Created by Lisa on 24.04.2017.
 */
public class Test_Sensor {
    public static void main()throws Exception{
        Utts.AppStart();

        ALBattery a = new ALBattery(Utts.getAPP().session());
        System.out.println(a);
    }
}
