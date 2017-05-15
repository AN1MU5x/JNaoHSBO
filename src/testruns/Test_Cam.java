package testruns;

/**
 * Created by JNaoHSBO on 10.05.2017.
 */

import com.aldebaran.qi.helper.proxies.ALVideoRecorder;
import utillities.Uts;

public class Test_Cam {

    public static void main(String[] args) throws Exception {
        Uts.AppStart();
        ALVideoRecorder recorder = new ALVideoRecorder(Uts.getSESSION());
        recorder.setCameraID(0);
        System.out.println("Framerate: "+recorder.getFrameRate());
        System.out.println("Format: "+recorder.getVideoFormat());
        if(!recorder.isRecording()) {
            System.out.println("start recording");
            recorder.startRecording("/home/nao/recordings/cameras/stream", "VideoStream");
            Thread.sleep(10000);
            recorder.stopRecording();
        }else{
            System.out.println("isRecording");
        }
        Uts.AppStop();

    }
}
