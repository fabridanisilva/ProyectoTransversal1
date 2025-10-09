/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Modelo.Conexion;
import Modelo.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fabri
 */
public class MateriaData {
    private Connection con = null;

    public MateriaData() {
        con = Conexion.getConexion();
    }
    
    
    public void agregarMateria(Materia materia){
        try {
            String sql = "INSERT INTO materia(nombre, año, estado) VALUES (?,?,?)";
        
        
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isActivo());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se agrego exitosamente!!");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ ex);
        }
    }
    
    public void actializarMateria(Materia materia){
    
        
        
        try {
            
            String sql = "UPDATE materia SET nombre=?,año=?,estado=? WHERE idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isActivo());
            ps.setInt(4, materia.getIdMateria());
            
            int exito = ps.executeUpdate();
            
            if (exito==1) {
                JOptionPane.showMessageDialog(null, "Se actualizo correctamente!!");
            }
            
            
    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    }
    
    public void eliminarMateria(int id){
    
           String sql = "UPDATE materia SET estado=0 WHERE idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, id);
            
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Se dio de baja exitosamente!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error: "+ ex);
        }
           
    }
    
    public Materia buscarMateria(int id){
    
        String sql = "SELECT idMateria,nombre, año FROM materia WHERE estado=1 AND idMateria=?";
        
        Materia materia = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                materia = new Materia();
                
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setAnioMateria(rs.getInt("año"));
                materia.setNombre(rs.getString("nombre"));
                materia.setActivo(true);
            }else{
            JOptionPane.showMessageDialog(null, "No existe el alumno");}
            ps.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        return materia;
    }
    
    public ArrayList<Materia> mostrarMaterias(){
        
        String sql = "SELECT idMateria,nombre,año FROM `materia` WHERE estado=1";
        ArrayList<Materia> materias = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
                    
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materia.setActivo(true);
                
                materias.add(materia);
            }
            ps.close();
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        
        
        
        
        return materias;
    }
    
    
}
