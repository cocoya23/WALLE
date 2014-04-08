/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.mx.conocer.walle.managed;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name = "usuario")
@SessionScoped
public class UsuariosBean implements Serializable{
    
    private String username;
    private String authority;

    public String getAuthority() {
        
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        
        for (GrantedAuthority grantedAuthority : authorities) {
            authority = grantedAuthority.getAuthority();
        }
        return authority;
    }

    public String getUsername() {
        
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        return username;
    }
    
    public void kickUser(){
        
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        
    }
    
}
