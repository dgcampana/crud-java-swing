package uaem.isc.aplicacion;

import java.sql.*;
/**
 * Clase de la conexion a MySQL
 * @author Ricardo Sánchez 
 */
public class Conexion {
    
   private static Connection cnx = null;
   private static final String driver = "com.mysql.cj.jdbc.Driver";
   private static final String url = "jdbc:mysql://localhost/mydb";
   private static final String options = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   private static final String password = "root";
   private static final String usuario = "root";
   
   /**
    * Se establece la conección con la base de datos 
    * @return
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   public static Connection obtener(){
        if (cnx == null) {
            try {
               Class.forName(driver).newInstance();
               cnx = DriverManager.getConnection(url + options, usuario, password);
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }
        return cnx;
   }
   
   /**
    * Se cierra la conección con la base de datos
    */
   public static void cerrar() {
        try{
            if (cnx != null) {
                cnx.close();
            } 
        } catch(SQLException e ){
            e.printStackTrace();
        } 
   }
}
