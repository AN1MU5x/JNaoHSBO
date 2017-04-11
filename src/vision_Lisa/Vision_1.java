package vision_Lisa;

import com.aldebaran.qi.helper.proxies.ALBattery;
import utillities.Utts;

/**
 * Created by Lisa on 07.04.2017.
 */
public class Vision_1 {
    public static void main(String[] args)throws Exception{
        Utts.AppStart();
        ALBattery a = new ALBattery(Utts.APP.session());

        System.out.println(a.getBatteryCharge());
    }
}
