package testruns;





/**
 * Created by Andi on 06.04.2017.
 */



    import com.aldebaran.qi.helper.proxies.*;
    import movings_Andi_Iskar.Position;
    import utillities.Utts;

    import java.util.*;

public class Test_mov_Andi {

    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        ALLocalization home = new ALLocalization(Utts.getAPP().session());

        ALMotion bewe = new ALMotion(Utts.getSESSION());
        ALNavigation be = new ALNavigation(Utts.getSESSION());

        ArrayList array = new ArrayList<>();
        ArrayList array1 = new ArrayList<>();
        ArrayList array2 = new ArrayList<>();
        ArrayList array3 = new ArrayList<>();
        ArrayList array4 = new ArrayList<>();
        ArrayList array5 = new ArrayList<>();
        ArrayList array6 = new ArrayList<>();
        ArrayList array7 = new ArrayList<>();
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
        System.out.println(array);
        System.out.println(bewe.getMoveConfig("Max"));
        System.out.println(bewe.getMoveConfig("Min"));
        System.out.println(bewe.getMoveConfig("Default"));
        bewe.moveTo(0.5f, 0.f, 0.f, Position.moveconfigvor());



        //home.

       // bewe.stopMove();
        // be.move(0.5f,0f,0f);


        // home.learnHome();


           /* ArrayList<Float> homepostion = new ArrayList<>();
            ArrayList<Float> prob = new ArrayList<>();
           homepostion=(ArrayList)home.getRobotPosition();
           prob.add(0,0f);
            prob.add(1,0f);
            prob.add(2,0f);
           System.out.println("Die home position"+homepostion);
            System.out.println("Die prob position"+prob);
           System.out.println("Roboter Postionn anfang "+home.getRobotPosition());
          //Utts.walk(0.5f,0.f,0);
          // Utts.walk(0.f,0.2f,0);
            System.out.println("Roboter Postionn vor go home "+home.getRobotPosition());

          home.goToPosition(prob);

           //home.goToHome();



            System.out.println("Ist der Roboter zuhause? "+home.isInCurrentHome());
            System.out.println("Die orientierung "+home.getRobotOrientation());
            System.out.println("Roboter Postionn nach go homer "+home.getRobotPosition());
            System.out.println();

        }*/

    }
}



