package testruns;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALMemory;
import utillities.Utts;

import java.util.ArrayList;

/**
 * Created by JNaoHSBO on 26.04.2017.
 */
public class Test_Combo {

    private static boolean see=false;
    private static ArrayList allWords;
    private static ALMemory loopEvent;


    public static ArrayList getAllWords() {
        return allWords;
    }

    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        allWords = new ArrayList<String>();
        Utts.setNames(new ArrayList());
        Utts.addNames("lisa");
        Utts.addNames("andi");
        Utts.addNames("iskar");
        Utts.addNames("stefan");

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

        for (String m: Utts.getNames()) {
            allWords.add(m);

        }
        for (String m: uttWords) {
            allWords.add(m);

        }

        for (String m: sentences){
            allWords.add(m);
        }
        loopEvent = new ALMemory(Utts.getSESSION());
        Test_Combo tc = new Test_Combo();
        tc.run(Utts.getSESSION());
        Utts.getAPP().run();
    }

    public void run(Session session) throws Exception {
        System.out.println("RUN");
        loopEvent.subscribeToEvent("ComboLoop",arg0 ->{
            see=(boolean)arg0;
            if(!see) {
                System.out.println("LISTEN");
                try {
                    Test_Events.reaction();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(see){
                System.out.println("WATCH");
                try {
                    Test_Events.see();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        loopEvent.raiseEvent("ComboLoop",false);

    }
}
