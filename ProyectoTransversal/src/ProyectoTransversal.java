
import Modelo.Alumno;
import Modelo.Conexion;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.time.LocalDate;
import persistencia.AlumnoData;
import persistencia.InscripcionData;
import persistencia.MateriaData;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chich
 */
public class ProyectoTransversal {
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
       //Connection con=Conexion.getConexion();//verificacion si esta conectado a BD
       AlumnoData alu=new AlumnoData();
       Alumno Fabricio=new Alumno(21908345,"Silva","fabricio daniel",LocalDate.of(2000, 3, 12), true );
        
        //alu.guardarAlumno(Fabricio);
        Alumno emmanuel=new Alumno(21908315,"vasquez","emmanuel",LocalDate.of(2000, 3, 12), true );
        //alu.guardarAlumno(emmanuel);
        Alumno lucas=new Alumno(21928345,"salcedo"," lucas ariel",LocalDate.of(2000, 3, 12), true );
        //alu.guardarAlumno(lucas);
        
        
       // alu.modificarAlumno(Fabricio);
       //alu.eliminarAlumno(1);
       
      /*
       Alumno alumnoEncontrado=alu.buscarAlumno(3);
       if(alumnoEncontrado!=null){
        System.out.println("dni:"+alumnoEncontrado.getDni());
        System.out.println("apellido:"+alumnoEncontrado.getApellido());
        System.out.println("nombre:"+alumnoEncontrado.getNombre());
        
       }
      */
        
        /*
        for(Alumno alumno:alu.listarAlumnos()){
            
            System.out.println(alumno.getDni());
            System.out.println(alumno.getApellido());
            System.out.println(alumno.getNombre());
            System.out.println(alumno.getFechaNacimiento());
        }
        ¨*/
        
        
        Materia Laboratorio=new Materia("laboratorio", 2025, true );
        MateriaData mat=new MateriaData();
        //mat.agregarMateria(Laboratorio);
        Materia BasedeDatos=new Materia("basededatos", 2025, true );
        //mat.agregarMateria(BasedeDatos);
        Materia Web=new Materia("web", 2025, true );
        //mat.agregarMateria(Web);
        
        
        
        
        
        /*
        for(Materia materia:mat.mostrarMaterias()){
            System.out.println(materia.getNombre());
            System.out.println(materia.getAnioMateria());

            
        }
        */
        
        
        
        
        
        
        
        /*
        Alumno alumnoEncontrado=alu.buscarAlumno(3);
        System.out.println("dni: "+alumnoEncontrado.getDni());
        System.out.println("apellido: "+alumnoEncontrado.getApellido());
        */
        
        
      
       
       InscripcionData id=new InscripcionData();
       
       Alumno ariel=alu.buscarAlumno(3);
       Materia materia=mat.buscarMateria(1);
       Inscripcion insc=new Inscripcion(ariel,materia,9);
       
       
       //id.guardarInscripcion(insc);
       //id.actualizarNota(3, 1, 5);
       //id.borrarInscripcionMateriaAlumno(3, 1);
       
       /*
       for(Inscripcion inscripcion:id.obtenerInscripciones()){
           System.out.println("id"+inscripcion.getIdInscripcion());
           System.out.println("Appelido"+inscripcion.getAlumno().getApellido());
           System.out.println("Materia"+inscripcion.getMateria().getNombre());
       }
       */
       
        
        
        
        /*
        InscripcionData ins=new InscripcionData();
        for(Inscripcion inscripcion:ins.ListarInscripciones()){
            System.out.println(inscripcion.getMateria());
            System.out.println(inscripcion.getAlumno());
            System.out.println(inscripcion.getNota());
        }
         */
        
        
        for (Materia obtenerMateriasNoCursada : id.obtenerMateriasCursadas(3)) {
            System.out.println(obtenerMateriasNoCursada);
        }
        

   }
}

    

