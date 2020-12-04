import javas.conection.CreateConnection;
import javas.time.TimeJava;


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
