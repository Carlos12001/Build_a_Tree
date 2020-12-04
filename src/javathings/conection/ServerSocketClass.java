package javathings.conection;

import javas.conection.JacksonDecoder;
import javas.conection.JacksonEncoder;
import javas.conection.UpdateInfo;
import javas.time.TimeJava;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class  ServerSocketClass implements Runnable{
    UpdateInfo serverInfo;
    Boolean inChallenge = false;
    Boolean waitingChallenge = false;
    int timeTillChallenge = 8;
    int timeTillToken = 4;
    int ChallengeCounter = 0;

    ServerSocketClass(){
        serverInfo = UpdateInfo.getUpdateInfo();
    }

    public void iniciarEscuchar(){
        Thread miHilo= new Thread(this);
        miHilo.start();

    }

    public void enviar(String mensaje){
        try {
            Socket misocket = new Socket("127.0.0.1",9998);
            BufferedOutputStream flujo_salida=new BufferedOutputStream(misocket.getOutputStream());
            flujo_salida.write(mensaje.getBytes());
            flujo_salida.close();
            misocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                ServerSocket servidor = new ServerSocket(9999);
                Socket misocket= servidor.accept();
                BufferedReader flujo_entrada=new BufferedReader(new InputStreamReader(misocket.getInputStream()));
                String mensaje_texto= flujo_entrada.readLine();
                /* Aquí procesamos la información */

                UpdateInfo infoToUpdate = new JacksonDecoder(mensaje_texto).Decode();
                serverInfo.UpdateFile(infoToUpdate);

                System.out.println("Info received: " + infoToUpdate);

                this.ChallengeCounter++;
                System.out.println("Challenge Counter: " + this.ChallengeCounter);
                ChallengeManager();

                String answerJsonStr = new JacksonEncoder().EncodeInfo(this.serverInfo);

                this.enviar(answerJsonStr);
                System.out.println(mensaje_texto);
                flujo_entrada.close();
                servidor.close();
                misocket.close();

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String[] ChallengeAssigner(){
        String[] names = serverInfo.getPlayersName();
        boolean[] states = serverInfo.getPlayersGameOver();
        String[] challengeList = {"TreeAVL","TreeSplay","TreeB","TreeBST"};
        String[] challenges = {"","","",""};

        for(int n=0; n < names.length; n++) {
            if (states[n]) {
                challenges[n] = challengeList[n];
            }
            else {
                challenges[n] = "";
            }
        }
        return challenges;

    }

    public void ChallengeManager(){
        if(timeTillChallenge == 0){
            pickChallengeTime();
        }

        else{
            if (!this.inChallenge){
                if (this.ChallengeCounter == this.timeTillChallenge){
                    this.ChallengeCounter = 0;
                    this.inChallenge = true;
                    String[] challengeUpdate = ChallengeAssigner();
                    serverInfo.setChallenge(challengeUpdate);
                }
            }
        }
        if(this.inChallenge){
            if (this.ChallengeCounter == this.timeTillToken){
                this.ChallengeCounter = 0;
            }
        }
    }

    public void pickChallengeTime(){
        int minSecs = 40;
        int maxSecs = 95;
        //this.timeTillChallenge = (int)(Math.random() * (maxSecs - minSecs + 1) + minSecs);
        //System.out.println("time till ch: " + this.timeTillChallenge);
    }

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hola desde Servidor");

        ServerSocketClass hilito= new ServerSocketClass();

        TimeJava newTime = new TimeJava();
        newTime.timeStart(1);
        hilito.iniciarEscuchar();

    }
}




