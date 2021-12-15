
package modelo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    private static Conexion conexion;
    private static final String DBURL="jdbc:mysql://localhost:3306/tiendabd?serverTimezone=UTC";
    private static Connection conn=null;
    
    private Conexion(){
        
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn= DriverManager.getConnection(DBURL,"ugeneral_user","Prueba123_");
        }catch (ClassNotFoundException | SQLException | NoSuchMethodException | SecurityException |
                InstantiationException | IllegalAccessException | IllegalArgumentException |
                InvocationTargetException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    
    // Sincronizado significa que no va a haber más de un proceso ejecutando esta instrucción
    public static synchronized Connection getConexion(){
        
        if (conexion==null){
            conexion=new Conexion();
        }
        return conn;
    }
    
}
