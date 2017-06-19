package motion;

import audio.WordRecognizedEvent;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTracker;
import vision.FaceDetectedEvent;

public class Follow {

    //ALTracker ist ein Modul, dass eine Brücke zwischen Zielerfassung und Bewegung aufbaut
    public static ALTracker alTracker;

    //Boole´sche variablen zur Abfrage ob Ziel erfasst oder verloren und ob EMMA folgen soll
    public static boolean bSearch = false;
    public static boolean bFollowOn = false;
    public static boolean bTargetLost = false;

    //FaceDetectedEvent, siehe: vision/FaceDetectedEvent
    private FaceDetectedEvent faceDetectedEvent;

    public void run(Session session) throws Exception{

        //Anlegen eines Objekts von alTracker, mit Übergabe der laufenden session
        alTracker = new ALTracker(session);

        //Aufruf des FaceDetectedEvents
        faceDetectedEvent = new FaceDetectedEvent();

        /* Wenn gesuchte Person erkannt, werden bSearch und bFollowOn in der Klasse FaceDetectedEvent auf true gesetzt
         * und diese unsubscribed. --> EMMA geht in die while-Schleife
         */
        while (bSearch) {

            //Zur Problemvermeidung abfrage ob bFollowOn true ist
            if (bFollowOn) {

                //Wenn Ziel verloren und ID des Ziels gesetzt
                if ((alTracker.isTargetLost() && WordRecognizedEvent.iFunktion==4) || (alTracker.isTargetLost() && WordRecognizedEvent.iFunktion==5) || (alTracker.isTargetLost() && WordRecognizedEvent.iFunktion==6) || (alTracker.isTargetLost() && WordRecognizedEvent.iFunktion==7)) {
                    bFollowOn = false;

                    //Aufruf der Methode stopTracker, um ein folgen einer nicht-gesuchten Person zu verhindern
                    alTracker.stopTracker();

                    //Erneuter Aufruf des FaceDetectedEvents zur erkennung der gesuchten Person
                    faceDetectedEvent.run(session);

                    //Boole´sche variable bTargetLost = true, da Ziel verloren
                    bTargetLost =true;

                    //bSearch auf false setzten zum verlassen der while - Schleife
                    bSearch =false;
                }

                //Wenn Person erkannt "bFollowOn" = true und ID gesetzt (4, 5, 6, 7 ...)
                else if((bFollowOn && WordRecognizedEvent.iFunktion==4) || (bFollowOn && WordRecognizedEvent.iFunktion==5)|| (bFollowOn && WordRecognizedEvent.iFunktion==6) || (bFollowOn && WordRecognizedEvent.iFunktion==7)){

                    //Methode sucht "beliebiges" (durch unsere Klasse nicht mehr beliebig) Gesicht
                    alTracker.track("Face");

                    //Methode setzt den Roboter in Bewegung, mit Daten der Zielerfassung (alTracker.track())
                    alTracker.setMode("Move");

                    //Ziel ist nicht verloren
                    bTargetLost =false;
                }

            }
        }
    }
}