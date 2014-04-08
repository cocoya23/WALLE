/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.mx.conocer.walle.managed;


import gob.mx.conocer.walle.bean.Organizacion;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "directorio")
@SessionScoped
public class DirectorioBean implements Serializable{
    
    private boolean showEsSindicato,showTieneSindicato,showEsAsociacion,showPerteneceCA,showDomicilioIntegrante,showBajas;
    
    private List<Organizacion> organizaciones;
    private Organizacion selectedOrganizacion;
    
    public void createOrganizacion(){
        
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public Organizacion getSelectedOrganizacion() {
        return selectedOrganizacion;
    }

    public void setSelectedOrganizacion(Organizacion selectedOrganizacion) {
        this.selectedOrganizacion = selectedOrganizacion;
    }
    
    public void fakeCall(){
        
    }

    public boolean isShowEsSindicato() {
        return showEsSindicato;
    }

    public void setShowEsSindicato(boolean showEsSindicato) {
        this.showEsSindicato = showEsSindicato;
    }

    public boolean isShowTieneSindicato() {
        return showTieneSindicato;
    }

    public void setShowTieneSindicato(boolean showTieneSindicato) {
        this.showTieneSindicato = showTieneSindicato;
    }

    public boolean isShowEsAsociacion() {
        return showEsAsociacion;
    }

    public void setShowEsAsociacion(boolean showEsAsociacion) {
        this.showEsAsociacion = showEsAsociacion;
    }

    public boolean isShowPerteneceCA() {
        return showPerteneceCA;
    }

    public void setShowPerteneceCA(boolean showPerteneceCO) {
        this.showPerteneceCA = showPerteneceCO;
    }

    public boolean isShowDomicilioIntegrante() {
        return showDomicilioIntegrante;
    }

    public void setShowDomicilioIntegrante(boolean showDomicilioIntegrante) {
        this.showDomicilioIntegrante = showDomicilioIntegrante;
    }

    public boolean isShowBajas() {
        return showBajas;
    }

    public void setShowBajas(boolean showBajas) {
        this.showBajas = showBajas;
    }
    
}
