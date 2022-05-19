/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package g4s21jfmop14;

import ConexionDAO.Conexion;
import DTO.Dato;


/**
 *
 * @author panch
 */
public class G4s21JFMOP14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion conexion = new Conexion();
        conexion.abrir();
        
        /*Dato dato = new Dato("Luis", 20, "sin correo", 4);
        if(conexion.actualizar(dato)){
            System.out.println("Se actualizo");
        }else{
            System.out.println("No se actualizo");
        }
        */
        
        /*Dato dato = new Dato("Olga", 35, "olgaoliva@gmail.com");
        if(conexion.insertar(dato)){
            System.out.println("Se inserto");
        }else{
            System.out.println("No se inserto");
        }
        */
        
        /*if(conexion.consultar()){
            System.out.println("Se consultaron");
            //System.out.println(conexion.getListaDatos());
        }else{
            System.out.println("No se pudo consultar");
        }
        */
        
        if(conexion.borrar(3)){
            System.out.println("Se elimino");
            //System.out.println(conexion.getListaDatos());
        }else{
            System.out.println("No se pudo eliminarr");
        }
        
        conexion.cerrar();
    }
    
}
