/*
 Clase encargada de la lectura de archivos dentro del sistema 
*/
package Archivo;

import EPS.Usuario;
import Modelo.Paciente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class Lector 
{   
    /**
     * carga a los pacientes que seran  usados en la clase cliente.
     * @return la lista de pacientes que realizan solicitud
     * @throws FileNotFoundException
     * @throws IOException 
     */
public List<Paciente> cargarPaciente() throws FileNotFoundException, IOException
{
 
    File archivo = new File("texto/configuracion.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
    
        List<Paciente> enfermosl = new ArrayList<>();
   
           String linea = null;
            linea = br.readLine();
        
          while (!linea.equals("final")) 
          {         Paciente aux = new Paciente();
              String[] datos;
              String[] cirugias;
              String cirugiaCad;
              String sintomasCad;
              String[] sintomas;
              String[] patologias;
              String patologia;
              datos=linea.split("-");
              aux.setNombre(datos[0]);
                System.out.println("--"+datos[0]);
              aux.setNumDocumento(datos[1]);
              aux.setEdad(Integer.parseInt(datos[2]));
               sintomasCad = datos[3];
               sintomas = sintomasCad.split(",");
               System.out.println("--"+sintomas[0]);
               aux.setSintomas(sintomas);
               aux.setEpsAsociado(datos[4]);
               cirugiaCad = datos[5];
              cirugias = cirugiaCad.split(",");
              aux.setCirugiasEnfermedades(cirugias);
              patologia = datos[6];
              patologias =patologia.split(",");
              aux.setPatologias(patologias);
              
              enfermosl.add(aux);
              linea=br.readLine();
          }
        
    return enfermosl;
}
/**
 * Carga los usuarios que hacen parte de la eps junto  a sus respectivos datos
 * @return los usuarios que estan en el documento de la EPS
 * @throws FileNotFoundException
 * @throws IOException 
 */
public List<Usuario> cargarUsuarios() throws FileNotFoundException, IOException
{
    //PUEDE ARROJAR ERROR POR EL NULL
    File archivo = new File("texto/test.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
    
        List<Usuario> usuarios = new ArrayList<>();
   
           String linea = null;
            linea = br.readLine();
        
          while (!linea.equals("final")) 
          {         Usuario aux = new Usuario();
              String[] datos;
              datos=linea.split("-");
              aux.setNombre(datos[0]);
                System.out.println("--"+datos[0]);
              aux.setNumDocumento(datos[1]);
              aux.setCoverturaActual(Integer.parseInt(datos[2]));
              usuarios.add(aux);
              linea=br.readLine();
          }
        
    return usuarios;
}
/**
 * Carga el nombre de la EPS junto algunos datos extra.
 * @return el nombre que tendra la  EPS
 * @throws FileNotFoundException 
 */
public String nombreEPS() throws FileNotFoundException
{
String nombre;
  File archivo = new File("texto/eps.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
         String linea = null;
    try {
        linea = br.readLine();
        nombre=linea;
    } catch (IOException ex) {
        Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
    }
return linea;
}

}
