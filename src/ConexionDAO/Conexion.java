/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDAO;

import DTO.Dato;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author panch
 */
public class Conexion {
    
    Connection conexion;
    List<Dato> ListaDato = new ArrayList<Dato>();
    
    public void abrir(){
        String user = "root";
        String password = "12345";
        String url = "jdbc:mysql://localhost:3306/g4s21?useUnicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void cerrar(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean insertar(Dato dato){
        boolean estado = true;
        
        try{
            abrir();
            PreparedStatement ps = conexion.prepareStatement("Insert into datos(nombre, edad, correo) values(?, ?, ?)");
            ps.setString(1, dato.getNombre());
            ps.setInt(2, dato.getEdad());
            ps.setString(3, dato.getCorreo());
            ps.execute();
            
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        
        return estado;
    }
    
    public boolean actualizar(Dato dato){
        boolean estado = true;
        
        try{
            abrir();
            PreparedStatement ps = conexion.prepareCall("update datos set nombre = ?, edad = ?, correo = ? where id = ?");
            
            ps.setString(1, dato.getNombre());
            ps.setInt(2, dato.getEdad());
            ps.setString(3, dato.getCorreo());
            ps.setInt(4, dato.getId());
            
            ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        
        return estado;
    }
    
    public boolean consultar(){
        boolean estado = true;
        
        try{
            abrir();
            PreparedStatement ps = conexion.prepareStatement("select * from datos");
            ResultSet rs = ps.executeQuery();
            Dato datos;
            
            while(rs.next()){
                datos = new Dato(rs.getString("nombre"),rs.getInt("edad"), rs.getString("correo"), rs.getInt("id"));
                //datos.setNombre(rs.getString("nombre"));
                ListaDato.add(datos);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        
        return estado;
    }
    
    public List<Dato> getListaDatos(){
        
        return ListaDato;
    }
    
    public boolean borrar(int id){
        boolean estado = true;
        try{
            abrir();
            PreparedStatement ps = conexion.prepareStatement("delete from datos where id = ?");
            ps.setInt(1, id);
            ps.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        return estado;
    }
    
}
