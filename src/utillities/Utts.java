package utillities;

import com.aldebaran.qi.Application;

/**
 * Created by Lisa on 06.04.2017.
 */
public class Utts {

    public static String EMMA = "tcp://Emma.local:9559";

    public static float DegToRad(int d){
        float e;
        e = (float)(d*Math.PI/180);
        return e;
    }

    public static void AppStart(String[] args){
        Application application = new Application(args, EMMA);
        application.start();
    }
}
