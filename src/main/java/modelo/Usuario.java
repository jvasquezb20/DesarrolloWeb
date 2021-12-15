
package modelo;


public class Usuario {
    
    private String idUsuario;
    private String pwUsuario;
    private String nombreUsuario;
    private String idRol;
    private String Npass;
    private String Cpass;
    
    public Usuario (String idUsuario, String nombreUsuario, String idRol){
        
        this.idUsuario= idUsuario;
        this.nombreUsuario= nombreUsuario;
        this.idRol= idRol;
        
    }

    //Propiedades GET and SET
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPwUsuario() {
        return pwUsuario;
    }

    public void setPwUsuario(String pwUsuario) {
        this.pwUsuario = pwUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getNpass() {
        return Npass;
    }

    public void setNpass(String Npass) {
        this.Npass = Npass;
    }

    public String getCpass() {
        return Cpass;
    }

    public void setCpass(String Cpass) {
        this.Cpass = Cpass;
    }
    
    
    
}
