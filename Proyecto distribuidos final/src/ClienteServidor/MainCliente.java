/*
 Clase donde se leen y cargan los pacientes que solicitan una cita
 */
package ClienteServidor;

import Archivo.Lector;
import Modelo.Paciente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class MainCliente {

    public static void main(String[] args) {
        vistaCliente visual= new vistaCliente();
        visual.setVisible(true);
        Lector lectura = new Lector();
        Cliente cliente = new Cliente();
        List<Paciente> pacientes = new ArrayList<>();
     
        try {
            pacientes = lectura.cargarPaciente();
        } catch (IOException ex) {
            System.err.println("error en la lectura.");
        }
        Reasignacion reasig = new Reasignacion(visual);
        for (Paciente p : pacientes) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("ClienteServidor.MainCliente.main()"+p.getNombre());
            visual.getjTextArea1().append(cliente.envio(p)+"\n \n");
        }

    }
}
