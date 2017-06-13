package testruns;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import com.aldebaran.qi.helper.proxies.ALTracker;
import com.aldebaran.qi.helper.proxies.ALVideoDevice;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utillities.Uts;


import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by iskar on 02.05.17.
 */
public class Test_Tracker{

    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        System.out.println("Successfully connected to the robot");
        Test_Tracker tracker = new Test_Tracker();

       tracker.run(Uts.getAPP().session());



        Uts.getAPP().run();
    }


    public void run(Session session) throws Exception {
        String subscriberID="subscriberID";



        System.out.println("VOr");

            ALVideoDevice d = new ALVideoDevice(session);

            d.subscribeCamera("Test", 0, 2, 0, 5);
            subscriberID = d.subscribe(subscriberID, 2, 0, 5);


           List<Object> o = (List<Object>)d.getImageRemote(subscriberID);

            System.out.println(o);

            d.releaseImage(subscriberID);
            System.out.println(d.releaseImage(subscriberID));
            ByteBuffer buffer = (ByteBuffer)o.get(6);
            System.out.println(buffer);






            d.unsubscribe(subscriberID);




    }


}
