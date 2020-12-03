package javathings.main;

import javathings.conection.CreateConnection;

/**
 *
 */
public class Server {

    /**
     * @param args
     */
    
    public static void main(String[] args){

        CreateConnection mainThread= new CreateConnection();


        mainThread.iniciarEscuchar();


    }

}
