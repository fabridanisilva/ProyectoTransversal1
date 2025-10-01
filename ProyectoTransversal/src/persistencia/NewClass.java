/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Modelo.Alumno;
import java.time.LocalDate;

/**
 *
 * @author fabri
 */
public class NewClass {
    public static void main(String[] args) {
        Alumno alumno = new Alumno(1234312,"julian","alvarez",LocalDate.of(2000, 4, 1),true);
        
        AlumnoData al = new AlumnoData();
        
        //al.guardarAlumno(alumno);
        //al.modificarAlumno(alumno);
        
        //al.eliminarAlumno(1);
        
        
        System.out.println(al.buscarAlumnoPorDNI(1234312));
    }
    
    
}
