package javathings.conection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class  ServerSocketClass implements Runnable{
    UpdateInfo serverInfo;

    ServerSocketClass(String time){
        serverInfo = new UpdateInfo(time);
    }

    public void iniciarEscuchar(){
        Thread miHilo= new Thread(this);
        miHilo.start();

    }

    public void enviar(String mensaje){
        try {
            Socket misocket = new Socket("192.168.100.11",9998);
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
                System.out.println("Info received: " + infoToUpdate);

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

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hola desde Servidor");

        ServerSocketClass hilito= new ServerSocketClass("6000");

        hilito.iniciarEscuchar();

    }
}




