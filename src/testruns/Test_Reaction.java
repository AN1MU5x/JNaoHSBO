package testruns;


import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import utillities.Utts;


import java.util.ArrayList;


/**
 * Created by JNaoHSBO on 05.04.2017.
 */
public class Test_Reaction{

    static ArrayList<String> voc = new ArrayList<>();

    public static void main(String[] args) throws Exception{

        voc.add("hello");
        voc.add("lisa");
        voc.add("stop");
        voc.add("andi");
        voc.add("stefan");
        voc.add("iskar");
        Utts.AppStart();
        ALSpeechRecognition alSpeechRecognition = new ALSpeechRecognition(Utts.APP.session());

        alSpeechRecognition.pause(true);
        alSpeechRecognition.setVocabulary(voc, false);
        alSpeechRecognition.pause(false);
        Test_Reaction test_reaction = new Test_Reaction();
        test_reaction.run(Utts.APP.session());
        Utts.APP.run();
    }

    long recID;
    ALMemory memory;
    ArrayList recWord = new ArrayList<String>();

    public void run(Session session) throws Exception {

        memory = new ALMemory(session);
        recID = memory.subscribeToEvent(
                "WordRecognized", arg0 -> {
                    recWord = (ArrayList) memory.getData("WordRecognized");
                    System.out.println(recWord);
                    String name = (String)recWord.get(0);
                    if(!name.equals("lisa")&&(float)recWord.get(1)>0.4f){
                        try {
                            Utts.talk("Hello " + name);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if(name.equals("lisa")&&(float)recWord.get(1)>0.4f) {
                        try {
                            Utts.talk("Hello " + name + " I know You!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if(name.equals("stop")&&(float)recWord.get(1)>0.5f){
                        try {
                            Utts.talk("OK i will stop!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        memory.unsubscribeToEvent(recID);
                        Utts.APP.stop();
                    }else{
                        try {
                            Utts.talk("again please!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
