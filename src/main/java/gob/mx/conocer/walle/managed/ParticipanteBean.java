/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.mx.conocer.walle.managed;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "participante")
@SessionScoped
public class ParticipanteBean implements Serializable {
    
    private boolean showEsAutor;

    
    public boolean isShowEsAutor() {
        return showEsAutor;
    }

    
    public void setShowEsAutor(boolean showEsAutor) {
        this.showEsAutor = showEsAutor;
    }
    
}
