/*
 Clase encargada del envio de solicitudes al igual que la recepcion de confirmaciones o denegaciones.
 */
package ClienteServidor;

import Archivo.Lector;
import Modelo.Paciente;
import INS.iINS;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class Cliente 
{
    Cliente()
    {
    
    }   
    /**
     * envia a la IPS los datos de un paciente y retorna la respuesta del servidor.
     * @param paciente
     * @return 
     */
    public String envio(Paciente paciente)
    {
          
      Socket socket = null;
      String recibido = "";
        try {
            int puerto = 7000;
            String host = "127.0.0.1";
            socket = new Socket(host, puerto);
            DataInputStream in = new DataInputStream(socket.getInputStream());

            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            os.writeObject(paciente);

             recibido = in.readUTF();
            recibido = "mensaje de la IPS: " + recibido;
            socket.close();
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
        
   return recibido; 
    }
    
}
