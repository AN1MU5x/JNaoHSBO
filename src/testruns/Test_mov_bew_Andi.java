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
       System.out.println( bew.getBodyNames("Chains"));
       /*
       für bodynamen(körpername) HeadYaw, HeadPitch, LShoulderPitch, LShoulderRoll, LElbowYaw, LElbowRoll, LWristYaw, LHand, LHipYawPitch, LHipRoll,
       LHipPitch, LKneePitch, LAnklePitch, LAnkleRoll, RHipYawPitch, RHipRoll, RHipPitch, RKneePitch, RAnklePitch, RAnkleRoll, RShoulderPitch,
       RShoulderRoll, RElbowYaw, RElbowRoll, RWristYaw, RHand
         */
       /*
       für chainnaem(Ketten) =Head, LArm, LLeg, RLeg, RArm
        */




    }

}
