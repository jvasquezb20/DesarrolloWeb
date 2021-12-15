package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Producto;

public class ProductoGestion {
    
    
    private static final String SQL_INSERT_PRODUCTO = "INSERT INTO producto (idProducto,NombreProducto,Precio,Stock) VALUES (?,?,?,?);";
    public static boolean insertar (Producto producto){
        
        try{
            
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_INSERT_PRODUCTO);
            sentencia.setString(1, producto.getIdProduct());
            sentencia.setString(2, producto.getNombreProducto());
            sentencia.setString(3, producto.getPrecio());
            sentencia.setString(4, producto.getStock());
            
            return sentencia.executeUpdate()>0; 
            
        }catch (SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;
       
    }

    private static final String SQL_SELECT_PRODUCTOS= "Select * from producto";
    public static ArrayList<Producto> getProducto(){
        
        ArrayList<Producto> lista= new ArrayList<>();
        
        try{
            
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_PRODUCTOS);
            ResultSet rs = consulta.executeQuery();
            while (rs!=null && rs.next()){
                lista.add(new Producto (rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4))
                  );
            }
            
        }catch (SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return lista;
    }
   
    private static final String SQL_SELECT_PRODUCTO = "select * from producto where idProducto=?";
    public static Producto getProducto(String id){
        
        Producto producto = null;
        
        try{
            
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_PRODUCTO);
            consulta.setString(1, id);
            ResultSet datos = consulta.executeQuery();
            if (datos.next()){
                producto = new Producto(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4)        
                );
            }
            
        }catch (SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return producto;
        
    }
    
    private static final String SQL_DELETE_PRODUCTO= "delete from producto where idProducto=?";
    public static boolean eliminar (Producto producto){
        
        try{
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_DELETE_PRODUCTO);
            consulta.setString(1, producto.getIdProduct());
            
            return consulta.executeUpdate()>0; 
            
        }catch (Exception ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null, ex);
            
        }
        return false;
    }
    
    private static final String SQL_UPDATE_PRODUCTO = "update producto set NombreProducto=?,Precio=?,Stock=? where idProducto=?;";
    public static boolean actualiza (Producto producto){
        
        try{
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_PRODUCTO);
            sentencia.setString(1, producto.getNombreProducto());
            sentencia.setString(2, producto.getPrecio());
            sentencia.setString(3, producto.getStock());
            sentencia.setString(4, producto.getIdProduct());
            
            return sentencia.executeUpdate()>0; 
            
        }catch(SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return false;
                
    }
}
