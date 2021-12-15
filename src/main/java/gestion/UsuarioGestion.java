
package gestion;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Usuario;


public class UsuarioGestion {
    
    private static final String SQL_VALIDA="select nombreUsuario,idRol from usuario where idUsuario=? and pwUsuario=MD5(?)";
    
    public static Usuario Valida (String idUsuario, String password){
        
        Usuario usuario=null;
        
        try{
            
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_VALIDA);
            sentencia.setString(1, idUsuario);
            sentencia.setString(2, password);
            ResultSet rs= sentencia.executeQuery();
            
            if (rs.next()){
                usuario = new Usuario(idUsuario,rs.getString(1),rs.getString(2));
            }
            
        }catch (SQLException ex){
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE,null,ex);
            
        }

        return usuario;
    }
    
    private static final String SQL_INSERT_Usuario = "INSERT INTO usuario (idUsuario,pwUsuario,nombreUsuario,idRol) VALUES (?,MD5(?),?,?);";
    
     public static boolean insertar (Usuario usuario){
      
    
        try{
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_INSERT_Usuario);
            sentencia.setString(1, usuario.getIdUsuario());
            sentencia.setString(2, usuario.getPwUsuario());
            sentencia.setString(3, usuario.getNombreUsuario());
            sentencia.setString(4, usuario.getIdRol());
             return sentencia.executeUpdate()>0; // Esta validación devuelve true si el registro se pudo insertar en bd
            
        }catch (SQLException ex){
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;
       
    }
    private static final String SQL_UPDATE_CONTRA = "update usuario set pwUsuario=MD5(?) where pwUsuario=MD5(?);";
    
    public static boolean actualizaContra (Usuario usuario){
        
        try{
            if(usuario.getNpass().equals(usuario.getCpass())){
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_CONTRA);
            sentencia.setString(1, usuario.getNpass());
            sentencia.setString(2, usuario.getPwUsuario());
            return sentencia.executeUpdate()>0; 
            
            }}catch(SQLException ex){
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return false;
                
    }
   
     
}
