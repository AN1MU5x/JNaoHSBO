package movings_Andi_Iskar;


import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import utillities.Utts;

/**
 * Created by Andi on 10.04.2017.
 */
public class Position  {





    public static ALRobotPosture p;



    public static void Sit() throws Exception{
        p = new ALRobotPosture(Utts.APP.session());
        p.stopMove();
        Utts.talk("Give me a second I am sitting down.");
        p.goToPosture("Sit", 1.f);
        Utts.talk("I am ready with sitting");
    }
    public static void Crouch() throws Exception{
        p = new ALRobotPosture(Utts.APP.session());
        p.stopMove();
        Utts.talk("Give me a second I am crouching.");
        p.goToPosture("Crouch", 1.f);
        Utts.talk("I am ready with Crouching");
    }
    public static void Stand() throws Exception{
        p = new ALRobotPosture(Utts.APP.session());
        p.stopMove();
        Utts.talk("Give me a second I am Stand up.");
        p.goToPosture("Stand", 1.f);
        Utts.talk("I am ready with Stand up");
    }
    public static void LyingBack() throws Exception{
        p = new ALRobotPosture(Utts.APP.session());
        p.stopMove();
        Utts.talk("Give me a second I am LyingBack.");
        p.goToPosture("LyingBack", 1.f);
        Utts.talk("I am ready with LyingBack");
    }
    public static void LyingBelly() throws Exception{
        p = new ALRobotPosture(Utts.APP.session());
        p.stopMove();
        Utts.talk("Give me a second I am LyingBelly.");
        p.goToPosture("LyingBelly", 1.f);
        Utts.talk("I am ready with LyingBelly");
    }
    public static void StandZero() throws Exception{
        p = new ALRobotPosture(Utts.APP.session());
        p.stopMove();
        Utts.talk("Give me a second I am Stand Zero.");
        p.goToPosture("StandZero", 1.f);
        Utts.talk("I am ready with StandZero");
    }
    public static void SitRelax() throws Exception{
        p = new ALRobotPosture(Utts.APP.session());
        p.stopMove();
        Utts.talk("Give me a second I am Sit Relax.");
        p.goToPosture("SitRelax", 1.f);
        Utts.talk("I am ready with Sit Relax");
    }
    public static void StandInit() throws Exception{
        p = new ALRobotPosture(Utts.APP.session());
        p.stopMove();
        Utts.talk("Give me a second I am Stand Init.");
        p.goToPosture("StandInit", 1.f);
        Utts.talk("I am ready with StandInit");
    }



}
