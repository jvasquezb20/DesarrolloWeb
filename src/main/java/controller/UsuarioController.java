package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import gestion.UsuarioGestion;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Usuario;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

    

    public UsuarioController() {
        super("", "", "");

    }

    public String valida() {

        Usuario usuario = UsuarioGestion.Valida(this.getIdUsuario(), this.getPwUsuario());

        if (usuario != null) {
            this.setIdUsuario(usuario.getIdUsuario());
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setIdRol(usuario.getIdRol());
            String pagina = "";

            if (usuario.getIdRol().equals("admin")) {
                pagina = "principal.xhtml";
            }
            if (usuario.getIdRol().equals("cliente")) {
                pagina = "principalUsuario.xhtml";
            }
            if (usuario.getIdRol().equals("proveedor")) {
                pagina = "principalProveedor.xhtml";
            }
            return pagina;
        } else {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o "
                    + "Contraseña inválidas");
            FacesContext.getCurrentInstance().addMessage("loginForm:clave", msg);
            return "index.xhtml";

        }

    }

    public String inserta() {

        if (UsuarioGestion.insertar(this)) {
            return "crear.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:identificacion", mensaje);
            return "login.xhtml";
        }
    }

    private String plantilla;

    public String Plantilla(String PRol) {

        if (PRol.equals("admin")) {
            plantilla = "/resources/plantillas/plantilla.xhtml";
        }
        if (PRol.equals("cliente")) {

            plantilla = "/resources/plantillas/plantillaUsuario.xhtml";
        }
        if (PRol.equals("proveedor")) {

            plantilla = "/resources/plantillas/plantillaProveedor.xhtml";
        }
        return plantilla;

    }

    public String modifica(){

        
        if (UsuarioGestion.actualizaContra(this)) {
                
            return "index.xhtml";
            
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Las contraseñas no coinciden");
            FacesContext.getCurrentInstance().addMessage("CambioContrasenaForm:newPass", mensaje);
            return "CambioContraseña.xhtml";
        }
    }

    

}
