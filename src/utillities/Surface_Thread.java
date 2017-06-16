package utillities;

/**
 * Created by JNaoHSBO on 07.06.2017.
 */
public class Surface_Thread extends Thread{

    //Benutzeroberfläche als Thread, um den Ablauf flüssiger zu machen
    public void run(){
        try {
            User_Surface.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
