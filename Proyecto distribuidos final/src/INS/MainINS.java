/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INS;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Leonel
 */
public class MainINS 
{
    public static void main(String[] args) 
    {
       INSvista vistaINS = new INSvista();
       vistaINS.setVisible(true);
     
     
       
       
       try
        {
          Registry registro = LocateRegistry.createRegistry(4444);
          INS entidadExperta =new INS(vistaINS);
          iINS sEntidadExperta =(iINS)UnicastRemoteObject.exportObject(entidadExperta, 0);
          registro = LocateRegistry.getRegistry(4444);
          registro.bind("bPuntaje", sEntidadExperta);
            System.out.println("corriendo server de INS...");
        }
        catch (Exception e) {
            System.out.println("---" + e.getMessage());
        }
        
        
       
        
    }
}
