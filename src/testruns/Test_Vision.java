package testruns;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALLocalization;

/**
 * Created by Lisa on 05.04.2017.
 */
public class Test_Vision {

    public static void main(String[] args) {

        String robotUrl = "tcp://Emma.local:9559";
        Application application = new Application(args, robotUrl);
        application.start();

        ALLocalization a = new ALLocalization(application.session());
        a.learnHome();
    }
}
