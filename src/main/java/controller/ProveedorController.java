package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import modelo.Proveedor;
import gestion.ProveedorGestion;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "proveedorController")
@SessionScoped
public class ProveedorController extends Proveedor implements Serializable {

    public ProveedorController() {
    }

    public String inserta() {

        if (ProveedorGestion.insertar(this)) {

            return "confirmacion.xhtml";

        } else {

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible Cédula Duplicada");
            FacesContext.getCurrentInstance().addMessage("registroProspectoForm:cedula", mensaje);
            return "registro.xhtml";

        }

    }
    private boolean noImprimir = true;
    
    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscaProveedor(String id) {

        Proveedor proveedor = ProveedorGestion.getProveedor(id);
        
        

        if (proveedor != null) {
            
            this.setIdUsuario(proveedor.getIdUsuario());
            this.setNombreProveedor(proveedor.getNombreProveedor());
            this.setFechaInicio(proveedor.getFechaInicio());
            this.setFechaFin(proveedor.getFechaFin());
            this.setPedido(proveedor.getPedido());
            
            this.noImprimir = false;
        } else {

            this.setIdUsuario("");
            this.setNombreProveedor("");
            this.setFechaInicio(null);
            this.setFechaFin(null);
            this.setPedido("");

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Encontrado",
                    "Porveedor no Encontrado");
            FacesContext.getCurrentInstance().addMessage("reporteUniForm:identificacion", mensaje);
            this.noImprimir = true;
        }
    }
    
    public List<Proveedor> getProveedores() {

        return ProveedorGestion.getProveedores();

    }
    
    public String edita(String id) {

        Proveedor proveedor = ProveedorGestion.getProveedor(id);

        if (proveedor != null) {
            this.setNombreProveedor(proveedor.getNombreProveedor());
            this.setFechaInicio(proveedor.getFechaInicio());
            this.setFechaFin(proveedor.getFechaFin());
            this.setPedido(proveedor.getPedido());
            this.setIdUsuario(proveedor.getIdUsuario());
            this.setIdProveedor(proveedor.getIdProveedor());
            return "editaProveedor.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("listadoProveedorForm", mensaje);
            return "ListadoProveedor.xhtml";
        }
    }
    public String elimina() {

        if (ProveedorGestion.eliminar(this)) {
            return "ListadoProveedor.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "editaProveedor.xhtml";
        }
    }
    public String modifica() {

        if (ProveedorGestion.actualiza(this)) {
            return "ListadoProveedor.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "editaProveedor.xhtml";
        }
    }
    public String enviarInsert(){
        
        return "registro.xhtml";
    }


}
