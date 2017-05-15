package testruns;

/**
 * Created by JNaoHSBO on 10.05.2017.
 */

import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALVideoDevice;
import com.aldebaran.qi.helper.proxies.ALVideoRecorder;
import com.sun.media.jfxmedia.control.VideoDataBuffer;
import javafx.scene.image.Image;
import utillities.Uts;

import java.nio.ByteBuffer;
import java.util.List;
public class Test_Cam {

    public static void main(String[] args) throws Exception {
        Uts.AppStart();

        String subscriberID = "subscriberID";
        ALVideoDevice d = new ALVideoDevice(Uts.getSESSION());
        while(true){
            subscriberID = d.subscribeCamera("Test", 0, 2, 0, 5);
            List<Object> list = (List<Object>) d.getImageRemote(subscriberID);
            ByteBuffer buffer = (ByteBuffer)list.get(6);
           /* byte[] rawData = buffer.array();
            int[] intarray = new int[(int)list.get(0) * (int)list.get(1)];
            for(int i = 0; i<(int)list.get(0) * (int)list.get(1); i++){
                intarray[i] =
                        ((rawData[i*3] & 0xFF) << 16) |
                                ((rawData[i*3+1] & 0xFF)<< 8) |
                                ((rawData[i*3+2] & 0xFF));
            }*/
            //Image image = new Image();

            VideoDataBuffer vdb;
            vdb = (VideoDataBuffer)list.get(6);


            d.releaseImage(subscriberID);
            d.unsubscribe(subscriberID);
        }

    }
}
