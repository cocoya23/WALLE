package gob.mx.conocer.walle.controllers;

import java.util.concurrent.Future;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notifications")
public class NotificationsController {
    
    @RequestMapping(value = "/notify")
    public void notify(Model model, HttpServletResponse response){
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        Future<FacesMessage> push = pushContext.push("/notification", new FacesMessage(FacesMessage.SEVERITY_INFO, "Integración", "Se aca de enviar el archivo con las correcciones."));
        
        System.out.println("done:"+push.isDone());
        
        response.setStatus(200);
    }
    
}
