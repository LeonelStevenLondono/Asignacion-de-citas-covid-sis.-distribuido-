/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EPS;

import Archivo.Lector;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonel
 */
public class MainEps 
{
      public static void main(String[] args) throws IOException 
      {
          Lector lectura = new Lector();
          List<Usuario> usuarios= new ArrayList<>();
          Eps principal = new Eps();
       
          usuarios = lectura.cargarUsuarios();
          principal.setBeneficiarios(usuarios);
          String nombre = lectura.nombreEPS();
          principal.setNombre(nombre);
          System.out.println(principal.getNombre());
             EPSvista visual = new EPSvista(principal.getNombre());
          visual.setVisible(true);
              visual.getjLabel1().setText("Usuarios afiliados a: "+principal.getNombre());
          for(Usuario a:usuarios)
          {
              visual.agregar(a.getNombre(), a.getNumDocumento(), a.getCoverturaActual());
          }
      
          try
          { 
              Registry registro = LocateRegistry.createRegistry(5555);
             
               iEPS iPrincipal =(iEPS)UnicastRemoteObject.exportObject(principal, 0);
            registro= LocateRegistry.getRegistry(5555);
               registro.bind("bRegistrado", iPrincipal);
                System.out.println("corriendo server de EPS...");
                
          
          }
         catch (Exception e) 
         {
            System.out.println("---" + e.getMessage());
        }
          
      }
}
