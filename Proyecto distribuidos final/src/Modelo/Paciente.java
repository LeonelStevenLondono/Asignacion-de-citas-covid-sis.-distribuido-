/*
Clase con todos los datos de un solicitante de cita
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Leonel
 */
public class Paciente extends Thread implements Serializable
{
    private String nombre;
    private String numDocumento;
    private int edad;
    private String[] sintomas;
    private String EpsAsociado;
    private List<Cita> historial;
    private String[] cirugiasEnfermedades;
    private Date horaEnvio;
    private int coverturaRequerida;
    private int coverturaActual;
    private String[] patologias;
    public Paciente()
    {
        this.nombre = "";
        this.numDocumento = "";
        this.edad = 0;
        this.EpsAsociado = null;
        this.historial = null;
        this.cirugiasEnfermedades = null;
        this.horaEnvio =null;
        this.sintomas =null;
    }

    public String[] getPatologias() {
        return patologias;
    }

    public void setPatologias(String[] patologias) {
        this.patologias = patologias;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String[] getSintomas() {
        return sintomas;
    }

    public void setSintomas(String[] sintomas) {
        this.sintomas = sintomas;
    }

    public String getEpsAsociado() {
        return EpsAsociado;
    }

    public void setEpsAsociado(String EpsAsociado) {
        this.EpsAsociado = EpsAsociado;
    }

    public List<Cita> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Cita> historial) {
        this.historial = historial;
    }

    public String[] getCirugiasEnfermedades() {
        return cirugiasEnfermedades;
    }

    public void setCirugiasEnfermedades(String[] cirugiasEnfermedades) {
        this.cirugiasEnfermedades = cirugiasEnfermedades;
    }

    public Date getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(Date horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

    public int getCoverturaRequerida() {
        return coverturaRequerida;
    }

    public void setCoverturaRequerida(int coverturaRequerida) {
        this.coverturaRequerida = coverturaRequerida;
    }

    public int getCoverturaActual() {
        return coverturaActual;
    }

    public void setCoverturaActual(int coverturaActual) {
        this.coverturaActual = coverturaActual;
    }

    public Paciente(String nombre, String numDocumento, int edad, String EpsAsociado, List<Cita> historial, String[] cirugiasEnfermedades,Date envio,String[] sintomas,String[] patologias) {
        this.nombre = nombre;
        this.numDocumento = numDocumento;
        this.edad = edad;
        this.EpsAsociado = EpsAsociado;
        this.historial = historial;
        this.cirugiasEnfermedades = cirugiasEnfermedades;
        this.horaEnvio =envio;
        this.sintomas = sintomas;
        this.patologias = patologias;
    }
    
    public void generarSolicitud()
    {
        //esto se hace en una hora y minuto especifico
    }
}
