package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALBarcodeReader;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;
import utillities.Utts;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Lisa on 05.04.2017.
 */
public class Test_Vision {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        ALFaceDetection a = new ALFaceDetection(Utts.getAPP().session());
        a.setTrackingEnabled(false);
        Test_Vision sensor = new Test_Vision();
        sensor.run(Utts.getAPP().session());
        Utts.getAPP().run();
    }

    ALMemory alMemory;
    ALBarcodeReader alBarcodeReader;
    boolean hilf= true;

    public void run(Session session) throws Exception {
        alMemory = new ALMemory(session);
        alBarcodeReader = new ALBarcodeReader(session);
        ALMotion a = new ALMotion(session);

        alBarcodeReader.subscribe("Test",10000,0.0f);
        alMemory.subscribeToEvent(
                "BarcodeReader/BarcodeDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object o) throws InterruptedException, CallError {
                        ArrayList codeData = (ArrayList)o;
                        ArrayList data = (ArrayList) (codeData.get(0));
                        String text = (String)(data.get(0));
                        Scanner sc = new Scanner(text).useDelimiter("-");
                        String x = sc.next();
                        String y = sc.next();
                        String z = sc.next();
                        System.out.println("fx:"+x+" "+y+" "+z);
                        sc.close();
                        float fx = Float.parseFloat(x);
                        float fy = Float.parseFloat(y);
                        float fz = Float.parseFloat(z);
                        System.out.println("fx:"+fx+" "+fy+" "+fz);
                        a.moveTo((float)fx,(float)fy,(float)fz);
                        System.out.println("Ende");

                        try {
                            Utts.talk(text);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}