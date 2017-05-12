package testruns;

import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import utillities.Uts;

import java.util.ArrayList;

public class Test_mov_Lisa {
    public static void main(String[] args) throws Exception {
        Uts.AppStart();

        ALMotion anfangsschritt =new ALMotion(Uts.getSESSION());

        anfangsschritt.wakeUp();
        anfangsschritt.stopMove();

        //Grundstellung
        ALRobotPosture p= new ALRobotPosture(Uts.getAPP().session());
        p.goToPosture("Stand",0.5f);
        Thread.sleep(2000);

        Uts.talk("Bitte festhalten");

        //Erster Schritt
        ArrayList bewegung1 =new ArrayList<String>();
        ArrayList winkel1 =new ArrayList<Float>();
        ArrayList zeitpunkt1 =new ArrayList<Float>();
        bewegung1.add(0,"LHipRoll");
        bewegung1.add(1,"RHipRoll");
        winkel1.add(0,Uts.DegToRad(-5));
        winkel1.add(1,Uts.DegToRad(5));
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
        winkel2.add(0,Uts.DegToRad(5));
        winkel2.add(1,Uts.DegToRad(5));
        winkel2.add(2,Uts.DegToRad(10));
        winkel2.add(3,Uts.DegToRad(10));
        winkel2.add(4,Uts.DegToRad(-10));
        winkel2.add(5,Uts.DegToRad(-10));
        zeitpunkt2.add(0,5.4f);
        zeitpunkt2.add(1,5.4f);
        zeitpunkt2.add(2,5.4f);
        zeitpunkt2.add(3,5.4f);
        zeitpunkt2.add(4,5.4f);
        zeitpunkt2.add(5,5.4f);
        anfangsschritt.setStiffnesses("LAnkleRoll",0.0f);
        anfangsschritt.setStiffnesses("RAnkleRoll",0.0f);
        //anfangsschritt.angleInterpolation(bewegung2,winkel2,zeitpunkt2,true);

        //Dritter Schritt
        ArrayList bewegung3 =new ArrayList<String>();
        ArrayList winkel3 =new ArrayList<Float>();
        ArrayList zeitpunkt3 =new ArrayList<Float>();
        bewegung3.add(0,"LHipRoll");
        bewegung3.add(1,"RHipRoll");
        winkel3.add(0,Uts.DegToRad(10));
        winkel3.add(1,Uts.DegToRad(10));
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
        bewegung4.add(4,"LKneePitch");
        winkel4.add(0,Uts.DegToRad(13));
        winkel4.add(1,Uts.DegToRad(10));
        winkel4.add(2,Uts.DegToRad(-40));
        winkel4.add(3,Uts.DegToRad(0));
        winkel4.add(4,Uts.DegToRad(40));
        zeitpunkt4.add(0,7f);
        zeitpunkt4.add(1,7f);
        zeitpunkt4.add(2,7f);
        zeitpunkt4.add(3,7f);
        zeitpunkt4.add(4,7f);
        anfangsschritt.setStiffnesses("LAnkleRoll",1.0f);
        anfangsschritt.setStiffnesses("RAnkleRoll",1.0f);
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
        bewegung5.add(7,"LShoulderPitch");
        winkel5.add(0,Uts.DegToRad(-30));
        winkel5.add(1,Uts.DegToRad(13));
        winkel5.add(2,Uts.DegToRad(10));
        winkel5.add(3,Uts.DegToRad(-30));
        winkel5.add(4,Uts.DegToRad(2));
        winkel5.add(5,Uts.DegToRad(40));
        winkel5.add(6,Uts.DegToRad(30));
        winkel5.add(7,Uts.DegToRad(0));
        zeitpunkt5.add(0,5.f);
        zeitpunkt5.add(1,5.f);
        zeitpunkt5.add(2,5.f);
        zeitpunkt5.add(3,5.f);
        zeitpunkt5.add(4,5.f);
        zeitpunkt5.add(5,5.f);
        zeitpunkt5.add(6,5.f);
        zeitpunkt5.add(7,5.f);
        anfangsschritt.setStiffnesses("LAnkleRoll",0.0f);
        anfangsschritt.setStiffnesses("LAnklePitch",0.0f);
        anfangsschritt.angleInterpolation(bewegung5,winkel5,zeitpunkt5,true);

        //Sechser Schritt
        ArrayList bewegung6 =new ArrayList<String>();
        ArrayList winkel6 =new ArrayList<Float>();
        ArrayList zeitpunkt6 =new ArrayList<Float>();
        bewegung6.add(0,"LKneePitch");
        bewegung6.add(0,"RHipRoll");
        bewegung6.add(1,"LHipRoll");
        bewegung6.add(3,"RKneePitch");
        bewegung6.add(4,"RAnkleRoll");
        bewegung6.add(5,"LShoulderPitch");
        winkel6.add(0,Uts.DegToRad(25));
        winkel6.add(1,Uts.DegToRad(17));
        winkel6.add(2,Uts.DegToRad(17));
        winkel6.add(3,Uts.DegToRad(35));
        winkel6.add(4,Uts.DegToRad(2));
        winkel6.add(5,Uts.DegToRad(0));
        zeitpunkt6.add(0,5.f);
        zeitpunkt6.add(1,5.f);
        zeitpunkt6.add(2,5.f);
        zeitpunkt6.add(3,5.f);
        zeitpunkt6.add(4,5.f);
        zeitpunkt6.add(5,5.f);
        anfangsschritt.setStiffnesses("LAnkleRoll",1.0f);
        anfangsschritt.setStiffnesses("LAnklePitch",1.0f);
        anfangsschritt.setStiffnesses("LAnklePitch",0.f);
        anfangsschritt.angleInterpolation(bewegung6,winkel6,zeitpunkt6,true);

        //Siebter Schritt
        ArrayList bewegung7 =new ArrayList<String>();
        ArrayList winkel7 =new ArrayList<Float>();
        ArrayList zeitpunkt7 =new ArrayList<Float>();
        bewegung7.add(0,"LAnkleRoll");
        bewegung7.add(1,"RAnklePitch");
        bewegung7.add(2,"RKneePitch");
        bewegung7.add(3,"LHipPitch");
        bewegung7.add(4,"LHipRoll");
        bewegung7.add(5,"RHipRoll");
        bewegung7.add(6,"LShoulderPitch");
        bewegung7.add(7,"LKneePitch");
        bewegung7.add(8,"LAnklePitch");
        winkel7.add(0,Uts.DegToRad(-3));
        winkel7.add(1,Uts.DegToRad(-30));
        winkel7.add(2,Uts.DegToRad(30));
        winkel7.add(3,Uts.DegToRad(-25));
        winkel7.add(4,Uts.DegToRad(-10));
        winkel7.add(5,Uts.DegToRad(-10));
        winkel7.add(6,Uts.DegToRad(0));
        winkel7.add(7,Uts.DegToRad(20));
        winkel7.add(8,Uts.DegToRad(5));
        zeitpunkt7.add(0,5.f);
        zeitpunkt7.add(1,5.f);
        zeitpunkt7.add(2,5.f);
        zeitpunkt7.add(3,5.f);
        zeitpunkt7.add(4,5.f);
        zeitpunkt7.add(5,5.f);
        zeitpunkt7.add(6,5.f);
        zeitpunkt7.add(7,6.f);
        zeitpunkt7.add(8,6.f);
        anfangsschritt.setStiffnesses("LAnklePitch",1.f);
        anfangsschritt.setStiffnesses("LAnkleRoll",0.f);
        anfangsschritt.angleInterpolation(bewegung7,winkel7,zeitpunkt7,true);

        //Achter Schritt
        ArrayList bewegung8 =new ArrayList<String>();
        ArrayList winkel8 =new ArrayList<Float>();
        ArrayList zeitpunkt8 =new ArrayList<Float>();
        bewegung8.add(0,"LAnkleRoll");
        bewegung8.add(1,"RAnklePitch");
        bewegung8.add(2,"RKneePitch");
        bewegung8.add(3,"LHipPitch");
        bewegung8.add(4,"LHipRoll");
        bewegung8.add(5,"RHipRoll");
        bewegung8.add(6,"LShoulderPitch");
        bewegung8.add(7,"LKneePitch");
        bewegung8.add(8,"LAnklePitch");
        winkel8.add(0,Uts.DegToRad(8)); //name7.add(0,"LAnkleRoll");
        winkel8.add(1,Uts.DegToRad(-10)); //name7.add(1,"RAnklePitch");
        winkel8.add(2,Uts.DegToRad(30)); //name7.add(2,"RKneePitch");
        winkel8.add(3,Uts.DegToRad(-20));//name7.add(3,"LHipPitch");
        winkel8.add(4,Uts.DegToRad(-15));//name7.add(4,"LHipRoll");
        winkel8.add(5,Uts.DegToRad(-10));//name7.add(5,"RHipRoll");
        winkel8.add(6,Uts.DegToRad(0));//name7.add(6,"LShoulderPitch");
        winkel8.add(7,Uts.DegToRad(25));//name7.add(7,"LKneePitch");
        winkel8.add(8,Uts.DegToRad(-5));//name7.add(8,"LAnklePitch");
        zeitpunkt8.add(0,5.f);
        zeitpunkt8.add(1,5.f);
        zeitpunkt8.add(2,5.f);
        zeitpunkt8.add(3,5.f);
        zeitpunkt8.add(4,5.f);
        zeitpunkt8.add(5,5.f);
        zeitpunkt8.add(6,5.f);
        zeitpunkt8.add(7,5.f);
        zeitpunkt8.add(8,5.f);
        anfangsschritt.setStiffnesses("LAnkleRoll",1.f);
        anfangsschritt.setStiffnesses("RAnklePitch",0.4f);
        anfangsschritt.angleInterpolation(bewegung8,winkel8,zeitpunkt8,true);

        //Neunter Schritt
        ArrayList bewegung9 =new ArrayList<String>();
        ArrayList winkel9 =new ArrayList<Float>();
        ArrayList zeitpunkt9 =new ArrayList<Float>();
        bewegung9.add(0,"LAnkleRoll");
        bewegung9.add(1,"RAnklePitch");
        bewegung9.add(2,"RKneePitch");
        bewegung9.add(3,"LHipPitch");
        bewegung9.add(4,"LHipRoll");
        bewegung9.add(5,"RHipRoll");
        bewegung9.add(6,"LShoulderPitch");
        bewegung9.add(7,"LKneePitch");
        bewegung9.add(8,"LAnklePitch");
        winkel9.add(0,Uts.DegToRad(8)); //name7.add(0,"LAnkleRoll");
        winkel9.add(1,Uts.DegToRad(-10)); //name7.add(1,"RAnklePitch");
        winkel9.add(2,Uts.DegToRad(30)); //name7.add(2,"RKneePitch");30
        winkel9.add(3,Uts.DegToRad(-20));//name7.add(3,"LHipPitch");
        winkel9.add(4,Uts.DegToRad(10));//name7.add(4,"LHipRoll");
        winkel9.add(5,Uts.DegToRad(-12));//name7.add(5,"RHipRoll");
        winkel9.add(6,Uts.DegToRad(0));//name7.add(6,"LShoulderPitch");
        winkel9.add(7,Uts.DegToRad(32));//name7.add(7,"LKneePitch");
        winkel9.add(8,Uts.DegToRad(-8));//name7.add(8,"LAnklePitch");
        zeitpunkt9.add(0,5.f);
        zeitpunkt9.add(1,5.f);
        zeitpunkt9.add(2,5.f);
        zeitpunkt9.add(3,5.f);
        zeitpunkt9.add(4,5.f);
        zeitpunkt9.add(5,5.f);
        zeitpunkt9.add(6,5.f);
        zeitpunkt9.add(7,5.f);
        zeitpunkt9.add(8,5.f);
        anfangsschritt.setStiffnesses("RAnklePitch",0.4f);
        anfangsschritt.angleInterpolation(bewegung9,winkel9,zeitpunkt9,true);
        Uts.talk("Fertig");
        Thread.sleep(3000);

    }
}
