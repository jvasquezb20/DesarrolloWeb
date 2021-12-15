
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Proveedor {
    
    private String idProveedor;
    private String NombreProveedor;
    private String idUsuario;
    private Date fechaInicio;
    private Date fechaFin;
    private String pedido;
    private String rol;
    private String contraseña;

    public Proveedor() {
    }

    public Proveedor(String idProveedor, String NombreProveedor,String idUsuario, Date fechaInicio, Date fechaFin, String pedido, String rol, String contraseña) {
        this.idProveedor = idProveedor;
        this.NombreProveedor = NombreProveedor;
        this.idUsuario = idUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pedido = pedido;
        this.rol = rol;
        this.contraseña = contraseña;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return NombreProveedor;
    }

    public void setNombreProveedor(String NombreProveedor) {
        this.NombreProveedor = NombreProveedor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    
    @Override
    public String toString() {
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fecha1= format.format(this.fechaInicio);
        String fecha2= format.format(this.fechaFin);
        
        
        return "\"Prospecto\":{\n"
                + "\"Nombre de empresa\":\"" + this.NombreProveedor + "\",\n"
                + "\"Fecha de inicio de entrega\":\"" + fecha1 + "\",\n"
                + "\"Ultimo dia de entrega\":\"" + fecha2 + "\",\n"
                + "\"Pedido\":\"" + this.pedido + "\",\n"
                + "\"Cedula juridica\":\"" + this.idProveedor + "\",\n"
                + "\"Usuario de ingreso\":\"" + this.idUsuario + "\",\n"
                + "\"Contrseña de ingreso\":\"" + this.contraseña + "\",\n"
                + "\"Rol\":\"" + this.rol + "\",\n"
                ;
                
     }
    
    
    
    
    
}
