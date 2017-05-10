package sensors;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import utillities.Uts;

public class TactilTouchedEvent {

    private static ALMemory memory;
    private static long frontTactilSubscriptionId;

    public void FrontTactil(Session session) throws Exception{
        memory = new ALMemory(Uts.getSESSION());

        frontTactilSubscriptionId = memory.subscribeToEvent(
                "FrontTactilTouched", new EventCallback<Float>() {
                    public void onEvent(Float arg0)
                            throws InterruptedException, CallError {
                        try {
                            if(arg0==1){
                                Uts.talk("Streichel dich doch selbst");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void MiddleTactil(Session session) throws Exception{
        memory = new ALMemory(Uts.getSESSION());

        frontTactilSubscriptionId = memory.subscribeToEvent(
                "MiddleTactilTouched", new EventCallback<Float>() {
                    public void onEvent(Float arg0)
                            throws InterruptedException, CallError {
                        try {
                            if(arg0==1){
                                Uts.talk("Ich mag das");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void RearTactil(Session session) throws Exception{
        memory = new ALMemory(Uts.getSESSION());

        frontTactilSubscriptionId = memory.subscribeToEvent(
                "FrontTactilTouched", new EventCallback<Float>() {
                    public void onEvent(Float arg0)
                            throws InterruptedException, CallError {
                        try {
                            if(arg0==1){
                                Uts.talk("Nicht von hinten Streicheln");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
//Einbinden in Main 
/*
public static void main(String[] args) throws Exception {
       Uts.AppStart();
       TactilTouchedEvent tactilTouchedEvent = new TactilTouchedEvent();
       tactilTouchedEvent.FrontTactil(Uts.getSESSION());
       tactilTouchedEvent.RearTactil(Uts.getSESSION());
       tactilTouchedEvent.MiddleTactil(Uts.getSESSION());
       Uts.getAPP().run();
       }
*/
