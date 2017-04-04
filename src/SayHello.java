/**
 * Created by iskar on 04.04.17.
 */

//probe
import com.aldebaran.qimessaging.*;
import javafx.application.Application;

public class SayHello {
    public static void main(String [] args) throws Exception{
        Application app = new Application(args);
        Session session = new Session();
        Future<void> fut = session.connect("tcp://nao.local:9559");
        synchronized (fut){
            fut.wait(1000);
        }

        com.aldebaran.qimessaging.Object tts = null;
        tts = session.service("ALTextToSpeech");
        tts.call("sai", "hello, world");
    }
}
