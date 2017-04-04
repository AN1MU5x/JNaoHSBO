package testruns; /**
 * Created by JNaoHSBO on 04.04.2017.
 */

    import com.aldebaran.qi.*;

public class Test_Stefan {

        public static void main(String[] args){
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

        }
}
