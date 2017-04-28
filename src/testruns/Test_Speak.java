package testruns;

import com.aldebaran.qi.helper.proxies.ALAudioPlayer;
import utillities.Utts;

/**
 * Created by Lisa on 25.04.2017.
 */
public class Test_Speak {
    public static void main(String[] args) throws Exception {
    Utts.AppStart();
    ALAudioPlayer ap = new ALAudioPlayer(Utts.getAPP().session());
    //ap.playFile("C:/Users/Lisa/Desktop/instrument4.wav");
    ap
    }
}
