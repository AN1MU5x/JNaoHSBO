package testruns;



import com.aldebaran.qi.helper.proxies.ALMotion;
import movings_Andi_Iskar.Position;
import utillities.Utts;

import java.util.ArrayList;


/**
 * Created by Andi on 19.04.2017.
 */
public class Test_mov_bew_Andi {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();

       ALMotion bew =new ALMotion(Utts.getSESSION());

       ArrayList name =new ArrayList<String>();
        ArrayList name1 =new ArrayList<String>();

        ArrayList angles =new ArrayList<Float>();
        ArrayList angles1 =new ArrayList<Float>();

        ArrayList time =new ArrayList<Float>();
        ArrayList time1 =new ArrayList<Float>();

        //hinzufügen bewegungsaktionen zur liste
        name.add(0,"RShoulderPitch");
        name.add(1,"RWristYaw");
        name.add(2,"RHand");

        name1.add(0,"RShoulderRoll");
        //festlegen der Aktionswinkel in liste
        angles.add(0,Utts.DegToRad(-70));
        angles.add(1,Utts.DegToRad(-70));
        angles.add(2,Utts.DegToRad(50));

        angles1.add(0,Utts.DegToRad(-50));
        angles1.add(1,Utts.DegToRad(0));

        //festlegen der Zeitpunkte der Aktionen
        time.add(0,1.f);
        time.add(1,1.2f);
        time.add(2,1.4f);

        time1.add(0,1.0f);
        time1.add(1,2.0f);

    }

}
