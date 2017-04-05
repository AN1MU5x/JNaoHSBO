package testruns;

/**
 * Created by Andi on 03.04.2017.
 */

import com.aldebaran.qi.Application;

import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class prob {
    public static void main(String[] args) throws Exception {
        String robotUrl = "tcp://nao.local:9559";
        // Create a new application
        Application application = new Application(args, robotUrl);
        // Start your application
        application.start();
        // Create an ALTextToSpeech object and link it to your current session
        ALTextToSpeech tts = new ALTextToSpeech(application.session());
        // Make your robot say something
        tts.say("Hello World!");
    }
   /* public static void main(String[] args){

        Application application = new Application(args);
        try{
            // Start the application and create a session.
            application.start();
            // A session has been created. It can be retrieved this way:
            // application.session();
        }
        catch(Exception e){
            // The application could not be started.
            e.printStackTrace();
        }
        */

}



