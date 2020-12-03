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
        TimeJava newTime = new TimeJava();
        newTime.timeStart(20000);

        mainThread.iniciarEscuchar();


    }

}
