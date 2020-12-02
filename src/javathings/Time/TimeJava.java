package javathings.Time;

import javathings.conection.UpdateInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLOutput;

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
        Thread thread = new Thread(()->{
            for (seg = 0; seg < segMax; seg++){
                delaySeg();
            }
//            UpdateInfo newToken = new UpdateInfo();
//            newToken.setTokenSend("treeAVL@12");  // Aqui se pone un metodo que tome tokens de manera aleatoria
        });
        thread.setDaemon(true);
        thread.start();
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
