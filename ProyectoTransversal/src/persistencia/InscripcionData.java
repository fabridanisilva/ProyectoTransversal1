
package persistencia;

import Modelo.Alumno;
import Modelo.Conexion;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class InscripcionData {
    private Connection con = null;
    private MateriaData md = new MateriaData(); 
    private AlumnoData ad = new AlumnoData();
    public InscripcionData() {
        this.con = Conexion.getConexion();
        
    }
    
    public void guardarInscripcion(Inscripcion inscripcion){
    
        
        
        try {
            String sql = "INSERT INTO `inscripto`( nota, idAlumno, idMateria) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setDouble(1, inscripcion.getNota());
            ps.setInt(2, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(3, inscripcion.getMateria().getIdMateria());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                inscripcion.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion exitosa!!");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        
    }
    
    public void actualizarNota(int idAlumno,int idMateria,double nota){
        String sql = "UPDATE inscripto SET nota=? WHERE idAlumno=? AND idMateria=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            
            int exito = ps.executeUpdate();
            
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Se modifico correctamente pa!!!");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno,int idMateria){
    
        String sql = "DELETE FROM inscripto WHERE idAlumno=? AND idMateria=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
            int filas = ps.executeUpdate();
            if (filas>0) {
                JOptionPane.showMessageDialog(null, "Inscripcion borrada con exito!!! ");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    
    }
    
    public ArrayList<Inscripcion> obtenerInscripciones(){
    
        ArrayList<Inscripcion> cursadas = new ArrayList<>();
        
        String sql = "SELECT * FROM inscripto";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            
            
            while (rs.next()) {                
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                insc.setNota(rs.getInt("nota"));
                
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
                
                insc.setAlumno(alu);
                
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                
                insc.setMateria(mat);
                
                cursadas.add(insc);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        return cursadas;
    }
    public ArrayList<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno){
    
        ArrayList<Inscripcion> cursadas = new ArrayList<>();
        
        String sql = "SELECT * FROM inscripto WHERE idAlumno = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, idAlumno);
            
            ResultSet rs = ps.executeQuery();
            
            
            while (rs.next()) {                
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                insc.setNota(rs.getInt("nota"));
                
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
                
                insc.setAlumno(alu);
                
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                
                insc.setMateria(mat);
                
                cursadas.add(insc);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        return cursadas;
    }
    
    public ArrayList<Materia> obtenerMateriasCursadas(int idAlumno){
    
        ArrayList<Materia> materias = new ArrayList<>();
        
        String sql = "SELECT inscripto.idMateria,nombre,año FROM inscripto,"
                + " materia WHERE inscripto.idMateria = materia.idMateria "
                + " AND inscripto.idAlumno = ?";
        
        
        
        try {
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            
            ps.setInt(1, idAlumno);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                Materia materia = new Materia();
                
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                
                
                materias.add(materia);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        
        return materias;
    
    }
    
        public ArrayList<Materia> obtenerMateriasNoCursadas(int idAlumno){
    
        ArrayList<Materia> materias = new ArrayList<>();
        
        String sql = "SELECT * FROM materia WHERE estado = 1 AND idMateria NOT IN "
                + "(SELECT idMateria FROM inscripto WHERE idAlumno=?)";
        
        
        
        try {
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            
            ps.setInt(1, idAlumno);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                Materia materia = new Materia();
                
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materia.setActivo(rs.getBoolean("estado"));
                
                materias.add(materia);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        
        return materias;
    
    }
    
}
