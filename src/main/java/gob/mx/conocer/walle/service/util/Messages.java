package gob.mx.conocer.walle.service.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {
    
    public static void sendMessage(String asunto){
        FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(asunto));
    }
    
    public static void sendMessage(String asunto,String detalle){
        FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(asunto, detalle));
    }
    
    public static void sendMessage(FacesMessage.Severity severity,String asunto,String detalle){
        FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(severity, asunto, detalle));
    }
    
    public static void sendNotification(String asunto){
        FacesContext.getCurrentInstance().addMessage("notification", new FacesMessage(asunto));
    }
    
    public static void sendNotification(String asunto,String detalle){
        FacesContext.getCurrentInstance().addMessage("notification", new FacesMessage(asunto, detalle));
    }
    
    public static void sendNotification(FacesMessage.Severity severity,String asunto,String detalle){
        FacesContext.getCurrentInstance().addMessage("notification", new FacesMessage(severity, asunto, detalle));
    }

}
