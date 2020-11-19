/*
Clase encargada del manejo de solicitudes y comunicacion con las demas entidades para confirmar 
la asignacion de una cita.
 */
package ClienteServidor;

import EPS.Eps;
import EPS.iEPS;
import INS.iINS;
import Modelo.Cita;
import Modelo.Paciente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class IPS extends Thread {

    private List<Eps> epsRegist;
    private List<Cita> citasAsignadas;
    private int id;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private VistaServidor vista;
    private Socket sock;
/**
 * Constructor de la ips
 * @param sockC
 * @param citas
 * @param vista 
 */
    public IPS(Socket sockC, List<Cita> citas,VistaServidor vista) {
        sock = sockC;
        epsRegist = new ArrayList<>();
        citasAsignadas = new ArrayList<>();
        citasAsignadas = citas;
        this.vista  = vista;
        this.vista.setVisible(true);
        this.start();
    }

    public void run() {
        try {
            //---------------------------------------------------------------------
            //manejo de sockets y creacion de variables auxiliares
            DataOutputStream salida = new DataOutputStream(sock.getOutputStream());
            ObjectInputStream obj = new ObjectInputStream(sock.getInputStream());
            Paciente pacient = (Paciente) obj.readObject();
            Cita asignar = new Cita();
            List<Cita> reasignar = new ArrayList<>();
            

            //-------------------------------------------------------------------
            try {
                    //----------------------------------------------------------
                    //enlase con entidades para el uso de metodos remotos
                    //----------------------------------------------------------
                Registry registry = LocateRegistry.getRegistry("25.105.210.81", 4444);
                Registry registry2 = LocateRegistry.getRegistry("25.105.210.81", 5555);
                iINS sEntidadExperta = (iINS) registry.lookup("bPuntaje");
                iEPS sEntidad = (iEPS) registry2.lookup("bRegistrado");

                int puntaje;
                boolean puntajeReq;
                boolean pertenece;
                //------------------------------------------------------------------------
                //Invocacion de metodos remotos
               //------------------------------------------------------------------------
                puntaje = sEntidadExperta.evaluarPaciente(pacient.getSintomas(), pacient.getEdad(), pacient.getCirugiasEnfermedades(), pacient.getPatologias(), pacient);
                puntajeReq = sEntidad.coberturaLograda(puntaje, pacient.getNumDocumento());
                pertenece = sEntidad.verificarUsuario(pacient.getNumDocumento());
                 //------------------------------------------------------------------------
                  //------------------------------------------------------------------------
                //------------------------------------------------------------------------
                //2fc, verifica que los demas servidores confirmen si se puede seguir con la transaccion
                 //------------------------------------------------------------------------
                  if (pertenece && puntajeReq && (puntaje >= 70)) 
                {

                    LocalDate diallegada = LocalDate.now();
                    LocalDate diaCita;
                    LocalTime horaLLegada = LocalTime.now();
                    LocalTime horacita;
                    asignar.setDocumento(pacient.getNumDocumento());
                    asignar.setEpsCliente(pacient.getEpsAsociado());
                    asignar.setNombre(pacient.getNombre());
                    asignar.setLlegadaDia(diallegada);
                    asignar.setLlegadaHora(horaLLegada);
                     asignar.setIp(sock.getInetAddress().toString().split("/")[1]);
                   
                     //------------------------------------------------------------------------
                     //Asignacion de prioridad del paciente
                     //------------------------------------------------------------------------
                    if (puntaje >= 70 && puntaje < 80) {
                        asignar.setPrioridad(1);
                    } else if (puntaje >= 80 && puntaje < 90) {
                        asignar.setPrioridad(2);
                    } else if (puntaje >= 90 && puntaje <= 100) {
                        asignar.setPrioridad(3);
                    }
                     //------------------------------------------------------------------------
                     //------------------------------------------------------------------------
                    asignar.setIdCita(citasAsignadas.size());
                    Cita aux2;
                    Cita aux = asignar;
                    Cita original = new Cita();
                    //------------------------------------------------------------------------
                    //Asignacion de la primera cita
                     //------------------------------------------------------------------------
                    if (citasAsignadas.isEmpty()) {
                        diaCita = diallegada.plusDays(1);
                        aux.setFechaAsignacion(diaCita);
                        horacita = LocalTime.of(7, 00, 00);
                        aux.setHoracita(horacita);
                        System.out.println("aux primero...." + aux.getNombre());
                        citasAsignadas.add(aux);
                    } else {
                        
                         //------------------------------------------------------------------------
                         //Verifica la necesidad de mover pacientes a otras horas 
                         //------------------------------------------------------------------------
                          
                        for (int i = 0; i < citasAsignadas.size(); i++) 
                        {

                            if (aux.getPrioridad() > citasAsignadas.get(i).getPrioridad()) {
                               

                                aux.setFechaAsignacion(citasAsignadas.get(i).getFechaAsignacion());
                                aux.setHoracita(citasAsignadas.get(i).getHoracita());
                                asignar = aux;
                                aux = citasAsignadas.get(i);
                                //si se mueven citas, se agregan a un arreglo para notificar el cambio.
                                citasAsignadas.set(i, asignar);
                                citasAsignadas.remove(aux);
                                reasignar.add(aux);
                                 vista.limpiar();
                                //     System.out.println("prioridad del principal"+asignar.getPrioridad());
                                //System.out.println("prioridad del secundario"+d.getPrioridad()+d.getNombre()+d.getFechaAsignacion());
                            }

                        }
                      
                        aux2 = citasAsignadas.get(citasAsignadas.size() - 1);
                          //------------------------------------------------------------------------
                          //Se asigna la hora y fecha de la cita segun el paciente
                           //------------------------------------------------------------------------
                        if (citasAsignadas.size() % 5 == 1) {

                            horacita = LocalTime.of(9, 00, 00);
                            diaCita = LocalDate.of(aux2.getFechaAsignacion().getYear(), aux2.getFechaAsignacion().getMonthValue(), aux2.getFechaAsignacion().getDayOfMonth());
                            aux.setHoracita(horacita);
                            aux.setFechaAsignacion(diaCita);
                            citasAsignadas.add(aux);
                            if(!reasignar.isEmpty())
                            {
                              reasignar.add(aux);
                            }
                          
                            System.out.println("modulo igual a 1" + aux.getNombre() + aux.getFechaAsignacion() + aux.getHoracita());
                        } else if (citasAsignadas.size() % 5 == 2) {
                            horacita = LocalTime.of(11, 00, 00);
                            diaCita = LocalDate.of(aux2.getFechaAsignacion().getYear(), aux2.getFechaAsignacion().getMonthValue(), aux2.getFechaAsignacion().getDayOfMonth());
                            aux.setHoracita(horacita);
                            aux.setFechaAsignacion(diaCita);
                            citasAsignadas.add(aux);
                         if(!reasignar.isEmpty())
                            {
                              reasignar.add(aux);
                            }
                        } else if (citasAsignadas.size() % 5 == 3) {
                            horacita = LocalTime.of(13, 00, 00);
                            diaCita = LocalDate.of(aux2.getFechaAsignacion().getYear(), aux2.getFechaAsignacion().getMonthValue(), aux2.getFechaAsignacion().getDayOfMonth());
                            aux.setHoracita(horacita);
                            aux.setFechaAsignacion(diaCita);
                            citasAsignadas.add(aux);
                             if(!reasignar.isEmpty())
                            {
                              reasignar.add(aux);
                            }
                        } else if (citasAsignadas.size() % 5 == 4) {
                            horacita = LocalTime.of(15, 00, 00);
                            diaCita = LocalDate.of(aux2.getFechaAsignacion().getYear(), aux2.getFechaAsignacion().getMonthValue(), aux2.getFechaAsignacion().getDayOfMonth());
                            aux.setHoracita(horacita);
                            aux.setFechaAsignacion(diaCita);
                            citasAsignadas.add(aux);
                            if(!reasignar.isEmpty())
                            {
                              reasignar.add(aux);
                            }
                        } else if (citasAsignadas.size() % 5 == 0) {
                            horacita = LocalTime.of(7, 00, 00);
                            diaCita = LocalDate.of(aux2.getFechaAsignacion().getYear(), aux2.getFechaAsignacion().getMonthValue(), aux2.getFechaAsignacion().getDayOfMonth());
                            diaCita = diaCita.plusDays(1);
                            aux.setHoracita(horacita);
                            aux.setFechaAsignacion(diaCita);
                            citasAsignadas.add(aux);
                             if(!reasignar.isEmpty())
                            {
                              reasignar.add(aux);
                            }
                        }

                    }
                    
                     //------------------------------------------------------------------------
                     //Se busca el paciente por el que se inicio la transaccion
                      //------------------------------------------------------------------------
                    for (Cita b : citasAsignadas) {
                        if (b.getDocumento().equals(pacient.getNumDocumento())) {
                            System.out.println("SE ENCONTRO EL PACIENTE...");
                            original = b;
                        }
                    }
                    ///interfaz grafica
                        for(Cita v: citasAsignadas)
                     {
                         System.out.println("verificacion de repetidos..."+v.getNombre());
                     }
                       
                    
                     citasAsignadas=citasAsignadas.stream().distinct().collect(Collectors.toList());
                     vista.limpiar();
                    vista.agregar(citasAsignadas);
                   
                    
                       //Envio de asignacion principal.
                    salida.writeUTF(pacient.getNombre() + " su cita fue asignada con un puntaje de:" + Integer.toString(puntaje) + "en el dia: " + original.getFechaAsignacion() + "a las " + original.getHoracita() + "horas");
                 
                    //Reenvio de reasignacion.
                    
                    int puerto = 9000;
                    Socket reasignarport = null;
                    DataOutputStream aviso;
                    
                     //------------------------------------------------------------------------
                     //si hubo cambios de horario, se notifica la reasignacion de fecha
                      //------------------------------------------------------------------------
                    reasignar=reasignar.stream().distinct().collect(Collectors.toList());
 
                     
                    for (int b = 0; b < reasignar.size(); b++) 
                    {
                        reasignarport = new Socket(reasignar.get(b).getIp(), puerto);
                        aviso = new DataOutputStream(reasignarport.getOutputStream());
                        aviso.writeUTF(reasignar.get(b).getNombre()+ " su cita fue reasignada para la fecha de "+ reasignar.get(b).getFechaAsignacion()+" a las "+reasignar.get(b).getHoracita()+" horas");
                    }
                 //------------------------------------------------------------------------
                 //se notifica al paciente en caso que no cumpla con los requerimientos para realizar la asignacion de cita
                  //------------------------------------------------------------------------
                      
                } else {
                    if (!pertenece) {
                        salida.writeUTF(pacient.getNombre() + " su cita no pudo ser asignada por que no se encontro su suscripcion a la eps" + pacient.getEpsAsociado());
                    } else if (!puntajeReq) {
                        salida.writeUTF(pacient.getNombre() + " su cita no pudo ser asignada debido a que su cobertura no alcanza para la atencion ");
                    } else if (puntaje < 70) {
                        salida.writeUTF(pacient.getNombre() + " su cita no pudo ser asignada ya que no se concidera sintomas graves " + Integer.toString(puntaje));
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException ex) {
            Logger.getLogger(IPS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IPS.class.getName()).log(Level.SEVERE, null, ex);
        }
     

    }

    public List<Eps> getEpsRegist() {
        return epsRegist;
    }

    public void setEpsRegist(List<Eps> epsRegist) {
        this.epsRegist = epsRegist;
    }

    public List<Cita> getCitasAsignadas() {
        return citasAsignadas;
    }

    public void setCitasAsignadas(List<Cita> citasAsignadas) {
        this.citasAsignadas = citasAsignadas;
    }

    public int getIdentif() {
        return id;
    }

    public void setIdentif(int id) {
        this.id = id;
    }

}
