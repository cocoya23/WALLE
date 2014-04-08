/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.mx.conocer.walle.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quique
 */
public class Nodo implements Cloneable, Serializable{
    
    private Integer idNodo;
    private String titulo;
    private String tipo;
    private List<Nodo> hijos;
    
    public Nodo(){
        hijos=new ArrayList<Nodo>();
    }
    
    public Nodo clone() throws CloneNotSupportedException{
        
        Nodo nodo = null;
        
        nodo = (Nodo)super.clone();
        
        return nodo;
        
    }

    public Integer getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(Integer idNodo) {
        this.idNodo = idNodo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Nodo> hijos) {
        this.hijos = hijos;
    }
    
    
}
