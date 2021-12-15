package modelo;

public class Producto {
  private String idProduct;
  private String nombreProducto;
  private String precio;
  private String stock;

    public Producto(String idProduct, String nombreProducto, String precio, String stock) {
        this.idProduct = idProduct;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
    }
  
    public Producto() {
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    
    
   
  
  
  
  
  
    
}
