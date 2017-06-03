package sensors;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import motion.Position;
import utillities.Uts;

public class TactilTouchedEvent {

    private static ALMemory memory;
    private static long frontTactilSubscriptionId;

    public void run(Session session) throws Exception{

        memory = new ALMemory(Uts.getSESSION());

        frontTactilSubscriptionId = memory.subscribeToEvent(
                "FrontTactilTouched", new EventCallback<Float>() {
                    public void onEvent(Float arg0) throws InterruptedException, CallError {
                        System.out.println("Front Tactil Touched");
                        //Hier Anweisung
                        Position.bFollow = false;//Probeweise zum stopen der follow Funktion

                    }
                });
        memory.subscribeToEvent(
                "MiddleTactilTouched", new EventCallback<Float>() {
                    public void onEvent(Float arg0) throws InterruptedException, CallError {
                        System.out.println("Middle Tactil Touched");
                        //Hier Anweisung
                    }
                });
        memory.subscribeToEvent(
                "RearTactilTouched", new EventCallback<Float>() {
                    public void onEvent(Float arg0) throws InterruptedException, CallError {
                        System.out.println("Rear Tactil Touched");
                        //Hier Anweisung
                        if(WordRecognizedEvent.iFunktion==3){
                            WordRecognizedEvent.iFunktion=0;
                            try {
                                Position.stehen();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }
}

