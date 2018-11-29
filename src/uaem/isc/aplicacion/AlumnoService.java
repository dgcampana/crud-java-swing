package uaem.isc.aplicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import uaem.isc.dto.Alumno;

/**
 * @author Ricardo Sanchez Paz
 */
public class AlumnoService {
    
    private static String tablaAlumno = "alumno";
    
    /**
     * Obtiene todos los alumnos de la BD
     * @param conexion
     * @return List
     */
    public static void obtenerTodos(JTable tabla){
        Connection conexion = null;
        String query = "SELECT *"
                     + "FROM " + AlumnoService.tablaAlumno;
        String titulos[] = {"Matricula","Nombre","Apellidos","Semestre"};
        try{
           conexion = Conexion.obtener();
           PreparedStatement consulta = conexion.prepareStatement(query);
           ResultSet resultado = consulta.executeQuery();
           
           ArrayList<ArrayList<String>> datosList = new ArrayList<>();
           while(resultado.next()){
              ArrayList dato = new ArrayList();
              Long id = resultado.getLong("alumnoId");           
              
              dato.add(id.toString());
              dato.add(resultado.getString("alumnoNombre"));
              dato.add(resultado.getString("alumnoApellidos"));
              dato.add(resultado.getString("alumnoSemestre"));
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
    
    /**
     * Metodo para registrar un alumno
     * @param alumno
     * @return Mensaje de confirmación
     */
    public static String crear(Alumno a){
        String mensaje = "";
        String insert = "INSERT INTO alumno (alumnoId, alumnoNombre, alumnoApellidos,alumnoSemestre ) VALUES(?, ?, ?, ?)";
        
        try{
           Connection conexion = null;
           PreparedStatement consulta;
           conexion = Conexion.obtener();
           consulta = conexion.prepareStatement(insert);
           consulta.setString(1, null);
           consulta.setString(2, a.getNombre());
           consulta.setString(3, a.getApellidos());
           consulta.setString(4, a.getSemestre());
           
           consulta.executeUpdate();
           mensaje = "Alumno creado con exito.";
        
        }catch(Exception ex){
           ex.printStackTrace();
           mensaje = "Error al crear el alumno.";
        }
        return mensaje;
    } 
    
    /**
     * Metodo para editar un alumno
     * @return Mensaje de estatus
     */
    public static String editar(Alumno a){
        String mensaje = "";
        String update = "UPDATE alumno "
                      + "SET alumnoNombre = ?, alumnoApellidos = ?, alumnoSemestre = ? "
                      + "WHERE alumnoId = ?";
        try{
           Connection conexion = null;
           PreparedStatement consulta;
           conexion = Conexion.obtener();
           consulta = conexion.prepareStatement(update);
           
           consulta.setString(1, a.getNombre());
           consulta.setString(2, a.getApellidos());
           consulta.setString(3, a.getSemestre());
           consulta.setLong  (4, a.getId());
           
           consulta.executeUpdate();
           mensaje = "Alumno editado con exito.";
        
        }catch(Exception ex){
           ex.printStackTrace();
           mensaje = "Error al crear el alumno.";
        }
        return mensaje;
    }
    
    /**
     * Motodo para eliminar un usuario
     * @param id
     * @return 
     */
    public static String eliminar(Long id){
        String mensaje = "";
        String delete = "DELETE FROM alumno "
                      + "WHERE alumnoId = ?";

        try{
           Connection conexion = null;
           PreparedStatement consulta;
           conexion = Conexion.obtener();
           consulta = conexion.prepareStatement(delete);
           consulta.setLong  (1, id);
           consulta.executeUpdate();
           mensaje = "Alumno Eliminado con exito.";
        
        }catch(SQLIntegrityConstraintViolationException ex){
           ex.printStackTrace();
           mensaje = "Error al eliminar, El alumno se encuentra asignado a uno o mas cursos.";
        }catch(SQLException esql){
           esql.printStackTrace();
           mensaje = "Error al eliminar al alumno.";
        }              
        return mensaje;
    }
    
}
