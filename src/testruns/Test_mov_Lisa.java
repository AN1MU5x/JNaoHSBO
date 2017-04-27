package testruns;

import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import utillities.Utts;

import java.util.ArrayList;

/**
 * Created by Lisa on 26.04.2017.
 */
public class Test_mov_Lisa {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();

        ALMotion anfangsschritt =new ALMotion(Utts.getSESSION());

        //Grundstellung
        ALRobotPosture p= new ALRobotPosture(Utts.getAPP().session());
        p.goToPosture("Stand",0.5f);
        Thread.sleep(2000);

        //Erster Schritt
        ArrayList bewegung1 =new ArrayList<String>();
        ArrayList winkel1 =new ArrayList<Float>();
        ArrayList zeitpunkt1 =new ArrayList<Float>();
        bewegung1.add(0,"LHipRoll");
        bewegung1.add(1,"RHipRoll");
        winkel1.add(0,Utts.DegToRad(-4));
        winkel1.add(1,Utts.DegToRad(4));
        zeitpunkt1.add(0,2.f);
        zeitpunkt1.add(1,2.f);
        anfangsschritt.setStiffnesses("LLeg",1.0f);
        anfangsschritt.setStiffnesses("RLeg",1.0f);
        anfangsschritt.setStiffnesses("LAnkleRoll",0.0f);
        anfangsschritt.setStiffnesses("RAnkleRoll",0.0f);
        anfangsschritt.angleInterpolation(bewegung1,winkel1,zeitpunkt1,true);

        //Zweiter Schritt
        ArrayList bewegung2 =new ArrayList<String>();
        ArrayList winkel2 =new ArrayList<Float>();
        ArrayList zeitpunkt2 =new ArrayList<Float>();
        bewegung2.add(0,"LKneePitch");
        bewegung2.add(1,"RKneePitch");
        bewegung2.add(2,"LHipPitch");
        bewegung2.add(3,"RHipPitch");
        bewegung2.add(4,"LAnklePitch");
        bewegung2.add(5,"RAnklePitch");
        winkel2.add(0,Utts.DegToRad(10));
        winkel2.add(1,Utts.DegToRad(10));
        winkel2.add(2,Utts.DegToRad(10));
        winkel2.add(3,Utts.DegToRad(10));
        winkel2.add(4,Utts.DegToRad(-10));
        winkel2.add(5,Utts.DegToRad(-10));
        zeitpunkt2.add(0,5.4f);
        zeitpunkt2.add(1,5.4f);
        zeitpunkt2.add(2,5.4f);
        zeitpunkt2.add(3,5.4f);
        zeitpunkt2.add(4,5.4f);
        zeitpunkt2.add(5,5.4f);
        anfangsschritt.setStiffnesses("LAnkleRoll",0.0f);
        anfangsschritt.setStiffnesses("RAnkleRoll",0.0f);
        anfangsschritt.angleInterpolation(bewegung2,winkel2,zeitpunkt2,true);

        //Dritter Schritt
        ArrayList bewegung3 =new ArrayList<String>();
        ArrayList winkel3 =new ArrayList<Float>();
        ArrayList zeitpunkt3 =new ArrayList<Float>();
        bewegung3.add(0,"LHipRoll");
        bewegung3.add(1,"RHipRoll");
        winkel3.add(0,Utts.DegToRad(10));
        winkel3.add(1,Utts.DegToRad(10));
        zeitpunkt3.add(0,3.0f);
        zeitpunkt3.add(1,3.0f);
        anfangsschritt.setStiffnesses("LAnkleRoll",0.0f);
        anfangsschritt.angleInterpolation(bewegung3,winkel3,zeitpunkt3,true);

        //Vierter Schritt
        ArrayList bewegung4 =new ArrayList<String>();
        ArrayList winkel4 =new ArrayList<Float>();
        ArrayList zeitpunkt4 =new ArrayList<Float>();
        bewegung4.add(0,"RHipRoll");
        bewegung4.add(1,"LHipRoll");
        bewegung4.add(2,"LHipPitch");
        bewegung4.add(3,"LShoulderPitch");
        winkel4.add(0,Utts.DegToRad(13));
        winkel4.add(1,Utts.DegToRad(10));
        winkel4.add(2,Utts.DegToRad(-40));
        winkel4.add(3,Utts.DegToRad(0));
        zeitpunkt4.add(0,7f);
        zeitpunkt4.add(1,7f);
        zeitpunkt4.add(2,7f);
        zeitpunkt4.add(3,7f);
        anfangsschritt.setStiffnesses("LAnkleRoll",1.0f);
        anfangsschritt.setStiffnesses("RAnkleRoll",1.0f);
        anfangsschritt.setStiffnesses("LKneePitch",0.0f);
        anfangsschritt.angleInterpolation(bewegung4,winkel4,zeitpunkt4,true);

        //Fünfter Schritt
        ArrayList bewegung5 =new ArrayList<String>();
        ArrayList winkel5 =new ArrayList<Float>();
        ArrayList zeitpunkt5 =new ArrayList<Float>();
        bewegung5.add(0,"RAnklePitch");
        bewegung5.add(1,"RHipRoll");
        bewegung5.add(2,"LHipRoll");
        bewegung5.add(3,"LHipPitch");
        bewegung5.add(4,"RHipPitch");
        bewegung5.add(5,"RKneePitch");
        bewegung5.add(6,"LAnklePitch");
        winkel5.add(0,Utts.DegToRad(-30));
        winkel5.add(1,Utts.DegToRad(13));
        winkel5.add(2,Utts.DegToRad(10));
        winkel5.add(3,Utts.DegToRad(-30));
        winkel5.add(4,Utts.DegToRad(10));
        winkel5.add(5,Utts.DegToRad(20));
        winkel5.add(6,Utts.DegToRad(30));
        zeitpunkt5.add(0,5.f);
        zeitpunkt5.add(1,5.f);
        zeitpunkt5.add(2,5.f);
        zeitpunkt5.add(3,5.f);
        zeitpunkt5.add(4,5.f);
        zeitpunkt5.add(5,5.f);
        zeitpunkt5.add(6,5.f);
        anfangsschritt.setStiffnesses("LAnkleRoll",0.0f);
        anfangsschritt.setStiffnesses("LAnklePitch",0.0f);
        anfangsschritt.angleInterpolation(bewegung5,winkel5,zeitpunkt5,true);

    /*    //Sechser Schritt
        ArrayList bewegung6 =new ArrayList<String>();
        ArrayList winkel6 =new ArrayList<Float>();
        ArrayList zeitpunkt6 =new ArrayList<Float>();
        bewegung6.add(0,"RHipRoll");
        bewegung6.add(1,"LHipRoll");
        winkel6.add(0,Utts.DegToRad(10));
        winkel6.add(1,Utts.DegToRad(10));
        zeitpunkt6.add(0,5.f);
        zeitpunkt6.add(1,5.f);
        anfangsschritt.setStiffnesses("LAnkleRoll",1.0f);
        anfangsschritt.setStiffnesses("LAnklePitch",1.0f);

        Thread.sleep(10000);*/
    }
}
