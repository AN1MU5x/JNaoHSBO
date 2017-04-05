package testruns;

/**
 * Created by Test_Turn on 04.04.2017.
 */
    import com.aldebaran.qi.Application;
    import com.aldebaran.qi.helper.proxies.ALMotion;


public class Test_Turn {

        public static void main(String[] args) throws Exception {
            String robotUrl = "tcp://Emma.local:9559";
            // Create a new application
            Application application = new Application(args, robotUrl);
            // Start your application
            application.start();

            ALMotion a = new ALMotion(application.session());
           a.moveTo(0.0f,0.0f,1.571f);
        }
    }

