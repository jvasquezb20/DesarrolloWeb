/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import modelo.Conexion;
import modelo.Proveedor;

/**
 *
 * @author KennethArroyoVargas
 */
@Named(value = "reporteController")
@SessionScoped
public class ReporteController implements Serializable {

    
    public ReporteController() {
    }
    public void verPdfProveedor(){
        
        try{           
            File jasper = new File (FacesContext.getCurrentInstance()
            .getExternalContext().getRealPath("/proveedor/Proveedorpd.jasper"));
            
            JasperPrint reporteJasper= JasperFillManager.fillReport(jasper.getPath(),null, Conexion.getConexion());
            HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            ServletOutputStream flujo= respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper,flujo);
            FacesContext.getCurrentInstance().responseComplete();
            
        }catch (JRException ex){
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE,null,ex);
        }catch (IOException ex){
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE,null,ex);
        }

    }
     public void descargarPdfProveedor(){
        
        try{           
            File jasper = new File (FacesContext.getCurrentInstance()
            .getExternalContext().getRealPath("/proveedor/Proveedorpd.jasper"));
            
            JasperPrint reporteJasper= JasperFillManager.fillReport(jasper.getPath(),null, Conexion.getConexion());
            HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            respuesta.addHeader("Content-disposition", "attachement; filename=reporte.pdf");
            ServletOutputStream flujo= respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper,flujo);
            FacesContext.getCurrentInstance().responseComplete();
            
        }catch (JRException ex){
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE,null,ex);
        }catch (IOException ex){
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE,null,ex);
        }

    }
    public void certificaNotas(Proveedor proveedor){
        
        Map<String,Object>  parametros = new HashMap<>();
        parametros.put("id", proveedor.getIdProveedor());
        
         
        try{           
            File jasper = new File (FacesContext.getCurrentInstance()
            .getExternalContext().getRealPath("/proveedor/ProveedorUni.jasper"));
            
            JasperPrint reporteJasper= JasperFillManager.fillReport(jasper.getPath(),parametros, Conexion.getConexion());
            HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            ServletOutputStream flujo= respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper,flujo);
            FacesContext.getCurrentInstance().responseComplete();
            
        }catch (JRException ex){
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE,null,ex);
        }catch (IOException ex){
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE,null,ex);
        }

    }
}
