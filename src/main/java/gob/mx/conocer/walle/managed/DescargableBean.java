/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.mx.conocer.walle.managed;

import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "descargable")
@SessionScoped
public class DescargableBean implements Serializable{
    
    private StreamedContent archivo;
    
    public StreamedContent getArchivo() {
        InputStream is = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/recursos/archivos/prueba.pdf");
        archivo = new DefaultStreamedContent(is, "application/pdf", "demo.pdf");
        return archivo;
    }

    public void setArchivo(StreamedContent archivo) {
        this.archivo = archivo;
    }
    
}
