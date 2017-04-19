package testruns;




/**
 * Created by Andi on 06.04.2017.
 */



    import com.aldebaran.qi.helper.proxies.ALLocalization;
    import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
    import utillities.Utts;

    import java.util.*;

public class Test_mov_Andi {

        public static void main(String[] args) throws Exception {
            Utts.AppStart();
            ALLocalization home = new ALLocalization(Utts.getAPP().session());

           // home.learnHome();


            ArrayList<Float> homepostion = new ArrayList<>();
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

        }

    }



