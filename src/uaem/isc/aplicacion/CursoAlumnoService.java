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
public class CursoAlumnoService {
 
    /**
     * Obtiene todos los alumnos de la BD
     * @param conexion
     * @return List
     */
    public static void obtenerTodos(JTable tabla){
        Connection conexion = null;
        String query = "SELECT alumno.alumnoNombre, alumno.alumnoApellidos, "
                     + "alumno.alumnoSemestre ,curso.cursoNombre "
                     + "FROM  alumno " 
                     + "INNER JOIN  curso_alumno ON alumno.alumnoId = curso_alumno.alumno_alumnoId "
                     + "INNER JOIN  curso ON curso_alumno.curso_cursoId = curso.cursoId ";
        
        
        String titulos[] = {"Numero Alumno","Apellidos Alumno","Semestre", "Curso"};
        try{
           conexion = Conexion.obtener();
           PreparedStatement consulta = conexion.prepareStatement(query);
           ResultSet resultado = consulta.executeQuery();
           
           ArrayList<ArrayList<String>> datosList = new ArrayList<>();
           while(resultado.next()){
              ArrayList dato = new ArrayList();
              dato.add(resultado.getString("alumnoNombre"));
              dato.add(resultado.getString("alumnoApellidos"));
              dato.add(resultado.getString("alumnoSemestre"));
              dato.add(resultado.getString("cursoNombre"));
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
