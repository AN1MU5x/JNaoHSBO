package testruns;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;

/**
 * Created by Lisa on 05.04.2017.
 */
public class Test_Vision {

    public static void main(String[] args) throws Exception {

        String robotUrl = "tcp://Emma.local:9559";
        Application application = new Application(args, robotUrl);
        application.start();

        ALMotion a = new ALMotion(application.session());
        a.moveTo(0f,0f,3.14f);



    }
}
