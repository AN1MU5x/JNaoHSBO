package testruns;
        import com.aldebaran.qi.Application;
        import com.aldebaran.qi.CallError;
        import com.aldebaran.qi.Session;
        import com.aldebaran.qi.helper.EventCallback;
        import com.aldebaran.qi.helper.proxies.ALFaceDetection;
        import com.aldebaran.qi.helper.proxies.ALMemory;
        import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
        import java.lang.Object;

        import java.util.ArrayList;


public class Test_Vision_Andi {

    public static void main(String[] args) throws Exception {
        String robotUrl = "tcp://Emma.local:9559";
        // Create a new application
        Application application = new Application(args, robotUrl);
        // Start your application
        application.start();
        System.out.println("Successfully connected to the robot");
        // Subscribe to selected ALMemory events
        Test_Vision_Andi reactor = new Test_Vision_Andi();
        reactor.run(application.session());

        // Run your application
        application.run();
    }

    ALMemory memory;
    ALTextToSpeech tts;
    ALFaceDetection face;





    public void run(Session session) throws Exception {

        memory = new ALMemory(session);

        tts = new ALTextToSpeech(session);
        face = new ALFaceDetection(session);

        System.out.println("Ausgabe der gesichter "+face.getLearnedFacesList());

        System.out.println("Start prob");

         face.subscribe("Test",500,0.0f);
        memory.subscribeToEvent("FaceDetected",
                new EventCallback() {
                    @Override
                    public void onEvent(Object o)
                            throws InterruptedException, CallError {
                                System.out.println("Face detected");

                                ArrayList ergebnis = (ArrayList) o;


                                if(ergebnis.size()>1) {


                                    ArrayList faceInfoArray= (ArrayList) ergebnis.get(1);



                                    if(faceInfoArray.size()>1){
                                        ArrayList timeFilteredRecoInfo=(ArrayList)faceInfoArray.get(1);
                                        if(timeFilteredRecoInfo.size()>=2){


                                               ArrayList nameImArray = (ArrayList) timeFilteredRecoInfo.get(1);
                                               String name;
                                               name = "" + nameImArray.get(0);
                                               System.out.println(name);
                                               tts.say(name);




                                        }
                                    }

                        }

                    }
                });



    }




}