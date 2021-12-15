
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Proveedor;


public class ProveedorGestion {
    
    public static final String SQL_INSERT_PROVEEDOR = 
            "insert into proveedor (idProveedor,NombreProveedor,idUsuario,"
            + "fechaInicio,fechaFin,pedido) values "
            + "(?,?,?,?,?,?);";
    private static final String SQL_INSERT_Usuario = "INSERT INTO usuario (idUsuario,pwUsuario,nombreUsuario,idRol) VALUES (?,MD5(?),?,?);";
    
    public static boolean insertar (Proveedor proveedor){
        
        try{
            
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_PROVEEDOR);
            
            sentencia.setString(1, proveedor.getIdProveedor());
            sentencia.setString(2, proveedor.getNombreProveedor());
            sentencia.setString(3, proveedor.getIdUsuario());
            sentencia.setObject(4, proveedor.getFechaInicio());
            sentencia.setObject(5, proveedor.getFechaFin());
            sentencia.setString(6, proveedor.getPedido());
            
            PreparedStatement sentencia2 = Conexion.getConexion().prepareStatement(SQL_INSERT_Usuario);
            sentencia2.setString(1, proveedor.getIdUsuario());
            sentencia2.setString(2, proveedor.getContraseña());
            sentencia2.setString(3, proveedor.getNombreProveedor());
            sentencia2.setString(4, proveedor.getRol());
            
            sentencia2.executeUpdate();
            
            int fila = sentencia.executeUpdate();
            
            return fila >0;
            
        }catch (SQLException ex){
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE,null, ex);
            
        }
        
        return false;
        
    }
    
    private static final String SQL_SELECT_PROVEEDOR = "select * from proveedor where idProveedor=?";
    
    public static Proveedor getProveedor(String id){
        
        Proveedor proveedor = null;
        
        try{
            
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_PROVEEDOR);
            consulta.setString(1, id);
            ResultSet datos = consulta.executeQuery();
            if (datos.next()){
                proveedor= new Proveedor(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getDate(4),
                        datos.getDate(5),
                        datos.getString(6),
                        datos.getString(1),
                        datos.getString(1)
                );
            }
            
        }catch (SQLException ex){
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        return proveedor;
        
    }
    
    private static final String SQL_SELECT_PROVEEDORES= "Select * from proveedor";
    public static ArrayList<Proveedor> getProveedores(){
        
        ArrayList<Proveedor> lista= new ArrayList<>();
        
        try{
            
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_PROVEEDORES);
            ResultSet rs = consulta.executeQuery();
            while (rs!=null && rs.next()){
                lista.add(new Proveedor (rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(3),
                        rs.getString(3)));
            }
            
        }catch (SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return lista;
    }
    private static final String SQL_DELETE_PROVEEDOR= "delete from proveedor where idProveedor=?";
    private static final String SQL_DELETE_USUARIO= "delete from usuario where idUsuario=?";
    
    public static boolean eliminar (Proveedor proveedor){
        
        try{
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_DELETE_PROVEEDOR);
            consulta.setString(1, proveedor.getIdProveedor());
            
            PreparedStatement consulta2 = Conexion.getConexion().prepareStatement(SQL_DELETE_USUARIO);
            consulta2.setString(1, proveedor.getIdUsuario());
            
            consulta.executeUpdate();
            
            return consulta2.executeLargeUpdate()>0; 
            
        }catch (Exception ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null, ex);
            
        }
        return false;
    }
    private static final String SQL_UPDATE_PROVEEDOR = "update proveedor set NombreProveedor=?,fechaInicio=?,"
            + "fechaFin=?,pedido=? where idProveedor=?;";
    
    public static boolean actualiza (Proveedor proveedor){
        
        try{
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_PROVEEDOR);
            sentencia.setString(1, proveedor.getNombreProveedor());
            sentencia.setObject(2, proveedor.getFechaInicio());
            sentencia.setObject(3, proveedor.getFechaFin());
            sentencia.setString(4, proveedor.getPedido());
            sentencia.setString(5, proveedor.getIdProveedor());
            
            
            return sentencia.executeUpdate()>0; 
            
        }catch(SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return false;
                
    }
    
    
}
