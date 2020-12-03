package javathings.Time;

import javathings.conection.CreateConnection;
import javathings.conection.UpdateInfo;
import javathings.trees.abstracTree.Tree;
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
    public void timeToken(int segMax){
        Thread thread = new Thread(()-> {
            for (seg = 0; seg < segMax; seg++) {
                delaySeg();
            }
            Tree[] tmp = CreateConnection.treeArray;
            boolean complete = false;

            Tree current2 = null;

            for (Tree current : tmp) {
                if (current.getCurrent().split("@")[1].equals("-1")) {
                    complete = true;
                    current2 = current;
                    break;
                }
            }

            if (!complete) {
                java.util.Random Random = new Random();
                Tree treeTmp = tmp[Random.nextInt(tmp.length)];
                UpdateInfo.getUpdateInfo().setTokenSend(treeTmp.getCurrent());
            } else {
                UpdateInfo.getUpdateInfo().setTokenSend(current2.getCurrent());
            }

        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * @param segMax
     */
    public void timeChallenge(int segMax){
        Thread thread = new Thread(()->{
            for (seg = 0; seg < segMax; seg++){
                delaySeg();
            }
        });
    }
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
