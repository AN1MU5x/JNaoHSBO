package movings_Andi_Iskar;

import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALTracker;
import utillities.Utts;

/**
 * Created by JNaoHSBO on 05.04.2017.
 */
public class Moving_1 {

    public static void winken() throws Exception{
        ALTracker a = new ALTracker(Utts.getAPP().session());
        ALMotion suche = new ALMotion(Utts.getSESSION());
    }
}
