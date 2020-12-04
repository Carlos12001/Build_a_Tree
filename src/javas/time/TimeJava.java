package javas.time;

import javas.conection.CreateConnection;
import javas.conection.UpdateInfo;
import javas.trees.Abstract.Tree;
import java.util.Random;

/**
 *
 */
public class TimeJava {

    /**
     *
     */
    private int minutes;

    /**
     *
     */
    private int seg;


    /**
     *
     */
    public TimeJava(){
        minutes = 0;
        seg = 0;
    }

    /**
     * @param minMax
     */
    public void timeStart(int minMax){
        Thread thread = new Thread(() -> {
            for (minutes = 0; minutes < minMax; minutes++){
                for (seg = 0; seg < 60; seg ++){
                    UpdateInfo.getUpdateInfo().setTime(minutes + ":" + seg);
                    delaySeg();
                }
            }
            UpdateInfo.getUpdateInfo().setTime(minMax + ":00");
        });
        thread.setDaemon(true);
        thread.start();
    }
    /**
     * @param segMax
     */
   /* public void timeChallenge(int segMax){
        Thread thread = new Thread(()->{
            for (seg = 0; seg < segMax; seg++){
                delaySeg();
            }
        });
    }*/
    /**
     *
     */
    private static void delaySeg(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
