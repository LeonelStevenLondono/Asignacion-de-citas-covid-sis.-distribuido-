/*
clase encargada de correr un hilo que tiene como finalidad estar siempre esperando la reasignacion de una cita
 */
package ClienteServidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class Reasignacion extends Thread 
{
    vistaCliente interfac;
    public Reasignacion(vistaCliente vist)
    {
        interfac=vist;
        this.start();
    }
    public void run()
    {
      try
      {
          int puerto =9000;
          ServerSocket notif = new  ServerSocket(puerto);
          while(true)
          {
              Socket escuchar = notif.accept();
              DataInputStream entrar = new DataInputStream(escuchar.getInputStream());
              String mensaje = entrar.readUTF();
              mensaje="reasignacion de fecha para:"+mensaje;
              interfac.getjTextArea1().append(mensaje+"\n \n");
          }
      } catch (IOException ex) {
            Logger.getLogger(Reasignacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

