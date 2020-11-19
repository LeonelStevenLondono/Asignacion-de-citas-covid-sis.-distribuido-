/*
clase usada para almacenar los datos de un paciente que se le asigno una cita en algun horario
 */
package Modelo;

import EPS.Eps;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class Cita
{
 private String documento;
 private LocalDate llegadaDia;
 private LocalTime llegadaHora;
 private LocalDate fechaAsignacion;
 private LocalTime horacita;
 private String epsCliente;
 private int prioridad;
 private int IdCita;
 private String nombre;
 private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Cita() {
        
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public LocalDate getLlegadaDia() {
        return llegadaDia;
    }

    public void setLlegadaDia(LocalDate llegadaDia) {
        this.llegadaDia = llegadaDia;
    }

    public LocalTime getLlegadaHora() {
        return llegadaHora;
    }

    public void setLlegadaHora(LocalTime llegadaHora) {
        this.llegadaHora = llegadaHora;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public LocalTime getHoracita() {
        return horacita;
    }

    public void setHoracita(LocalTime horacita) {
        this.horacita = horacita;
    }

    public String getEpsCliente() {
        return epsCliente;
    }

    public void setEpsCliente(String epsCliente) {
        this.epsCliente = epsCliente;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getIdCita() {
        return IdCita;
    }

    public void setIdCita(int IdCita) {
        this.IdCita = IdCita;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    public Cita(String documento, LocalDate fechaAsignacion, String epsCliente, int prioridad, int IdCita) {
        this.documento = documento;
        this.fechaAsignacion = fechaAsignacion;
        this.epsCliente = epsCliente;
        this.prioridad = prioridad;
        this.IdCita = IdCita;
    }
 
}
