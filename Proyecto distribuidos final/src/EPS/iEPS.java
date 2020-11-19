/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EPS;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Leonel
 */
public interface iEPS extends Remote
{
      public boolean coberturaLograda(int coverturaRequerida,String cedula ) throws RemoteException;
       public boolean verificarUsuario(String id) throws RemoteException;
}
