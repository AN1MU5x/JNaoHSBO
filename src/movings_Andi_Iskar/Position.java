package movings_Andi_Iskar;


import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import utillities.Utts;

/**
 * Created by Andi on 10.04.2017.
 */
public class Position  {





    private static ALRobotPosture p;



    public static void Sitzen() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        Utts.talk("Gib mir ein moment ich setze mich hin.");
        p.goToPosture("Sit", 1.f);
        Utts.talk("Ich hab mich hingesetzt");
    }

    public static void Crouch() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        Utts.talk("Gib mir ein moment ich hocke mich hin.");
        p.goToPosture("Crouch", 1.f);
        Utts.talk("Ich bin fertig");
    }
    public static void Stand() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        Utts.talk("Gib mir ein moment ich stehe auf.");
        p.goToPosture("Stand", 1.f);
        Utts.talk("Ich stehe");
    }
    public static void LyingBack() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        Utts.talk("Gib mir ein moment ich lege mich auf den Rücken.");
        p.goToPosture("LyingBack", 1.f);
        Utts.talk("Ich liege auf den Rücken");
    }
    public static void LyingBelly() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        Utts.talk("Gib mir ein moment ich lege mich auf den Bauch hin.");
        p.goToPosture("LyingBelly", 1.f);
        Utts.talk("Ich liege auf dem Bauch");
    }
    public static void StandZero() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        Utts.talk("");
        p.goToPosture("StandZero", 1.f);
        Utts.talk("");
    }
    public static void SitRelax() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        Utts.talk(".");
        p.goToPosture("SitRelax", 1.f);
        Utts.talk("");
    }
    public static void StandInit() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        Utts.talk("");
        p.goToPosture("StandInit", 1.f);
        Utts.talk("");
    }



}
