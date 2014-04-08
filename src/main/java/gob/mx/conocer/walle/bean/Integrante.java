package gob.mx.conocer.walle.bean;

import java.io.Serializable;

public class Integrante implements Serializable{
    
    private Integer idIntegrante;
    private String nombres;
    private String paterno;
    private String materno;
    private String cargoComite;
    private String cargoOrganizacion;

    public Integer getIdIntegrante() {
        return idIntegrante;
    }

    public void setIdIntegrante(Integer idIntegrante) {
        this.idIntegrante = idIntegrante;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getCargoComite() {
        return cargoComite;
    }

    public void setCargoComite(String cargoComite) {
        this.cargoComite = cargoComite;
    }

    public String getCargoOrganizacion() {
        return cargoOrganizacion;
    }

    public void setCargoOrganizacion(String cargoOrganizacion) {
        this.cargoOrganizacion = cargoOrganizacion;
    }
    
}
