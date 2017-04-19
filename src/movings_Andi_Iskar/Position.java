package movings_Andi_Iskar;


import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import utillities.Utts;

/**
 * Created by Andi on 10.04.2017.
 */
public class Position  {





    private static ALRobotPosture p;



    public static void sitzen() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("Sit")) {
            Utts.talk("Gib mir ein moment ich setze mich hin.");
            p.goToPosture("Sit", 1.f);
            Utts.talk("Ich hab mich hingesetzt");
        }
    }

    public static void hocke() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("Crouch")) {
            Utts.talk("Gib mir ein moment ich hocke mich hin.");
            p.goToPosture("Crouch", 1.f);
            Utts.talk("Ich bin fertig");
        }
    }
    public static void stehen() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("Stand")) {
            Utts.talk("Gib mir ein moment ich stehe auf.");
            p.goToPosture("Stand", 1.f);
            Utts.talk("Ich stehe");
        }
    }
    public static void liegenRuecken() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("LyingBack")) {
            Utts.talk("Gib mir ein moment ich lege mich auf den Rücken.");
            p.goToPosture("LyingBack", 1.f);
            Utts.talk("Ich liege auf den Rücken");
        }
    }
    public static void liegenBauch() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("LyingBelly")) {
            Utts.talk("Gib mir ein moment ich lege mich auf den Bauch.");
            p.goToPosture("LyingBelly", 1.f);
            Utts.talk("Ich liege auf dem Bauch");
        }
    }
    public static void stehenNull() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("StandZero")) {
            Utts.talk("Gib mir ein moment ich stell mich hin.");
            p.goToPosture("StandZero", 1.f);
            Utts.talk("Ich bin fertig");
        }
    }
    public static void sitzenRelax() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("SitRelax")) {
            Utts.talk("Gib mir ein moment ich setze mich relex hin.");
            p.goToPosture("SitRelax", 1.f);
            Utts.talk("Ich sitze");
        }
    }
    public static void stehenInit() throws Exception{
        p = new ALRobotPosture(Utts.getAPP().session());
        p.stopMove();
        if(!p.getPosture().equals("StandInit")) {
            Utts.talk("Gib mir ein moment ich stell mich hin");
            p.goToPosture("StandInit", 1.f);
            Utts.talk("Ich stehe");
        }
    }



}
