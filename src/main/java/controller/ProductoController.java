package controller;

//import gestion.ProductoGestion;
import gestion.ClienteGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import modelo.Producto;
import java.util.List;
import gestion.ProductoGestion;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



@Named(value = "productoController")
@SessionScoped
public class ProductoController extends Producto implements Serializable{
    
    public ProductoController(){
    
}
    

    public List<Producto> getProducto() {

        return ProductoGestion.getProducto();

    }
    
    public String edita(String id) {

        Producto producto = ProductoGestion.getProducto(id);

        if (producto != null) {
            this.setIdProduct(producto.getIdProduct());
            this.setNombreProducto(producto.getNombreProducto());
            this.setPrecio(producto.getPrecio());
            this.setStock(producto.getStock());
            return "ProductoEdita.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm", mensaje);
            return "ProductoEdita.xhtml";
        }
    }
    public String elimina() {

        if (ProductoGestion.eliminar(this)) {
            return "ListadoProductoAdmin.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:identificacion", mensaje);
            return "ProductoEdita.xhtml";
        }
    }
    public String modifica() {

        if (ProductoGestion.actualiza(this)) {
            return "ListadoProductoAdmin.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:identificacion", mensaje);
            return "ProductoEdita.xhtml";
        }
    }
    public String enviarInsert(){
        
        return "CrearProducto.xhtml";
    }
    public String inserta() {

        if (ProductoGestion.insertar(this)) {
            return "ListadoProductoAdmin.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("crearProductoForm:identificacion", mensaje);
            return "CrearProducto.xhtml";
        }
    }
    
}
