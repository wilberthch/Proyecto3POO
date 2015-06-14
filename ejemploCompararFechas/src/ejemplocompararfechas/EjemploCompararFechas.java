/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplocompararfechas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author will
 */
public class EjemploCompararFechas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDate fechaInicio = LocalDate.parse("25/07/2005", dateFormater);
        
        LocalDate fechaFin = LocalDate.parse("25/12/2005", dateFormater);
        
        LocalDate fechaPrueba1 = LocalDate.parse("12/10/2005", dateFormater);
        LocalDate fechaPrueba2 = LocalDate.parse("01/05/2005", dateFormater);
        
        boolean esFechaEnMedio = estaFechaEnMedio(fechaPrueba1, fechaInicio, fechaFin);
        if(esFechaEnMedio)
        {
            System.out.println(fechaPrueba1.toString() + " esta en medio de " + fechaInicio.toString() + " y " + fechaFin.toString());
        }
        else
        {
            System.out.println(fechaPrueba1.toString() + " NO esta en medio de " + fechaInicio.toString() + " y " + fechaFin.toString());
        }
        
        
        
        esFechaEnMedio = estaFechaEnMedio(fechaPrueba2, fechaInicio, fechaFin);
        if(esFechaEnMedio)
        {
            System.out.println(fechaPrueba2.toString() + " esta en medio de " + fechaInicio.toString() + " y " + fechaFin.toString());
        }
        else
        {
            System.out.println(fechaPrueba2.toString() + " NO esta en medio de " + fechaInicio.toString() + " y " + fechaFin.toString());
        }
        
        
        
        
    }
    
    private static boolean estaFechaEnMedio(LocalDate pFechaAComparar, LocalDate pFechaInicio, LocalDate pFechaFin)
    {
        return (pFechaAComparar.compareTo(pFechaInicio) >= 0 && pFechaAComparar.compareTo(pFechaFin) <= 0);
    }
    
}
