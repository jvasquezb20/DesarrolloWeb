package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import gestion.ClienteGestion;
import java.util.List;
import modelo.Cliente;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "clienteController")
@SessionScoped
public class ClienteController extends Cliente implements Serializable {

    private String Contraseña;

    public ClienteController() {
    }

    public String inserta() {

        if (ClienteGestion.insertar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "crear.xhtml";
        }
    }

    public String elimina() {

        if (ClienteGestion.eliminar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "edita.xhtml";
        }
    }

    public String edita(String id) {

        Cliente cliente = ClienteGestion.getCliente(id);

        if (cliente != null) {
            this.setIdCliente(cliente.getIdCliente());
            this.setIdUsuario(cliente.getIdUsuario());
            this.setNombre(cliente.getNombre());
            this.setApellidos(cliente.getApellidos());
            this.setCorreo(cliente.getCorreo());
            this.setDebe(cliente.getDebe());
            this.setGenero(cliente.getGenero());
            return "edita.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("listForm", mensaje);
            return "edita.xhtml";
        }
    }

    public List<Cliente> getCliente() {

        return ClienteGestion.getCliente();

    }

    public String modifica() {

        if (ClienteGestion.actualiza(this)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "edita.xhtml";
        }
    }

    public String modificaDebo(String id, String debo) {

        if (ClienteGestion.actualizaDebo(id, debo)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "DeboUsuarioVer.xhtml";
        }
    }
    private boolean editar = true;

    public boolean isNoEditar() {
        return editar;
    }

    public void setNoEditar(boolean editar) {
        this.editar = editar;
    }

    public void buscaCliente(String id, String Usuario, String Urol) {

        Cliente cliente = ClienteGestion.getCliente(id);

        if (cliente != null) {

            if (Usuario.equals(cliente.getIdUsuario()) || Urol.equals("admin")) {

                this.setIdCliente(cliente.getIdCliente());
                this.setIdUsuario(cliente.getIdUsuario());
                this.setNombre(cliente.getNombre());
                this.setApellidos(cliente.getApellidos());
                this.setCorreo(cliente.getCorreo());
                this.setDebe(cliente.getDebe());
                this.setGenero(cliente.getGenero());

                if (Urol.equals("admin")) {
                    this.editar = false;
                }
            }
        } else {

            this.setIdCliente("");
            this.setNombre("");
            this.setApellidos("");
            this.setDebe("");

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Encontrado",
                    "Cliente no Encontrado");
            FacesContext.getCurrentInstance().addMessage("DeboUserVerForm:cedula", mensaje);
            this.editar = true;
        }
    }
    public String enviarInsert(){
        
        return "login.xhtml";
    }

}
