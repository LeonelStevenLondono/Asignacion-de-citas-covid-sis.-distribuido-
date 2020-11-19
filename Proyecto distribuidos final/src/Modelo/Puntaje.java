/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.io.Serializable;
/**
 *
 * @author Leonel
 */
public class Puntaje implements Serializable
{
    int numero;
    
    public Puntaje()
    {
        numero  = 0;
        
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
