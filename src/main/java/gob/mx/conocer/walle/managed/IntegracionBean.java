/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.mx.conocer.walle.managed;

import gob.mx.conocer.walle.entity.Documento;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "integracion")
@ViewScoped
public class IntegracionBean implements Serializable{
    
    List<Documento> documentos;
    Documento selectedDocumento;
    StreamedContent archivo;
    String tipo;
    boolean integracion;
    boolean restructuracion;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    
    public IntegracionBean(){
        documentos = new ArrayList<Documento>();
                
    }
    
    public void handlerSolicitud(FileUploadEvent event){
        int v = 0;
        for (Documento documento: getDocumentos()){
            if(documento.getFormato().equals("Solicitud de integración")) v = documento.getVersion();
        }
        Documento documento = new Documento();
        documento.setNombre(event.getFile().getFileName());
        documento.setFechaCarga(sdf.format(Calendar.getInstance().getTime()));
        documento.setVersion(++v);
        documento.setFormato("Solicitud de integración");
        documento.setEstado("Enviado al Asesor");
        documento.setObservaciones("");
        documento.setArchivo(event.getFile().getContents());
        getDocumentos().add(documento);
    }
    
    public void handlerPresentacion(FileUploadEvent event){
        int v = 0;
        for (Documento documento: getDocumentos()){
            if(documento.getFormato().equals("Presentación de integración")) v = documento.getVersion();
        }
        Documento documento = new Documento();
        documento.setNombre(event.getFile().getFileName());
        documento.setFechaCarga(sdf.format(Calendar.getInstance().getTime()));
        documento.setVersion(++v);
        documento.setFormato("Presentación de integración");
        documento.setEstado("Enviado al Asesor");
        documento.setObservaciones("");
        documento.setArchivo(event.getFile().getContents());
        getDocumentos().add(documento);
    }
    
    public void handlerPlanMetas(FileUploadEvent event){
        int v = 0;
        for (Documento documento: getDocumentos()){
            if(documento.getFormato().equals("Plan y Metas")) v = documento.getVersion();
        }
        Documento documento = new Documento();
        documento.setNombre(event.getFile().getFileName());
        documento.setFechaCarga(sdf.format(Calendar.getInstance().getTime()));
        documento.setVersion(++v);
        documento.setFormato("Plan y Metas");
        documento.setEstado("Enviado al Asesor");
        documento.setObservaciones("");
        documento.setArchivo(event.getFile().getContents());
        getDocumentos().add(documento);
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public Documento getSelectedDocumento() {
        return selectedDocumento;
    }

    public void setSelectedDocumento(Documento selectedDocumento) {
        this.selectedDocumento = selectedDocumento;
    }

    public StreamedContent getArchivo() {
        InputStream is = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/recursos/archivos/prueba.pdf");
        archivo = new DefaultStreamedContent(is, "application/octet-stream", selectedDocumento.getNombre());
        return archivo;
    }

    public void setArchivo(StreamedContent archivo) {
        this.archivo = archivo;
    }
    
    public boolean isIntegracion() {
        return integracion;
    }

    public void setIntegracion(boolean integracion) {
        this.integracion = integracion;
    }

    public boolean isRestructuracion() {
        return restructuracion;
    }

    public void setRestructuracion(boolean restructuracion) {
        this.restructuracion = restructuracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if(tipo.equals("Integracion")){
            setIntegracion(true);
        }else if(tipo.equals("Restructuracion")){
            setRestructuracion(true);
        }
        this.tipo = tipo;
    }   
    
}
