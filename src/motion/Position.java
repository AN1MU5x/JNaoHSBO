package motion;

import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTracker;
import sensors.TactilTouchedEvent;
import utillities.Uts;
import java.util.ArrayList;

public class Position  {

    //Das ALRobotPosture - Modul ermöglicht es Ihnen, den Roboter in verschiedene vordefinierte Positionen zu bewegen
    private static ALRobotPosture p;

    //Das ALMotion-Modul bietet Methoden, die es ermöglichen den Roboter zu bewegen
    private static ALMotion bew;

    //Array-Listen zum abspeichern der gelenke, winkel und der Zeit (Funktion winken)
    private static ArrayList name;
    private static ArrayList name1;
    private static ArrayList angles;
    private static ArrayList angles1;
    private static ArrayList time;
    private static ArrayList time1;


    public static boolean bFollow;

    //Einfache Folgefunktion
    public static void follow(Session session) throws Exception{
        bFollow = true;

        //Anlegen eines Objekts von ALTracker mit übergabe der Session
        ALTracker a = new ALTracker(session);

        while (bFollow) {
            a.track("Face");
            a.setMode("Move");

            //TactilTouchedEvent zum stoppen über die Sensoren am Kopf anlegen
            TactilTouchedEvent ctte = new TactilTouchedEvent();

            //Ausführen der durchgängigen Sensorabfrage
            ctte.run(session);
        }
        a.stopTracker();
    }
    //Funktion Komm zu  mir
    public static void come(Session session)throws Exception{

        //Anlegen eines Objekts von ALTracker mit übergeben der Session
        ALTracker a = new ALTracker(session);
        a.track("Face");
        a.setMode("Move");
    }

    //Funktion sitzen
    public static void sitzen(Session session) throws Exception{

        //Anlegen eines Objekts aus der Klasse ALRobotPosture, welche vordefinierte Positionen beinhaltet
        p = new ALRobotPosture(session);

        //Aufruf der Methode stopMove, um Überschneidungen zu verhindern
        p.stopMove();

        //Abfrage ob Roboter bereits sitzt, wenn nicht, wird hinsetzen eingeleitet
        if(!p.getPosture().equals("Sit")) {
            Uts.talk("Gib mir einen Moment ich setze mich hin.");

            //Die Methode goToPosture erwartet einen Positonsnamen und die Geschwindigkeit, mit welcher in diese Position bewegt werden soll
            p.goToPosture("Sit", 1.f);
            Uts.talk("Ich hab mich hingesetzt");
        }
    }

    public static void hocke(Session session) throws Exception{
        p = new ALRobotPosture(session);
        p.stopMove();
        if(!p.getPosture().equals("Crouch")) {
            Uts.talk("Gib mir einen Moment ich hocke mich hin.");
            p.goToPosture("Crouch", 1.f);
            Uts.talk("Ich bin fertig");
        }
    }

    public static void stehen(Session session) throws Exception{
        p = new ALRobotPosture(session);
        p.stopMove();
        if(!p.getPosture().equals("Stand")) {
            Uts.talk("Gib mir einen Moment ich stehe auf.");
            p.goToPosture("Stand", 1.f);
            Uts.talk("Ich stehe");
        }
    }

    public static void liegenRuecken(Session session) throws Exception{
        p = new ALRobotPosture(session);
        p.stopMove();
        if(!p.getPosture().equals("LyingBack")) {
            Uts.talk("Gib mir einen Moment ich lege mich auf den Rücken.");
            p.goToPosture("LyingBack", 1.f);
            Uts.talk("Ich liege auf dem Rücken");
        }
    }

    public static void liegenBauch(Session session) throws Exception{
        p = new ALRobotPosture(session);
        p.stopMove();
        if(!p.getPosture().equals("LyingBelly")) {
            Uts.talk("Gib mir einen Moment ich lege mich auf den Bauch.");
            p.goToPosture("LyingBelly", 1.f);
            Uts.talk("Ich liege auf dem Bauch");
        }
    }

    public static void stehenNull(Session session) throws Exception{
        p = new ALRobotPosture(session);
        p.stopMove();
        if(!p.getPosture().equals("StandZero")) {
            Uts.talk("Gib mir einen Moment ich stell mich hin.");
            p.goToPosture("StandZero", 1.f);
            Uts.talk("Ich bin fertig");
        }
    }

    public static void sitzenRelax(Session session) throws Exception{
        p = new ALRobotPosture(session);
        p.stopMove();
        if(!p.getPosture().equals("SitRelax")) {
            Uts.talk("Gib mir einen Moment ich setze mich relex hin.");
            p.goToPosture("SitRelax", 1.f);
            Uts.talk("Ich sitze");
        }
    }

    //Stehen Einleiten, mit der Option sich auf für folgende Bewegung bereit zu halten
    public static void stehenInit(Session session) throws Exception{
        p = new ALRobotPosture(session);
        p.stopMove();
        if(!p.getPosture().equals("StandInit")) {
            Uts.talk("Gib mir einen Moment ich stell mich hin");
            p.goToPosture("StandInit", 0.5f);
            Uts.talk("Ich stehe");
        }
    }

    //Methode, welche den Roboter winken lässt
    public static void winken(Session session) throws Exception{

        //Anlegen von Objekt aus der Klasse ALMotion, welche Methoden zur Ansteuerung der einzelnen Motoren beinhaltet
        bew= new ALMotion(session);

        //Erzeugen ArrayList
        name =new ArrayList<String>();
        name1 =new ArrayList<String>();

        angles =new ArrayList<Float>();
        angles1 =new ArrayList<Float>();

        time =new ArrayList<Float>();
        time1 =new ArrayList<Float>();

        //Hinzufügen Bewegungsaktionen zur liste (Was soll angesprochen werden)
        name.add(0,"RShoulderPitch");
        name.add(1,"RWristYaw");
        name.add(2,"RHand");

        name1.add(0,"RShoulderRoll");

        //Festlegen der Aktionswinkel in liste (Um wieviel Grad soll zuständiger Motor drehen)
        angles.add(0,Uts.DegToRad(-70));
        angles.add(1,Uts.DegToRad(-70));
        angles.add(2,Uts.DegToRad(50));

        angles1.add(0,Uts.DegToRad(-50));
        angles1.add(1,Uts.DegToRad(0));

        //Festlegen der Zeitpunkte der Aktionen (Wann und wie Lange soll Aktion durchgeführt werden)
        time.add(0,1.f);
        time.add(1,1.2f);
        time.add(2,1.4f);

        time1.add(0,1.0f);
        time1.add(1,2.0f);

        //Die Steifheit auf 100% stellen, um die Motoren bewegen zu können (weniger Steifheit = weniger Kraft)
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

}



