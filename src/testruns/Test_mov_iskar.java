package testruns;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.*;
import utillities.Utts;
import movings_Andi_Iskar.Position;

import java.util.ArrayList;


/**
 * Created by iskar on 06.04.17.
 */

public class Test_mov_iskar {

    public static void main (String [] args) throws Exception {
        Utts.AppStart();

        ALMotion bew = new ALMotion(Utts.getSESSION());

        bew.wakeUp();
       // bew.stopMove();
        //bew.rest();
        ALRobotPosture p= new ALRobotPosture(Utts.getAPP().session());
        p.goToPosture("Stand",0.5f);
        Thread.sleep(1000);
        ArrayList name =new ArrayList<String>();
        ArrayList angle = new ArrayList<Float>();
        ArrayList time = new ArrayList<Float>();
        name.add(0,"RShoulderPitch");
        name.add(1,"LShoulderPitch");
        angle.add(0,0);
        angle.add(1,0);
        time.add(0,1f);
        time.add(1,1f);
        bew.setStiffnesses("RShoulderPitch",1.0f);
        bew.setStiffnesses("LShoulderPitch",1.0f);
        bew.angleInterpolation(name,angle,time,true);
        Thread.sleep(5000);
        bew.setStiffnesses("RShoulderPitch",0f);
        bew.setStiffnesses("LShoulderPitch",0f);

    }
}
