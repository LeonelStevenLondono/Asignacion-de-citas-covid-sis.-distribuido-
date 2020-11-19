/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INS;
import Modelo.Paciente;
import Modelo.Paciente;
import java.rmi.*;
/**
 *
 * @author Leonel
 */
public interface iINS extends Remote
{
     public int evaluarPaciente(String[] sintomas,int edad,String[] sirugias,String[] patolog,Paciente paci) throws RemoteException;
     
}
