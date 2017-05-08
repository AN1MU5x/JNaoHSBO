package testruns;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import utillities.Uts;

import java.util.ArrayList;

/**
 * Created by Daniel on 24.04.2017.
 */
public class Test_Andi_backup {

    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        System.out.println("Successfully connected to the robot");
        // Subscribe to selected ALMemory events
        Test_Andi_backup reactor = new Test_Andi_backup();

        reactor.run(Uts.getSESSION());

        // Run your application
        Uts.getAPP().run();

    }

    ALMotion bew;
    ALRobotPosture p;
    ArrayList name;
    ArrayList name1;
    ArrayList name2;
    ArrayList name3;
    ArrayList name4;
    ArrayList name5;
    ArrayList name6;
    ArrayList name7;
    ArrayList name8;

    ArrayList angles;
    ArrayList angles1;
    ArrayList angles2;
    ArrayList angles3;
    ArrayList angles4;
    ArrayList angles5;
    ArrayList angles6;
    ArrayList angles7;
    ArrayList angles8;

    ArrayList time;
    ArrayList time1;
    ArrayList time2;
    ArrayList time3;
    ArrayList time4;
    ArrayList time5;
    ArrayList time6;
    ArrayList time7;
    ArrayList time8;




    public void run(Session session) throws Exception{
        bew = new ALMotion(session);

        bew.wakeUp();
        bew.stopMove();
        //bew.rest();
       p= new ALRobotPosture(session);
        p.goToPosture("Stand",0.5f);
        Thread.sleep(1000);
        //bew.rest();

         name =new ArrayList<String>();
         name1 =new ArrayList<String>();
         name2 =new ArrayList<String>();
       name3 =new ArrayList<String>();
         name4 =new ArrayList<String>();
        name5=new ArrayList<String>();
         name6=new ArrayList<String>();
         name7=new ArrayList<String>();
         name8=new ArrayList<String>();

         angles =new ArrayList<Float>();
         angles1 =new ArrayList<Float>();
         angles2 =new ArrayList<Float>();
        angles3 =new ArrayList<Float>();
        angles4 =new ArrayList<Float>();
         angles5 =new ArrayList<Float>();
         angles6 =new ArrayList<Float>();
         angles7 =new ArrayList<Float>();
         angles8 =new ArrayList<Float>();

         time =new ArrayList<Float>();
         time1 =new ArrayList<Float>();
        time2 =new ArrayList<Float>();
        time3 =new ArrayList<Float>();
         time4 =new ArrayList<Float>();
         time5 =new ArrayList<Float>();
       time6 =new ArrayList<Float>();
         time7 =new ArrayList<Float>();
        time8 =new ArrayList<Float>();

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
        name5.add(5,"LShoulderPitch");

        // name6.add(0,"LKneePitch");

        name6.add(0,"LAnkleRoll");
        name6.add(1,"RAnklePitch");
        name6.add(2,"RKneePitch");
        name6.add(3,"LHipPitch");
        // name6.add(4,"LKneePitch");
        name6.add(4,"LHipRoll");
        name6.add(5,"RHipRoll");
        name6.add(6,"LShoulderPitch");
        name6.add(7,"LKneePitch");
        name6.add(8,"LAnklePitch");

        name7.add(0,"LAnkleRoll");
        name7.add(1,"RAnklePitch");
        name7.add(2,"RKneePitch");
        name7.add(3,"LHipPitch");
        // name6.add(4,"LKneePitch");
        name7.add(4,"LHipRoll");
        name7.add(5,"RHipRoll");
        name7.add(6,"LShoulderPitch");
        name7.add(7,"LKneePitch");
        name7.add(8,"LAnklePitch");

        name8.add(0,"LAnkleRoll");
        name8.add(1,"RAnklePitch");
        name8.add(2,"RKneePitch");
        name8.add(3,"LHipPitch");
        // name6.add(4,"LKneePitch");
        name8.add(4,"LHipRoll");
        name8.add(5,"RHipRoll");
        name8.add(6,"LShoulderPitch");
        name8.add(7,"LKneePitch");
        name8.add(8,"LAnklePitch");
        //  name8.add(9,"RHipPitch");

        // name1.add(0,"RShoulderRoll");
        //festlegen der Aktionswinkel in liste

        angles.add(0,Uts.DegToRad(-5));
        angles.add(1,Uts.DegToRad(5));

        angles1.add(0,Uts.DegToRad(10));
        angles1.add(1,Uts.DegToRad(10));
        angles1.add(2,Uts.DegToRad(10));
        angles1.add(3,Uts.DegToRad(10));
        angles1.add(4,Uts.DegToRad(-10));
        angles1.add(5,Uts.DegToRad(-10));

        angles2.add(0,Uts.DegToRad(10));
        angles2.add(1,Uts.DegToRad(10));

        //   angles3.add(0,Utts.DegToRad(-10));
        //  angles3.add(1,Utts.DegToRad(-10));
        angles3.add(0,Uts.DegToRad(13));
        angles3.add(1,Uts.DegToRad(10));
        angles3.add(2,Uts.DegToRad(-40));
        angles3.add(3,Uts.DegToRad(0));
        angles3.add(4,Uts.DegToRad(25));

        angles4.add(0,Uts.DegToRad(-30));
        angles4.add(1,Uts.DegToRad(13));
        angles4.add(2,Uts.DegToRad(10));
        angles4.add(3,Uts.DegToRad(-30));
        angles4.add(4,Uts.DegToRad(2));
        angles4.add(5,Uts.DegToRad(40));
        angles4.add(6,Uts.DegToRad(30));
        angles4.add(7,Uts.DegToRad(0));

        angles5.add(0,Uts.DegToRad(25));
        angles5.add(1,Uts.DegToRad(17));
        angles5.add(2,Uts.DegToRad(17));
        angles5.add(3,Uts.DegToRad(35));
        angles5.add(4,Uts.DegToRad(2));
        angles5.add(5,Uts.DegToRad(0));

        // angles6.add(0,Utts.DegToRad(0));

        angles6.add(0,Uts.DegToRad(-3));
        angles6.add(1,Uts.DegToRad(-30));
        angles6.add(2,Uts.DegToRad(30));
        angles6.add(3,Uts.DegToRad(-25));
        // angles6.add(4,Utts.DegToRad(0));
        angles6.add(4,Uts.DegToRad(-10));
        angles6.add(5,Uts.DegToRad(-10));
        angles6.add(6,Uts.DegToRad(0));
        angles6.add(7,Uts.DegToRad(20));
        angles6.add(8,Uts.DegToRad(5));

        angles7.add(0,Uts.DegToRad(8)); //name7.add(0,"LAnkleRoll");
        angles7.add(1,Uts.DegToRad(-10)); //name7.add(1,"RAnklePitch");
        angles7.add(2,Uts.DegToRad(30)); //name7.add(2,"RKneePitch");
        angles7.add(3,Uts.DegToRad(-20));//name7.add(3,"LHipPitch");
        // angles6.add(4,Utts.DegToRad(0));
        angles7.add(4,Uts.DegToRad(-15));//name7.add(4,"LHipRoll");
        angles7.add(5,Uts.DegToRad(-10));//name7.add(5,"RHipRoll");
        angles7.add(6,Uts.DegToRad(0));//name7.add(6,"LShoulderPitch");
        angles7.add(7,Uts.DegToRad(25));//name7.add(7,"LKneePitch");
        angles7.add(8,Uts.DegToRad(-5));//name7.add(8,"LAnklePitch");

        angles8.add(0,Uts.DegToRad(8)); //name7.add(0,"LAnkleRoll");
        angles8.add(1,Uts.DegToRad(-10)); //name7.add(1,"RAnklePitch");
        angles8.add(2,Uts.DegToRad(30)); //name7.add(2,"RKneePitch");30
        angles8.add(3,Uts.DegToRad(-20));//name7.add(3,"LHipPitch");
        angles8.add(4,Uts.DegToRad(-17));//name7.add(4,"LHipRoll");
        angles8.add(5,Uts.DegToRad(-12));//name7.add(5,"RHipRoll");
        angles8.add(6,Uts.DegToRad(0));//name7.add(6,"LShoulderPitch");
        angles8.add(7,Uts.DegToRad(27));//name7.add(7,"LKneePitch");
        angles8.add(8,Uts.DegToRad(-8));//name7.add(8,"LAnklePitch");
        //angles8.add(9,Utts.DegToRad(-30));


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
        time5.add(5,5.f);

        //time6.add(0,5.f);
        time6.add(0,5.f);
        time6.add(1,5.f);
        time6.add(2,5.f);
        time6.add(3,5.f);
        time6.add(4,5.f);
        time6.add(5,5.f);
        time6.add(6,5.f);
        time6.add(7,6.f);
        time6.add(8,6.f);

        time7.add(0,5.f);
        time7.add(1,5.f);
        time7.add(2,5.f);
        time7.add(3,5.f);
        time7.add(4,5.f);
        time7.add(5,5.f);
        time7.add(6,5.f);
        time7.add(7,5.f);
        time7.add(8,5.f);

        time8.add(0,5.f);
        time8.add(1,5.f);
        time8.add(2,5.f);
        time8.add(3,5.f);
        time8.add(4,5.f);
        time8.add(5,5.f);
        time8.add(6,5.f);
        time8.add(7,5.f);
        time8.add(8,5.f);
        //   time8.add(9,5.f);






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

        bew.setStiffnesses("LAnkleRoll",0.f);

        bew.angleInterpolation(name6,angles6,time6,true);

        bew.setStiffnesses("LAnkleRoll",1.f);

        bew.setStiffnesses("RAnklePitch",0.4f);
        bew.angleInterpolation(name7,angles7,time7,true);
        bew.setStiffnesses("RAnklePitch",0.4f);

        // bew.setStiffnesses("RAnklePitch",0.f);
        //bew.setStiffnesses("RAnkleRoll",0.f);
        bew.angleInterpolation(name8,angles8,time8,true);
        // bew.setStiffnesses("RAnklePitch",1.f);
        //bew.setStiffnesses("RAnkleRoll",1.f);



        Uts.talk("Fertig");

        Thread.sleep(3000);
        System.out.println("Ende");

    }
}
