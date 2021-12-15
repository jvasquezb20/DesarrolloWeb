
package modelo;

public class Cliente {
    private String idCliente;
    private String idUsuario;
    private String Nombre;
    private String Apellidos;
    private String Correo;
    private String Debe;
    private char genero;
    private String Contraseña;

    public Cliente(String idCliente, String idUsuario, String Nombre, String Apellidos, String Correo, String Debe, char genero) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Correo = Correo;
        this.Debe = Debe;
        this.genero = genero;
    }

    
    public Cliente (){
        
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }
    

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getDebe() {
        return Debe;
    }

    public void setDebe(String Debe) {
        this.Debe = Debe;
    }

    
    
    
}
    
