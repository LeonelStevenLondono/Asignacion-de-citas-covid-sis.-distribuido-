/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EPS;

import Modelo.Paciente;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class Eps implements iEPS {

    private String nombre;
    private String id;
    private List<Usuario> beneficiarios;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Usuario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<Usuario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }
/**
 * verifica si un usuario hace parte de la EPS
 * @param id
 * @return
 * @throws RemoteException 
 */
    
    public boolean verificarUsuario(String id) throws RemoteException{
      
        for (int i = 0; i < beneficiarios.size(); i++) {
            if (beneficiarios.get(i).getNumDocumento().equals(id) ) 
            {
                return true;
            }
        }
        return false;
    }
/**
 * verifica si un paciente cumple con la covertura requerida
 * @param coverturaRequerida
 * @param cedula
 * @return
 * @throws RemoteException 
 */
    public boolean coberturaLograda(int coverturaRequerida, String cedula) throws RemoteException {
        for (int i = 0; i < beneficiarios.size(); i++) {
            System.out.println("EPS.Eps.coberturaLograda()-----"+beneficiarios.get(i).getNombre());
            if (beneficiarios.get(i).getNumDocumento().equals(cedula)) {
                if (beneficiarios.get(i).getCoverturaActual() >= coverturaRequerida) {
                    return true;
                }
            }

        }
        return false;
    }
}
