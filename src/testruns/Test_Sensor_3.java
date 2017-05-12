package testruns;

import com.aldebaran.qi.helper.ALProxy;
import com.aldebaran.qi.helper.proxies.ALBodyTemperature;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSensors;
import utillities.Uts;

import java.util.ArrayList;

/**
 * Created by JNaoHSBO on 02.05.2017.
 */
public class Test_Sensor_3 {

    public static void main(String[] args) throws Exception {
        Uts.AppStart();

        ALBodyTemperature alBodyTemperature = new ALBodyTemperature(Uts.getSESSION());
        System.out.println(alBodyTemperature.getTemperatureDiagnosis());
        ALMemory alMemory = new ALMemory(Uts.getSESSION());
        alMemory.subscribeToEvent("HotDeviceDetected", arg0 ->{System.out.println(arg0);});


        Uts.AppStop();
    }
}
