package testruns;



import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import utillities.Uts;

import java.util.ArrayList;


/**
 * Created by Andi on 19.04.2017.
 */
public class Test_mov_bew_Andi {
    public static void main(String[] args) throws Exception {
        Uts.AppStart();



        ALMotion bew = new ALMotion(Uts.getSESSION());

      bew.wakeUp();
       bew.stopMove();
       //bew.rest();
    ALRobotPosture p= new ALRobotPosture(Uts.getAPP().session());
       p.goToPosture("Stand",0.5f);
      Thread.sleep(1000);
      //bew.rest();

        ArrayList name =new ArrayList<String>();
        ArrayList name1 =new ArrayList<String>();
        ArrayList name2 =new ArrayList<String>();
        ArrayList name3 =new ArrayList<String>();
     ArrayList name4 =new ArrayList<String>();
     ArrayList name5=new ArrayList<String>();
        ArrayList name6=new ArrayList<String>();

        ArrayList angles =new ArrayList<Float>();
        ArrayList angles1 =new ArrayList<Float>();
        ArrayList angles2 =new ArrayList<Float>();
        ArrayList angles3 =new ArrayList<Float>();
        ArrayList angles4 =new ArrayList<Float>();
     ArrayList angles5 =new ArrayList<Float>();
        ArrayList angles6 =new ArrayList<Float>();

        ArrayList time =new ArrayList<Float>();
        ArrayList time1 =new ArrayList<Float>();
        ArrayList time2 =new ArrayList<Float>();
        ArrayList time3 =new ArrayList<Float>();
        ArrayList time4 =new ArrayList<Float>();
     ArrayList time5 =new ArrayList<Float>();
        ArrayList time6 =new ArrayList<Float>();

        //hinzufügen bewegungsaktionen zur liste
        Uts.talk("Bitte festhalten");


        name.add(0,"LHipRoll");
        name.add(1,"RHipRoll");

        name1.add(0,"LKneePitch");
        name1.add(1,"RKneePitch");
        name1.add(2,"LHipPitch");
        name1.add(3,"RHipPitch");
        name1.add(4,"LAnklePitch");
        name1.add(5,"RAnklePitch");

        name2.add(0,"LHipRoll");
        name2.add(1,"RHipRoll");

        //name3.add(0,"RShoulderPitch");
       // name3.add(1,"LShoulderPitch");
        name3.add(0,"RHipRoll");
        name3.add(1,"LHipRoll");
       name3.add(2,"LHipPitch");

       //neu
        name3.add(3,"LShoulderPitch");
        name3.add(4,"LKneePitch");

       name4.add(0,"RAnklePitch");
        name4.add(1,"RHipRoll");
        name4.add(2,"LHipRoll");
       name4.add(3,"LHipPitch");

        name4.add(4,"RHipPitch");
        name4.add(5,"RKneePitch");
        name4.add(6,"LAnklePitch");
        name4.add(7,"LShoulderPitch");

        name5.add(0,"LKneePitch");
        name5.add(1,"RHipRoll");
        name5.add(2,"LHipRoll");
        name5.add(3,"RKneePitch");
        name5.add(4,"RAnkleRoll");

       // name6.add(0,"LKneePitch");
        name6.add(0,"LHipPitch");
        name6.add(1,"RHipPitch");
       name6.add(2,"LHipRoll");
       name6.add(3,"LAnkleRoll");


       // name1.add(0,"RShoulderRoll");
        //festlegen der Aktionswinkel in liste
/*
        angles.add(0,Utts.DegToRad(-5));
        angles.add(1,Utts.DegToRad(5));

        angles1.add(0,Utts.DegToRad(10));
        angles1.add(1,Utts.DegToRad(10));
        angles1.add(2,Utts.DegToRad(10));
        angles1.add(3,Utts.DegToRad(10));
        angles1.add(4,Utts.DegToRad(-10));
        angles1.add(5,Utts.DegToRad(-10));

        angles2.add(0,Utts.DegToRad(10));
        angles2.add(1,Utts.DegToRad(10));

     //   angles3.add(0,Utts.DegToRad(-10));
      //  angles3.add(1,Utts.DegToRad(-10));
        angles3.add(0,Utts.DegToRad(13));
        angles3.add(1,Utts.DegToRad(10));
        angles3.add(2,Utts.DegToRad(-40));
        angles3.add(3,Utts.DegToRad(0));
        angles3.add(4,Utts.DegToRad(25));

        angles4.add(0,Utts.DegToRad(-30));
        angles4.add(1,Utts.DegToRad(13));
        angles4.add(2,Utts.DegToRad(10));
        angles4.add(3,Utts.DegToRad(-30));
        angles4.add(4,Utts.DegToRad(2));
        angles4.add(5,Utts.DegToRad(40));
        angles4.add(6,Utts.DegToRad(30));
        angles4.add(7,Utts.DegToRad(0));

     angles5.add(0,Utts.DegToRad(25));
     angles5.add(1,Utts.DegToRad(17));
     angles5.add(2,Utts.DegToRad(17));
        angles5.add(3,Utts.DegToRad(35));
        angles5.add(4,Utts.DegToRad(2));

    // angles6.add(0,Utts.DegToRad(0));
        angles6.add(0,Utts.DegToRad(10));
        angles6.add(1,Utts.DegToRad(10));
        angles6.add(2,Utts.DegToRad(13));
        angles6.add(3,Utts.DegToRad(3));

*/
        //festlegen der Zeitpunkte der Aktionen
        time.add(0,2.f);
        time.add(1,2.f);

       time1.add(0,5.4f);
        time1.add(1,5.4f);
        time1.add(2,5.4f);
        time1.add(3,5.4f);
        time1.add(4,5.4f);
        time1.add(5,5.4f);

        time2.add(0,3.0f);
        time2.add(1,3.0f);

      //  time3.add(0,5f);
       // time3.add(1,5f);
        time3.add(0,7f);
        time3.add(1,7f);
        time3.add(2,7f);
        time3.add(3,7f);
        time3.add(4,7f);

        time4.add(0,5.f);
        time4.add(1,5.f);
        time4.add(2,5.f);
        time4.add(3,5.f);
        time4.add(4,5.f);
        time4.add(5,5.f);
        time4.add(6,5.f);
        time4.add(7,5.f);

     time5.add(0,5.f);
     time5.add(1,5.f);
     time5.add(2,5.f);
        time5.add(3,5.f);
        time5.add(4,5.f);

     //time6.add(0,5.f);
       time6.add(0,5.f);
        time6.add(1,5.f);
        time6.add(2,5.f);
        time6.add(3,5.f);





        bew.setStiffnesses("LLeg",1.0f);
        bew.setStiffnesses("RLeg",1.0f);
      //  bew.setStiffnesses("RAnklePitch",0.0f);
       // bew.setStiffnesses("RKneePitch",0.0f);
        bew.setStiffnesses("LAnkleRoll",0.0f);
        bew.setStiffnesses("RAnkleRoll",0.0f);
        bew.angleInterpolation(name,angles,time,true);
        bew.setStiffnesses("LAnkleRoll",0.0f);
        bew.setStiffnesses("RAnkleRoll",0.0f);

       // bew.setStiffnesses("RHipRoll",0.0f);

      // bew.angleInterpolation(name1,angles1,time1,true);

      bew.setStiffnesses("LAnkleRoll",0.0f);
        bew.angleInterpolation(name2,angles2,time2,true);
        bew.setStiffnesses("LAnkleRoll",1.0f);

      //bew.setStiffnesses("RHipRoll",1.0f);
        //bew.setStiffnesses("RHipPitch",1.0f);

        bew.setStiffnesses("RAnkleRoll",1.0f);
       // bew.setStiffnesses("RAnklePitch",1.0f);
       // bew.setStiffnesses("LAnkleRoll",0.0f);
       // bew.setStiffnesses("LAnklePitch",0.0f);
       // bew.setStiffnesses("LKneePitch",0.0f);
        bew.angleInterpolation(name3,angles3,time3,true);
        //bew.setStiffnesses("LKneePitch",1.0f);


         bew.setStiffnesses("LAnkleRoll",0.0f);
         bew.setStiffnesses("LAnklePitch",0.0f);
        bew.angleInterpolation(name4,angles4,time4,true);
        bew.setStiffnesses("LAnkleRoll",1.0f);
        bew.setStiffnesses("LAnklePitch",1.0f);
      //  bew.setStiffnesses("LKneePitch",1.0f);
        bew.setStiffnesses("LAnklePitch",0.f);
        bew.angleInterpolation(name5,angles5,time5,true);
        bew.setStiffnesses("LAnklePitch",1.f);

        bew.setStiffnesses("RAnklePitch",0.f);
       bew.angleInterpolation(name6,angles6,time6,true);
        bew.setStiffnesses("RAnklePitch",1.f);


        Thread.sleep(5000);
        System.out.println("Ende");

    }

}
