package testruns;

import com.aldebaran.qi.helper.proxies.ALLocalization;
import com.aldebaran.qi.helper.proxies.ALMotion;
import movings_Andi_Iskar.Position;
import utillities.Utts;

import java.util.ArrayList;

/**
 * Created by Daniel on 19.04.2017.
 */
public class Test_mov_bew_Andi {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        ALMotion bew= new ALMotion(Utts.getAPP().session());

        //gelenke name der gelenke erwartet als String name – Name of a chain, “Body”, “Chains”, “JointActuators”, “Joints” or “Actuators”.
       System.out.println( bew.getBodyNames("Actuators"));
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
        angles – One or more angles in radians
        fractionMaxSpeed – The fraction of maximum speed to use
        */
       System.out.println("Start");
        System.out.println(bew.getAngles("RShoulderPitch",true));
        ArrayList angles =new ArrayList<Float>();
        ArrayList name =new ArrayList<String>();
        ArrayList time =new ArrayList<Float>();
        angles.add(0,Utts.DegToRad(-70));
       // angles.add(1,0.1f);
        time.add(0,0.8f);
        //time.add(1,2.0f);
        name.add(0,"RShoulderPitch");
       // name.add(1,"RShoulderPitch");



      bew.setStiffnesses("RArm",1.0f);

        System.out.println(bew.getJointNames("RLeg"));
       bew.angleInterpolation(name,angles,time,true);
      // bew.changeAngles("LShoulderRoll",1.5f,0.3f);

        Thread.sleep(5000);
      bew.setStiffnesses("RArm",0.0f);
        System.out.println("Ende");






    }

}
