/*
Clase experta,encargada de  verificar y registrar a los pacientes positivos para covid-19
 */
package INS;

import Modelo.Paciente;
import Modelo.Puntaje;
import java.util.ArrayList;
import java.util.List;
import java.rmi.*;

/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class INS implements iINS
{
 private int cantidad;
 private List<Paciente> casos;
 public Puntaje puntaje;
 private INSvista vista;


    public INS(INSvista vist)
    {
     Puntaje puntaje = new Puntaje();
     casos = new ArrayList<>();
     vista= vist;
     vista.setVisible(true);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Paciente> getCasos() {
        return casos;
    }

    public void setCasos(List<Paciente> casos) {
        this.casos = casos;
    }

 

    public INS(int cantidad, List<Paciente> casos) {
        this.cantidad = cantidad;
        this.casos = casos;
        
    }
 /**
  * segun un paciente, evalua sus sintomas y demas patologias para decir si es o no apto para ser atendido
  * @param sintomas
  * @param edad
  * @param sirugias
  * @param patolog
  * @param paci
  * @return
  * @throws RemoteException 
  */
 public int evaluarPaciente(String[] sintomas,int edad,String[] sirugias,String[] patolog,Paciente paci) throws RemoteException
{
        Puntaje punt = new Puntaje();
    int puntaje=0;
    int cont =0;
    for(int i=0;i<sintomas.length;i++)
    {
           
               if(sintomas[i].equals("fiebre"))
               {
                cont++;   
                puntaje=puntaje+5;
               }
               if(sintomas[i].equals("tos"))
               {
                   cont++;
                     puntaje=puntaje+5;
               }
                 if(sintomas[i].equals("cansancio"))
               {
                   cont++;
                     puntaje=puntaje+5;
               }
                   if(sintomas[i].equals("dolor"))
               {
                   cont++;
                     puntaje=puntaje+5;
               }
                      if(sintomas[i].equals("falta de aire"))
               {
                   cont++;
                     puntaje=puntaje+10;
               }
                        if(sintomas[i].equals("insuficiencia pulmonar"))
               {
                   cont++;
                     puntaje=puntaje+10;
               }
                          if(sintomas[i].equals("shock septico"))
               {
                   cont++;
                     puntaje=puntaje+10;
               }
                            if(sintomas[i].equals("falla multiorganica"))
               {
                   cont++;
                     puntaje=puntaje+10;
               }
                          
                   
                   
           
        
    }
    if(edad>=50 )
    {
      puntaje = puntaje+20;
      
    }
    if(edad>=60 )
    {
      puntaje = puntaje+10;
    }
    if(patolog.length>0)
    {
        if(!patolog[0].equals("no tiene"))
        {
            puntaje = puntaje+20;
        }
        
        }
    if(puntaje>100)
    {
        puntaje =100;
    }
    if(puntaje>=70)
    {paci.setCoverturaRequerida(puntaje);
    casos.add(paci);
    vista.agregar(paci);
    }
    //this.puntaje.setNumero(puntaje);
    System.out.println("puntaje"+puntaje);
     return puntaje;
}
}


