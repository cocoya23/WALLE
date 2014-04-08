package gob.mx.conocer.walle.managed;

import gob.mx.conocer.walle.model.Notificacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

public class NotificacionesBean implements Serializable{

    private DashboardModel notificacionesModel;
    private List<Notificacion> notificaciones;

    public DashboardModel getNotificacionesModel() {

        notificacionesModel = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        
        List<Notificacion> notificaciones = getNotificaciones();
        
        for (Notificacion notificacion : notificaciones) {
            column1.addWidget("panel"+notificacion.getIdNotificacion());
        }

        notificacionesModel.addColumn(column1);
        
        return notificacionesModel;
    }

    public List<Notificacion> getNotificaciones() {
        notificaciones = new ArrayList<Notificacion>();
        
        /*Notificacion notificacion = new Notificacion();
        notificacion.setIdNotificacion(1);
        notificacion.setTitulo("Titulo UNO");
        notificacion.setMensaje("Mensaje mensaje mensaje mensaje mensaje mensaje mensaje mensaje");
        notificacion.setLiga("#");
        notificaciones.add(notificacion);
        notificacion = new Notificacion();
        notificacion.setIdNotificacion(2);
        notificacion.setTitulo("Titulo DOS");
        notificacion.setMensaje("Mensaje mensaje mensaje mensaje mensaje mensaje mensaje mensaje");
        notificacion.setLiga("#");
        notificaciones.add(notificacion);
        notificacion = new Notificacion();
        notificacion.setIdNotificacion(3);
        notificacion.setTitulo("Titulo TRES");
        notificacion.setMensaje("Mensaje mensaje mensaje mensaje mensaje mensaje mensaje mensaje");
        notificacion.setLiga("#");
        notificaciones.add(notificacion);*/
        
        return notificaciones;
    }
    
    public void searchNotifications(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        List<Notificacion> notificaciones = getNotificaciones();
        
        for (Notificacion notificacion : notificaciones) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, notificacion.getTitulo(), notificacion.getMensaje()+"<br/><a href='"+notificacion.getLiga()+"'>Ir</a>"));
        }        
        
    }
    
}
