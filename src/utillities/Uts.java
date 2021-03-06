package utillities;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import java.util.ArrayList;

public class Uts extends Thread {

    private static Application APP;
    private static Session SESSION;
    private static String EMMA = "tcp://Emma.local:9559";
    private static ArrayList<String> names = new ArrayList<>();

    public static Application getAPP() {
        return APP;
    }

    public static Session getSESSION() {
        return SESSION;
    }

    public static String getEMMA() {
        return EMMA;
    }

    public static void setNames(ArrayList names) {
        names = names;
    }

    public static ArrayList<String> getNames() {
        return names;
    }

    public static void addNames(String name){
        names.add(name);
    }

    //Umwandlung von Grad in Radiant für Bewegungen
    public static float DegToRad(int d){
        float e;
        e = (float)(d*Math.PI/180);
        return e;
    }

    //Starten der App auf Emma(!NUR AUF EMMA!)
    public static void AppStart(){
        String[] args = new String[]{""};
        APP = new Application(args, EMMA);
        APP.start();
        SESSION = APP.session();
    }

    public static void AppStop(){
        APP.stop();
    }

    // AppStart Methode zum starten der App auf dem Roboter mit eingegebenen Parametern
    // name und port, daher nicht nur für Emma anwendbar
    public static void AppStart(String name, String port){
        String[] args = new String[]{""};
        APP = new Application(args,"tcp://"+name+":"+port);
        APP.start();
        SESSION = APP.session();
    }

    // Methode zum sprechen
    public static void talk(String txt) throws Exception{
        ALTextToSpeech a = new ALTextToSpeech(APP.session());
        a.say(txt);
    }

    //Methode um den Roboter zu einem durch Koordinaten festgelegten Punkt gehen zu lassen
    public static void walk(float x, float y, int d) throws Exception {
        ALMotion a = new ALMotion(APP.session());
        a.moveTo(x,y,DegToRad(d));

    }

    //Aufnahme eines Gesichts in die bekannten Gesichter
    public static boolean learnFace(String sName) throws Exception {
        ALFaceDetection oA = new ALFaceDetection(Uts.APP.session());
        if(oA.learnFace(sName)){
            return(true);
        }
        else{
            return(false);
        }
    }
}
