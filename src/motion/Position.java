package motion;

import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTracker;
import utillities.Uts;
import java.util.ArrayList;


public class Position  {

    private static ALRobotPosture p;
    private static ALMotion bew;
    private static ArrayList name;
    private static ArrayList name1;
    private static ArrayList angles;
    private static ArrayList angles1;
    private static ArrayList time;
    private static ArrayList time1;
    private static ArrayList array;
    private static ArrayList array1;
    private static ArrayList array2;
    private static ArrayList array3;
    private static ArrayList array4;
    private static ArrayList array5;
    private static ArrayList array6;
    private static ArrayList array7;
    private static boolean b = true;

    public static void follow() throws Exception{
        ALTracker a = new ALTracker(Uts.getSESSION());
        while (b) {
            a.track("Face");
            a.setMode("Move");
        }
    }

    public static void sitzen() throws Exception{
        p = new ALRobotPosture(Uts.getSESSION());
        p.stopMove();

        if(!p.getPosture().equals("Sit")) {
            Uts.talk("Gib mir ein moment ich setze mich hin.");
            p.goToPosture("Sit", 1.f);
            Uts.talk("Ich hab mich hingesetzt");
        }
    }

    public static void hocke() throws Exception{
        p = new ALRobotPosture(Uts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("Crouch")) {
            Uts.talk("Gib mir ein moment ich hocke mich hin.");
            p.goToPosture("Crouch", 1.f);
            Uts.talk("Ich bin fertig");
        }
    }
    public static void stehen() throws Exception{
        p = new ALRobotPosture(Uts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("Stand")) {
            Uts.talk("Gib mir ein moment ich stehe auf.");
            p.goToPosture("Stand", 1.f);
            Uts.talk("Ich stehe");
        }
    }
    public static void liegenRuecken() throws Exception{
        p = new ALRobotPosture(Uts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("LyingBack")) {
            Uts.talk("Gib mir ein moment ich lege mich auf den Rücken.");
            p.goToPosture("LyingBack", 1.f);
            Uts.talk("Ich liege auf den Rücken");
        }
    }
    public static void liegenBauch() throws Exception{
        p = new ALRobotPosture(Uts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("LyingBelly")) {
            Uts.talk("Gib mir ein moment ich lege mich auf den Bauch.");
            p.goToPosture("LyingBelly", 1.f);
            Uts.talk("Ich liege auf dem Bauch");
        }
    }
    public static void stehenNull() throws Exception{
        p = new ALRobotPosture(Uts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("StandZero")) {
            Uts.talk("Gib mir ein moment ich stell mich hin.");
            p.goToPosture("StandZero", 1.f);
            Uts.talk("Ich bin fertig");
        }
    }
    public static void sitzenRelax() throws Exception{
        p = new ALRobotPosture(Uts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("SitRelax")) {
            Uts.talk("Gib mir ein moment ich setze mich relex hin.");
            p.goToPosture("SitRelax", 1.f);
            Uts.talk("Ich sitze");
        }
    }
    public static void stehenInit() throws Exception{
        p = new ALRobotPosture(Uts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("StandInit")) {
            Uts.talk("Gib mir ein moment ich stell mich hin");
            p.goToPosture("StandInit", 0.5f);
            Uts.talk("Ich stehe");
        }
    }

