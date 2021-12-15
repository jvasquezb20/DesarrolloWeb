
package gestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.Cliente;
import gestion.UsuarioGestion;

public class ClienteGestion {
    
    private static final String SQL_INSERT_Cliente = "INSERT INTO cliente (idcliente,idUsuario,Nombre,Apellidos,Correo,Debe,Genero) VALUES (?,?,?,?,?,?,?);";
    
     public static boolean insertar (Cliente cliente){
        
        try{
            
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_INSERT_Cliente);
            sentencia.setString(1, cliente.getIdCliente());
            sentencia.setString(2, cliente.getIdUsuario());
            sentencia.setString(3, cliente.getNombre());
            sentencia.setString(4, cliente.getApellidos());
            sentencia.setString(5, cliente.getCorreo());
            sentencia.setString(6, cliente.getDebe());
            sentencia.setString(7, ""+cliente.getGenero());
            
            return sentencia.executeUpdate()>0; // Esta validación devuelve true si el registro se pudo insertar en bd
            
        }catch (SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;
       
    }
     
    private static final String SQL_SELECT_CLIENTE = "select * from cliente where idCliente=?";
    
    public static Cliente getCliente(String id){
        
        Cliente cliente = null;
        
        try{
            
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_CLIENTE);
            consulta.setString(1, id);
            ResultSet datos = consulta.executeQuery();
            if (datos.next()){
                cliente= new Cliente(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getString(5),
                        datos.getString(6),
                        datos.getString(7).charAt(0)
                );
            }
            
        }catch (SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        return cliente;
        
    }
    private static final String SQL_SELECT_CLIENTES= "Select * from cliente";
    
    public static ArrayList<Cliente> getCliente(){
        
        ArrayList<Cliente> lista= new ArrayList<>();
        
        try{
            
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_CLIENTES);
            ResultSet rs = consulta.executeQuery();
            while (rs!=null && rs.next()){
                lista.add(new Cliente (rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7).charAt(0)));
            }
            
        }catch (SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return lista;
    }
    
      
    private static final String SQL_DELETE_CLIENTE= "delete from cliente where idCliente=?";
    private static final String SQL_DELETE_USUARIO= "delete from usuario where idUsuario=?";
    
    public static boolean eliminar (Cliente cliente){
        
        try{
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_DELETE_CLIENTE);
            consulta.setString(1, cliente.getIdCliente());
            
            PreparedStatement consulta2 = Conexion.getConexion().prepareStatement(SQL_DELETE_USUARIO);
            consulta2.setString(1, cliente.getIdUsuario());
            
            consulta.executeUpdate();
            
            return consulta2.executeLargeUpdate()>0; 
            
        }catch (Exception ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null, ex);
            
        }
        return false;
    }
    
    private static final String SQL_UPDATE_CLIENTE = "update cliente set Nombre=?,Apellidos=?,"
            + "Correo=?,debe=?,Genero=? where idCliente=?;";
    
    public static boolean actualiza (Cliente cliente){
        
        try{
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_CLIENTE);
            sentencia.setString(1, cliente.getNombre());
            sentencia.setString(2, cliente.getApellidos());
            sentencia.setString(3, cliente.getCorreo());
            sentencia.setString(4, cliente.getDebe());
            sentencia.setString(5,""+cliente.getGenero());
            sentencia.setString(6, cliente.getIdCliente() );
            
            return sentencia.executeUpdate()>0; // Si trae uno o más de uno, entonces se convierte en un true
            
        }catch(SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return false;
                
    }
    private static final String SQL_UPDATE_DEBO = "update cliente set debe=? where idCliente=?;";
    
    public static boolean actualizaDebo (String debo, String id){
        
        try{
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_DEBO);
            sentencia.setString(1, debo);
            sentencia.setString(2, id);
            
            return sentencia.executeUpdate()>0; 
            
        }catch(SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return false;
                
    }
    
}
