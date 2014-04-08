/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.mx.conocer.walle.bean;

import java.io.Serializable;
import java.util.List;

public class Organizacion implements Serializable{
    
    private Integer idOrganizacion;
    private String razonSocial;
    private String paginaWeb;
    private String tipoOrganizacion;
    private String sector;
    private String subsector;
    private Integer noEmpleados;
    private List<Integrante> integrantes;

    public Integer getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(Integer idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getTipoOrganizacion() {
        return tipoOrganizacion;
    }

    public void setTipoOrganizacion(String tipoOrganizacion) {
        this.tipoOrganizacion = tipoOrganizacion;
    }

    public String getSubsector() {
        return subsector;
    }

    public void setSubsector(String subsector) {
        this.subsector = subsector;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Integer getNoEmpleados() {
        return noEmpleados;
    }

    public void setNoEmpleados(Integer noEmpleados) {
        this.noEmpleados = noEmpleados;
    }

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }
    
}
