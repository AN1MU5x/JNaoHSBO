package testruns;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import utillities.Uts;
import java.util.ArrayList;

/**
 * Created by JNaoHSBO on 26.04.2017.
 */
public class Test_Combo {

    private static boolean see=false;
    private static ArrayList allWords;
    private static ALMemory loopEvent;
    private static Test_Events events;

    public static ArrayList getAllWords() {
        return allWords;
    }

    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        allWords = new ArrayList<String>();
        Uts.setNames(new ArrayList());
        Uts.addNames("lisa");
        Uts.addNames("andi");
        Uts.addNames("iskar");
        Uts.addNames("stefan");

        ArrayList<String> uttWords = new ArrayList<>();
        uttWords.add("stop");
        uttWords.add("gut");
        uttWords.add("akku");
        uttWords.add("ja");
        uttWords.add("nein");
        uttWords.add("schlecht");
        uttWords.add("hallo");

        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("wie geht es dir?");
        sentences.add("wer bin ich?");

        for (String m: Uts.getNames()) {
            allWords.add(m);

        }
        for (String m: uttWords) {
            allWords.add(m);

        }

        for (String m: sentences){
            allWords.add(m);
        }
        events = new Test_Events(new ALMemory(Uts.getSESSION()), new ALMemory(Uts.getSESSION()), new ALSpeechRecognition(Uts.getSESSION()), new ALFaceDetection(Uts.getSESSION()), allWords);
        loopEvent = new ALMemory(Uts.getSESSION());
        Test_Combo tc = new Test_Combo();
        tc.run(Uts.getSESSION());
        Uts.getAPP().run();
    }

    public void run(Session session) throws Exception {
        System.out.println("RUN");
        loopEvent.subscribeToEvent("ComboLoop",arg0 ->{
            Thread.sleep(50);
            see=(boolean)arg0;
            if(!see) {
                try {
                    System.out.println("LISTEN");
                    events.reaction();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(see){
                try {
                    System.out.println("WATCH");
                    events.see();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        loopEvent.raiseEvent("ComboLoop",false);

    }
}
