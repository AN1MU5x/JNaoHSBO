package utillities;

/**
 * Created by JNaoHSBO on 18.04.2017.
 */
public class TimeOut extends Thread {

    private static long delay;
    private static TimeOut timeOut;

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }

    public static TimeOut getTimeOut() {
        return timeOut;
    }

    public static void setTimeOut(TimeOut timeOut) {
        TimeOut.timeOut = timeOut;
    }

    public static TimeOut make(long millis){
        //immer mit .start() aufrufen
        delay = millis+System.currentTimeMillis();
        timeOut = new TimeOut();
        return timeOut;
    }


    public static void avoid(){
        System.out.println("TimeOut INTERRUPTED");
        timeOut.interrupt();
    }

    @Override
    public void run() {
        long currTime = System.currentTimeMillis();
        while((delay -currTime)>0){
            currTime = System.currentTimeMillis();
        }
        Uts.AppStop();
    }
}
