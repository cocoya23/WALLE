package gob.mx.conocer.walle.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Funcion")
public class Funcion implements Cloneable, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncion;
    
    @Column(name = "titulo")
    private String titulo;
    
    @ManyToOne
    @JoinColumn(name = "idTipoFuncion")
    private TipoFuncion tipoFuncion;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "idFuncionPadre")
    private List<Funcion> hijos;
    
    @ManyToOne
    @JoinColumn(name = "idFuncionPadre")
    private Funcion funcionPadre;
    
    public Funcion clone() throws CloneNotSupportedException{
        
        Funcion nodo = null;
        
        nodo = (Funcion)super.clone();
        
        return nodo;
        
    }

    public List<Funcion> getHijos() {
        if(hijos==null)hijos = new ArrayList<Funcion>();
        return hijos;
    }

    public void setHijos(List<Funcion> hijos) {
        this.hijos = hijos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }    

    public Integer getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Integer idFuncion) {
        this.idFuncion = idFuncion;
    }

    public TipoFuncion getTipoFuncion() {
        return tipoFuncion;
    }

    public void setTipoFuncion(TipoFuncion tipoFuncion) {
        this.tipoFuncion = tipoFuncion;
    }

    public Funcion getFuncionPadre() {
        return funcionPadre;
    }

    public void setFuncionPadre(Funcion funcionPadre) {
        this.funcionPadre = funcionPadre;
    }
}
