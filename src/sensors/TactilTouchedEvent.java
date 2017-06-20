package sensors;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import motion.Position;

public class TactilTouchedEvent {

    private static ALMemory alMemory;
    private static long frontTactilSubscriptionId;

    public void run(Session session) throws Exception{
        //Erstellt ein Objekt
        alMemory = new ALMemory(session );

        frontTactilSubscriptionId = alMemory.subscribeToEvent(
                "FrontTactilTouched", new EventCallback<Float>() {
                    public void onEvent(Float arg0) throws InterruptedException, CallError {
                        System.out.println("Front Tactil Touched");
                        if(arg0 == 1)
                            Position.bFollow = false;   //Stoppt die Funktion "Komm zu mir" an dieser Stelle
                    }
                });
        alMemory.subscribeToEvent(
                "MiddleTactilTouched", new EventCallback<Float>() {
                    public void onEvent(Float arg0) throws InterruptedException, CallError {
                        System.out.println("Middle Tactil Touched");
                        //Hier Anweisung
                    }
                });
        alMemory.subscribeToEvent(
                "RearTactilTouched", new EventCallback<Float>() {
                    public void onEvent(Float arg0) throws InterruptedException, CallError {
                        System.out.println("Rear Tactil Touched");
                        //Aktion zum wieder aufstehen des Nao´s, wenn dieser auf dem Bauch liegt
                        if(WordRecognizedEvent.iFunktion==3){
                            WordRecognizedEvent.iFunktion=0;
                            try {
                                Position.stehen(session);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }
}