    public static void winken() throws Exception{
        bew= new ALMotion(Uts.getAPP().session());

        //erzeugen ArrayList
        name =new ArrayList<String>();
        name1 =new ArrayList<String>();

        angles =new ArrayList<Float>();
        angles1 =new ArrayList<Float>();

        time =new ArrayList<Float>();
        time1 =new ArrayList<Float>();

        //hinzufügen bewegungsaktionen zur liste
        name.add(0,"RShoulderPitch");
        name.add(1,"RWristYaw");
        name.add(2,"RHand");

        name1.add(0,"RShoulderRoll");
        //festlegen der Aktionswinkel in liste
        angles.add(0,Uts.DegToRad(-70));
        angles.add(1,Uts.DegToRad(-70));
        angles.add(2,Uts.DegToRad(50));

        angles1.add(0,Uts.DegToRad(-50));
        angles1.add(1,Uts.DegToRad(0));

        //festlegen der Zeitpunkte der Aktionen
        time.add(0,1.f);
        time.add(1,1.2f);
        time.add(2,1.4f);

        time1.add(0,1.0f);
        time1.add(1,2.0f);
        //Die Steifheit auf 100% stellen
        bew.setStiffnesses("RArm",1.0f);
        //Bewegungsaktionen ausführen
        bew.angleInterpolation(name,angles,time,true);

        for(int i=0;i<2;i++) {
            bew.angleInterpolation(name1, angles1, time1, true);
        }

        bew.closeHand("RHand");

               /*
       ********
       für "Body" namen(körpername)
        HeadYaw, HeadPitch, LShoulderPitch, LShoulderRoll, LElbowYaw, LElbowRoll, LWristYaw, LHand, LHipYawPitch, LHipRoll,
       LHipPitch, LKneePitch, LAnklePitch, LAnkleRoll, RHipYawPitch, RHipRoll, RHipPitch, RKneePitch, RAnklePitch, RAnkleRoll, RShoulderPitch,
       RShoulderRoll, RElbowYaw, RElbowRoll, RWristYaw, RHand
       ********
       ********
       für "Chains" name(Ketten)
       Head, LArm, LLeg, RLeg, RArm
       ********
       ********
        "Joints" (Gelenke)
        HeadYaw, HeadPitch, LShoulderPitch, LShoulderRoll, LElbowYaw, LElbowRoll, LWristYaw, LHipYawPitch, LHipRoll, LHipPitch,
        LKneePitch, LAnklePitch, LAnkleRoll, RHipYawPitch, RHipRoll, RHipPitch, RKneePitch, RAnklePitch, RAnkleRoll, RShoulderPitch, RShoulderRoll,
        RElbowYaw, RElbowRoll, RWristYaw]
        ********
        *********
       "JointActuators"(Aktuatoren Stellantriebe)
        HeadYaw, HeadPitch, LShoulderPitch, LShoulderRoll, LElbowYaw, LElbowRoll, LWristYaw, LHand, LHipYawPitch, LHipRoll, LHipPitch, LKneePitch,
        LAnklePitch, LAnkleRoll, RHipYawPitch, RHipRoll, RHipPitch, RKneePitch, RAnklePitch, RAnkleRoll, RShoulderPitch, RShoulderRoll, RElbowYaw,
        RElbowRoll, RWristYaw, RHand
        ***********
        **********
       "Actuators" (Aktuatoren)
       LHand, RHand
       *************
        */
       /*
       angles (Winkel)
       setAngles erwartet(const AL::ALValue& names, const AL::ALValue& angles, const float& fractionMaxSpeed)

        names – The name or names of joints, chains, “Body”, “JointActuators”, “Joints” or “Actuators”.
        angles – One or more angles in radiansh
        fractionMaxSpeed – The fraction of maximum speed to use
        */

    }

    public static ArrayList moveconfigvor() throws Exception{
        array = new ArrayList<>();
        array1 = new ArrayList<>();
        array2 = new ArrayList<>();
        array3 = new ArrayList<>();
        array4 = new ArrayList<>();
        array5 = new ArrayList<>();
        array6 = new ArrayList<>();
        array7 = new ArrayList<>();
        array1.add(0, "MaxStepX");
        array1.add(1, 0.08f);

        array2.add(0, "MaxStepY");
        array2.add(1, 0.16f);

        array3.add(0, "MaxStepTheta");
        array3.add(1, 0.5f);

        array4.add(0, "MaxStepFrequency");
        array4.add(1, 0.0f);

        array5.add(0, "StepHeight");
        array5.add(1, 0.08f);
        array6.add(0, "TorsoWx");
        array6.add(1, 0.f);

        array7.add(0, "TorsoWy");
        array7.add(1, -0.1f);

        array.add(0, array1);
        array.add(1, array2);
        array.add(2, array3);
        array.add(3, array4);
        array.add(4, array5);
        array.add(5, array6);
        array.add(6, array7);


        return array;
    }
}



