package testruns;

import com.aldebaran.qi.helper.proxies.ALLocalization;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALTracker;
import utillities.Utts;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.CallError;


import java.util.ArrayList;

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

        tr.registerTarget("People",9.2f);
        tr.setMode("Head");
        tr.track("People");

        boolean b=true;
        /*while(b){


            //Thread.sleep(3000);
           // m.moveTo(tr.getTargetPosition());
            System.out.println(tr.getActiveTarget());
            if(tr.getTargetPosition().size()==3){
               // b=false;
               // System.out.println(tr.getTargetPosition());
                //float x=tr.getTargetPosition().get(0);
                //float y= tr.getTargetPosition().get(1);
                //float z=tr.getTargetPosition().get(2);
                //m.move(tr.getTargetPosition().get(0),tr.getTargetPosition().get(1),tr.getTargetPosition().get(2), Position.moveconfigvor());
                //System.out.println(tr.getTargetPosition()+"und die K "+x+" "+y+" "+z);
               // m.moveTo(0.f,0.f,1f);
            }

        }*/
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
                "PeoplePerception/PeopleDetected", new EventCallback() {
                    @Override
                    public void onEvent(Object arg0)
                            throws InterruptedException, CallError {
                        ArrayList a= (ArrayList) arg0;
                        System.out.println(a);



                    }
                });



    }
}

