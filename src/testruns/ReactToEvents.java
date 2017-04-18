package testruns;
        import com.aldebaran.qi.Application;
        import com.aldebaran.qi.CallError;
        import com.aldebaran.qi.Session;
        import com.aldebaran.qi.Tuple2;
        import com.aldebaran.qi.helper.ALMemoryHelper;
        import com.aldebaran.qi.helper.ALProxy;
        import com.aldebaran.qi.helper.EventCallback;
        import com.aldebaran.qi.helper.proxies.ALFaceDetection;
        import com.aldebaran.qi.helper.proxies.ALMemory;
        import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
        import utillities.Utts;
        import java.lang.Object;

        import java.util.ArrayList;
        import java.util.LinkedList;

public class ReactToEvents {

    public static void main(String[] args) throws Exception {
        String robotUrl = "tcp://Emma.local:9559";
        // Create a new application
        Application application = new Application(args, robotUrl);
        // Start your application
        application.start();
        System.out.println("Successfully connected to the robot");
        // Subscribe to selected ALMemory events
        ReactToEvents reactor = new ReactToEvents();
        reactor.run(application.session());

        // Run your application
        application.run();
    }

    ALMemory memory;
    ALTextToSpeech tts;
    ALFaceDetection face;

    long frontTactilSubscriptionId;




    public void run(Session session) throws Exception {

        memory = new ALMemory(session);

        tts = new ALTextToSpeech(session);
        frontTactilSubscriptionId = 0;
        face = new ALFaceDetection(session);

        System.out.println("Ausgabe der gesichter "+face.getLearnedFacesList());

        System.out.println("Start prob");

        face.subscribe("Test",500,0.0f);
      frontTactilSubscriptionId= memory.subscribeToEvent("FaceDetected",
                new EventCallback() {
                    @Override
                    public void onEvent(Object o)
                            throws InterruptedException, CallError {
                       System.out.println("Face detected");

                        ArrayList result = (ArrayList) o;
                        System.out.println("Größe des result"+result.size());

                        if(result.size()>1) {

                        System.out.println("Hallo");
                        ArrayList probe1= (ArrayList) result.get(1);
                        System.out.println("größe von probe1= "+probe1.size());
                       // ArrayList proben = (ArrayList) ((ArrayList) ((ArrayList) result.get(1)).get(1)).get(1);
                        /*
                            ArrayList faceInfoArray = (ArrayList) result.get(1);
                            if(faceInfoArray.size()>1) {
                                ArrayList proben = (ArrayList) ((ArrayList) ((ArrayList) result.get(1)).get(1)).get(1);
                                if(proben.size()==1) {
                                    //ArrayList proben = (ArrayList) ((ArrayList) ((ArrayList) result.get(1)).get(1)).get(1);
                                    System.out.println(proben);
                                    String a = (String) proben.get(0);
                                    System.out.println(a);
                                    tts.say("Hi you are " + a);
                                    System.out.println("Im if" + proben.size());
                                }
                            }
                            */
                        }

                    }
                });


      //memory.declareEvent("FaceDetection");
       // System.out.println(frontTactilSubscriptionId2);
        frontTactilSubscriptionId = memory.subscribeToEvent(
                "FrontTactilTouched", new EventCallback<Float>() {
                    @Override
                    public void onEvent(Float arg0)
                            throws InterruptedException, CallError {
                        // 1 means the sensor has been pressed
                        if(arg0==1) {
                            tts.say("ouch!");
                        }
                        System.out.println("Ja");

                    }
                });


    }




}