package javas.time;

import javas.conection.CreateConnection;
import javas.conection.UpdateInfo;
import javas.trees.Abstract.Tree;
import java.util.Random;

/**
 *This class manages the time, in game
 */
public class TimeJava {
    /**
     * Int to save the minutes that are calculated by the class
     */
    private int minutes;
    /**
     *Int to save the seconds calculated by the class
     */
    private int seg;


    public TimeJava(){
        minutes = 0;
        seg = 0;
    }

    /**
     * This method starts a thread to manage the time as it goes by, and using mins and segs .
     * @param minMax maximum minutes for the timer
     */
    public void timeStart(int minMax){
        Thread thread = new Thread(() -> {
            for (minu/**
     *
     */tes = 0; minutes < minMax; minutes++){
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
     *This method opens athread to create a timer for the challenges.
     */
   public void timeChallenge(int segMax){
        Thread thread = new Thread(()->{
            for (seg = 0; seg < segMax; seg++){
                delaySeg();
            }
            UpdateInfo.getUpdateInfo().setChallenge(new String[]{"", "", "", ""});
        });
    }

    /**
     *This method delays a timer thread one second eaxactiol
     */
    private static void delaySeg(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
