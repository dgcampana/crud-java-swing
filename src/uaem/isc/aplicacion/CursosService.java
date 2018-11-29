/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uaem.isc.aplicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego Gómez
 */
public class CursosService {
    
     private static String tablaCurso = "curso";
    
    /**
     * Obtiene todos los alumnos de la BD
     * @param conexion
     * @return List
     */
    public static void obtenerTodos(JTable tabla){
        Connection conexion = null;
        String query = "SELECT *"
                     + "FROM " + CursosService.tablaCurso;
        String titulos[] = {"Numero Curso","Nombre","Descripcion"};
        try{
           conexion = Conexion.obtener();
           PreparedStatement consulta = conexion.prepareStatement(query);
           ResultSet resultado = consulta.executeQuery();
           
           ArrayList<ArrayList<String>> datosList = new ArrayList<>();
           while(resultado.next()){
              ArrayList dato = new ArrayList();
              Long id = resultado.getLong("cursoId");           
              
              dato.add(id.toString());
              dato.add(resultado.getString("cursoNombre"));
              dato.add(resultado.getString("cursoDescripcion"));
              datosList.add(dato);
           }
           
           String[][] datos = new String[datosList.size()][];
            for (int i = 0; i < datosList.size(); i++) {
                ArrayList<String> row = datosList.get(i);
                datos[i] = row.toArray(new String[row.size()]);
            }
            
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.setDataVector(datos, titulos);

        }catch(Exception ex){
           ex.printStackTrace();
        }
    }
}
