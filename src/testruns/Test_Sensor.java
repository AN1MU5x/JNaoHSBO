package testruns;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.*;
import utillities.Utts;
import com.aldebaran.qi.helper.proxies.ALMotion;
import java.util.ArrayList;

/**
 * Created by Lisa on 24.04.2017.
 */
public class Test_Sensor {

    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        Test_Sensor sensor = new Test_Sensor();
        sensor.run(Utts.getAPP().session());
        Utts.getAPP().run();
    }



    public void run(Session session) throws Exception {

        ALTracker a = new ALTracker(session);
        ALMotion suche = new ALMotion(Utts.getSESSION());
/*
        ArrayList name =new ArrayList<String>();
        ArrayList name1 =new ArrayList<String>();
        ArrayList angles =new ArrayList<Float>();
        ArrayList angles1 =new ArrayList<Float>();
        ArrayList time =new ArrayList<Float>();
        ArrayList time1 =new ArrayList<Float>();
        name.add(0,"HeadYaw");
        //name.add(1,"HeadPitch");
        name1.add(0,"HeadYaw");
        //name1.add(1,"HeadPitch");
        angles.add(0,Utts.DegToRad(-30));
        //angles.add(1,Utts.DegToRad(0));
        angles1.add(0,Utts.DegToRad(30));
        //angles1.add(1,Utts.DegToRad(0));
        time.add(0,1.5f);
        //time.add(1,1f);
        time1.add(0,3.f);
        //time1.add(1,1f);
        //suche.setStiffnesses("Head",1.0f);*/

        boolean b = true;
        while(b){
            a.track("Face");
            a.setMode("Move");
            //a.track("Face");
            //suche.angleInterpolation(name,angles,time,true);
            //suche.angleInterpolation(name1,angles1,time1,true);
            System.out.println(a.isSearchEnabled());
            Thread.sleep(1000);
            System.out.println(a.getTargetPosition().size());
            //if(a.getTargetPosition().size() > 0) {
              //  suche.moveTo(a.getTargetPosition());

            //}

        }

        System.out.println(a.getSupportedTargets());
        System.out.println(a.getTargetPosition());
        //a.toggleSearch(false);

    }
}



//Thread.sleep(5000);
//System.out.println(a.isSearchEnabled());
//System.out.println(a.getTargetPosition().get(0));