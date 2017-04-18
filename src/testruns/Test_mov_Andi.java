package testruns;




/**
 * Created by Andi on 06.04.2017.
 */

    import com.aldebaran.qi.*;

    public class Test_mov_Andi {

        public static void main(String[] args) throws Exception {
            Application app = new Application(args,"tcp://Emma.local:9559");
            app.start(); // will throw if connection fails

            Session session = app.session();

            AnyObject tts = session.service("ALTextToSpeech");

            boolean ping = tts.<Boolean>call("ping").get();
            if (!ping) {
                System.out.println("Could not ping TTS");
            } else {
                System.out.println("Ping ok");
            }

            System.out.println("Calling say");
            tts.call("say", "Hello, world");
        }

    }



