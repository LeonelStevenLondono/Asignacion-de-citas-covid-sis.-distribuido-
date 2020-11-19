/*
clase intermedia que ejecuta la ips
 */
package ClienteServidor;

import INS.INS;
import INS.iINS;
import Modelo.Cita;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;


/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class MainServidor 
{
     public static void main(String[] args) 
    {
     List<Cita> citas = new ArrayList<>();
     try
     {
         VistaServidor vistaSer = new VistaServidor();
         vistaSer.setVisible(true);
         int puerto = 7000;
         ServerSocket escucha = new ServerSocket(puerto);
         System.out.println("Servidor iniciado IPS...");
         while(true)
         {
             Socket clienteSock = escucha.accept();
             IPS central = new IPS(clienteSock,citas,vistaSer);
             
             
         }
     }
     catch(IOException e)
     {
     
     }
    }
    
    
}


