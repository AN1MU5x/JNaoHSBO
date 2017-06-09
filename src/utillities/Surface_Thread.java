package utillities;

/**
 * Created by JNaoHSBO on 07.06.2017.
 */
public class Surface_Thread extends Thread{
    public void run(){
        try {
            User_Surface_1.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
