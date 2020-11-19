/*
clase utilizada para almacenar la informacion en memoria de los usuarios pertenecientes a una eps.
 */
package EPS;

/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class Usuario 
{
     private String nombre;
    private String numDocumento;
    private int coverturaActual;

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

    public int getCoverturaActual() {
        return coverturaActual;
    }

    public void setCoverturaActual(int coverturaActual) {
        this.coverturaActual = coverturaActual;
    }
    
}
