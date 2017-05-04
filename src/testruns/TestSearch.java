package testruns;

import com.aldebaran.qi.helper.proxies.ALLocalization;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALTracker;
import utillities.Utts;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.CallError;

/**
 * Created by Daniel on 27.04.2017.
 */
public class TestSearch {
    public static void main(String[] args) throws Exception {
        Utts.AppStart();
        System.out.println("Successfully connected to the robot");
        // Subscribe to selected ALMemory events
        TestSearch reactor = new TestSearch();

        reactor.run(Utts.getSESSION());

        // Run your application
        Utts.getAPP().run();

    }

    ALMemory memory;
    ALLocalization loc;


    public void run(Session session) throws Exception{


        memory = new ALMemory(session);
        ALTracker tr=new ALTracker(session);
        ALMotion m=new ALMotion(session);

        memory.declareEvent("ALLocalization/FullScanBegin");
        //tr.registerTarget("Andi",9.2f);
        tr.registerTarget("Face",9.2f);
        boolean b=true;
        while(b){
            //tr.track("Andi");
           // tr.setMode("Move");
            System.out.println(tr.getTargetPosition());
            //Thread.sleep(3000);
           // m.moveTo(tr.getTargetPosition());
            if(tr.getTargetPosition().size()>0){
               // b=false;
                m.walkTo(tr.getTargetPosition().get(0),tr.getTargetPosition().get(1),tr.getTargetPosition().get(2));
                System.out.println(tr.getTargetNames());
               // m.moveTo(0.f,0.f,1f);
            }
        }
       // m.moveTo(tr.getTargetPosition());
        //tr.track("Andi");

       // tr.setMode("Move");
        //System.out.println(tr.getTargetPosition());
       // tr.setMode("Move");
        //tr.track("Andi");
        Utts.talk("Hallo");
        //tr.unregisterAllTargets();
        System.out.println(tr.getTargetPosition());
      //  tr.trackEvent("ALLocalization/FullScanBegin");
        loc=new ALLocalization(session);

        //loc.learnHome();
        System.out.println(loc.getRobotPosition());
        //loc.goToHome();
        System.out.println(memory.getEventList());







        memory.subscribeToEvent(
                "ALLocalization/FullScanBegin", new EventCallback() {
                    @Override
                    public void onEvent(Object arg0)
                            throws InterruptedException, CallError {
                        System.out.print("HI");



                    }
                });



    }
}

