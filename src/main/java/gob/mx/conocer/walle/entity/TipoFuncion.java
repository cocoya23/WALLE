package gob.mx.conocer.walle.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "TipoFuncion")
public class TipoFuncion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoFuncion;
    
    @Column
    private String tipoFuncion;
    
    @OneToMany(mappedBy = "tipoFuncion")
    private List<Funcion> funciones;

    public Integer getIdTipoFuncion() {
        return idTipoFuncion;
    }

    public void setIdTipoFuncion(Integer idTipoFuncion) {
        this.idTipoFuncion = idTipoFuncion;
    }

    public String getTipoFuncion() {
        return tipoFuncion;
    }

    public void setTipoFuncion(String tipoFuncion) {
        this.tipoFuncion = tipoFuncion;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.idTipoFuncion != null ? this.idTipoFuncion.hashCode() : 0);
        hash = 59 * hash + (this.tipoFuncion != null ? this.tipoFuncion.hashCode() : 0);
        hash = 59 * hash + (this.funciones != null ? this.funciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoFuncion other = (TipoFuncion) obj;
        if (this.idTipoFuncion != other.idTipoFuncion && (this.idTipoFuncion == null || !this.idTipoFuncion.equals(other.idTipoFuncion))) {
            return false;
        }
        if ((this.tipoFuncion == null) ? (other.tipoFuncion != null) : !this.tipoFuncion.equals(other.tipoFuncion)) {
            return false;
        }
        if (this.funciones != other.funciones && (this.funciones == null || !this.funciones.equals(other.funciones))) {
            return false;
        }
        return true;
    }

    
    
}
