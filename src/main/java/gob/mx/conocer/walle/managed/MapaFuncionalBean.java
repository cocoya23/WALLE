package gob.mx.conocer.walle.managed;

import gob.mx.conocer.walle.entity.Funcion;
import gob.mx.conocer.walle.entity.TipoFuncion;
import gob.mx.conocer.walle.service.MapaFuncionalService;
import gob.mx.conocer.walle.service.util.Messages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "mapa")
@ViewScoped
public class MapaFuncionalBean implements Serializable{
    
    
    public static final String NODO_PROPOSITO_PRINCIPAL = "Propósito Principal";
    public static final String NODO_FUNCION_CLAVE = "Función Clave";
    public static final String NODO_FUNCION_INTERMEDIA = "Función Intermedia";
    public static final String NODO_FUNCION_INDIVIDUAL = "Función Individual";
    public static final String NODO_FUNCION_ELEMENTAL = "Función Elemental";
    
    @ManagedProperty(name = "mapaFuncionalService", value = "#{mapaFuncionalService}")
    private MapaFuncionalService mapaFuncionalService;
    
    private List<TipoFuncion> choosableTiposFuncion;
    private TipoFuncion tipoFuncion;
    private String titulo;
    private String tituloDialogo;
    private String mensaje;
    private boolean create;
    
    private TreeNode root;
    private TreeNode selectedNode;
    private TreeNode clipboardNode;
    
    private boolean disabledAddAction;
    private boolean disabledAsECAction;
    
    public MapaFuncionalBean(){
        disabledAsECAction = true;
    }
    
    public void prepareAdd(){
        create = true;
        tituloDialogo = "Agregar a ";
        if(selectedNode!=null){
            
            if(selectedNode.getType().equals(NODO_FUNCION_ELEMENTAL)){
                Messages.sendNotification(FacesMessage.SEVERITY_INFO,"Info","No se pueden agregar mas elemento a una "+NODO_FUNCION_ELEMENTAL);
                return;
            }
            
            List<TipoFuncion> tiposFuncion = mapaFuncionalService.getTiposFuncion();
            
            choosableTiposFuncion = new ArrayList<TipoFuncion>();
            
            for (TipoFuncion tipoFuncion : tiposFuncion) {
                if(selectedNode.getType().equals(NODO_PROPOSITO_PRINCIPAL)){
                    if(tipoFuncion.getTipoFuncion().equals(NODO_FUNCION_CLAVE))
                        choosableTiposFuncion.add(tipoFuncion);
                }else if(selectedNode.getType().equals(NODO_FUNCION_CLAVE)){
                    if(tipoFuncion.getTipoFuncion().equals(NODO_FUNCION_INTERMEDIA))
                        choosableTiposFuncion.add(tipoFuncion);
                    if(tipoFuncion.getTipoFuncion().equals(NODO_FUNCION_INDIVIDUAL))
                        choosableTiposFuncion.add(tipoFuncion);                    
                }else if(selectedNode.getType().equals(NODO_FUNCION_INTERMEDIA)){
                    if(tipoFuncion.getTipoFuncion().equals(NODO_FUNCION_INTERMEDIA))
                        choosableTiposFuncion.add(tipoFuncion);
                    if(tipoFuncion.getTipoFuncion().equals(NODO_FUNCION_INDIVIDUAL))
                        choosableTiposFuncion.add(tipoFuncion);
                }else if(selectedNode.getType().equals(NODO_FUNCION_INDIVIDUAL)){
                    if(tipoFuncion.getTipoFuncion().equals(NODO_FUNCION_ELEMENTAL))
                        choosableTiposFuncion.add(tipoFuncion);
                }
            }
            
        } else {
            
            Messages.sendNotification(FacesMessage.SEVERITY_INFO,"Info","Debe seleccionar un funcion primero");
            return;
            
        }
        
        
    }
    
    public void add(){
        
        if(selectedNode!=null){
            try{
                Funcion funcion = new Funcion();
                Funcion funcionPadre = (Funcion) selectedNode.getData();            
                funcion.setTitulo(getTitulo());
                funcion.setTipoFuncion(getTipoFuncion());
                funcion.setFuncionPadre(funcionPadre);
                funcionPadre.getHijos().add(funcion);
                mapaFuncionalService.addFuncion(funcion);
                new DefaultTreeNode(funcion.getTipoFuncion().getTipoFuncion(),funcion, selectedNode);
                selectedNode.setExpanded(true);
                selectedNode.setSelected(true);
                updateRoot();
            } catch (Exception ex) {
                ex.printStackTrace();
                Messages.sendMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error inesperado");
            }
        }
        
        create = false;
        tituloDialogo = "Modificar ";
        
    }
    
    public void remove(){
        
        if(selectedNode!=null){
            if(!selectedNode.getType().equals(NODO_PROPOSITO_PRINCIPAL)){
                Funcion funcion = (Funcion)selectedNode.getData();
                try {
                    mapaFuncionalService.deleteFuncion(funcion);
                    Funcion funcionPadre = (Funcion)selectedNode.getParent().getData();
                    funcionPadre.getHijos().remove(funcion);
                    selectedNode.getParent().getChildren().remove(selectedNode);
                    updateRoot();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Messages.sendMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error al eliminar el elemento");
                }
            }else{
                Messages.sendNotification(FacesMessage.SEVERITY_INFO, "Información", "No se puede eliminar el "+NODO_PROPOSITO_PRINCIPAL);
            }
            
        }
        
    }
    
    public void prepareRename(){
        
        create = false;
        tituloDialogo = "Modificar ";
        
        if(selectedNode!=null){
            titulo = ((Funcion)selectedNode.getData()).getTitulo();
        }
        
    }
    
    public void rename(){
        
        if(selectedNode!=null){
            Funcion funcion = (Funcion)selectedNode.getData();
            funcion.setTitulo(getTitulo());
            mapaFuncionalService.updateFuncion(funcion);
        }
        
    }
    
    public void copy(){
                
        if(selectedNode!=null){
            if(!selectedNode.getType().equals(NODO_PROPOSITO_PRINCIPAL)){
                clipboardNode = selectedNode;
                updateRoot();
                Messages.sendNotification(clipboardNode.getType() + " copiada");
            }else{
                Messages.sendNotification(FacesMessage.SEVERITY_INFO, "Información", "No se puede copiar el "+NODO_PROPOSITO_PRINCIPAL);
            }
        }
        
    }
    
    public void paste(){
        
        if(clipboardNode!=null && selectedNode!=null){
            try {                
                if(clipboardNode.getType().equals(NODO_FUNCION_CLAVE) && !selectedNode.getType().equals(NODO_PROPOSITO_PRINCIPAL)){
                    Messages.sendNotification(FacesMessage.SEVERITY_WARN, "Aviso", "Solo puedes agregar una "+NODO_FUNCION_CLAVE+" a el "+NODO_PROPOSITO_PRINCIPAL);
                    return;
                }
                if(clipboardNode.getType().equals(NODO_FUNCION_INTERMEDIA)){
                    if(selectedNode.getType().equals(NODO_PROPOSITO_PRINCIPAL) || selectedNode.getType().equals(NODO_FUNCION_ELEMENTAL) || selectedNode.getType().equals(NODO_FUNCION_INDIVIDUAL)){
                        Messages.sendNotification(FacesMessage.SEVERITY_WARN, "Aviso", "Solo puedes agregar una "+NODO_FUNCION_INTERMEDIA+" a una "+NODO_FUNCION_CLAVE+" o a otra"+NODO_FUNCION_INTERMEDIA);
                        return;
                    }
                }
                if(clipboardNode.getType().equals(NODO_FUNCION_INDIVIDUAL)){
                    if(selectedNode.getType().equals(NODO_PROPOSITO_PRINCIPAL) || selectedNode.getType().equals(NODO_FUNCION_ELEMENTAL) || selectedNode.getType().equals(NODO_FUNCION_INDIVIDUAL)){
                        Messages.sendNotification(FacesMessage.SEVERITY_WARN, "Aviso", "Solo puedes agregar una "+NODO_FUNCION_INDIVIDUAL+" a una "+NODO_FUNCION_CLAVE+" o a una "+NODO_FUNCION_INTERMEDIA);
                        return;
                    }
                }
                if(clipboardNode.getType().equals(NODO_FUNCION_ELEMENTAL) && !selectedNode.getType().equals(NODO_FUNCION_INDIVIDUAL)){
                    Messages.sendNotification(FacesMessage.SEVERITY_WARN, "Aviso", "Solo puedes agregar una "+NODO_FUNCION_ELEMENTAL+" a una "+NODO_FUNCION_INTERMEDIA);
                    return;
                }
                
                copiarHerencia((Funcion)clipboardNode.getData(),selectedNode);                
                updateRoot();
            } catch (Exception ex) {
                ex.printStackTrace();
                Messages.sendMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error inesperado al pegar el funcion");
            }
        }
        
    }
    
    public void copiarHerencia(Funcion funcion,TreeNode funcionPadreTree){
        try{
            Funcion funcionTmp = new Funcion();
            Funcion funcionPadre = (Funcion) funcionPadreTree.getData();            
            funcionTmp.setTitulo(funcion.getTitulo());
            funcionTmp.setTipoFuncion(funcion.getTipoFuncion());
            funcionTmp.setFuncionPadre(funcionPadre);
            funcionPadre.getHijos().add(funcionTmp);
            mapaFuncionalService.addFuncion(funcionTmp);
            DefaultTreeNode treeNode = new DefaultTreeNode(funcionTmp.getTipoFuncion().getTipoFuncion(),funcionTmp, funcionPadreTree);
            treeNode.setExpanded(true);
            for(Funcion hijo:funcion.getHijos()){
                copiarHerencia(hijo, treeNode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Messages.sendMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error inesperado");
        }
    
    }
    
    public void onNodeSelect(NodeSelectEvent nodeSelectEvent){
        TreeNode node = nodeSelectEvent.getTreeNode();
        if(node.getType().equals(NODO_FUNCION_ELEMENTAL)){
            setDisabledAddAction(true);
        } else {
            setDisabledAddAction(false);
        }
        if(node.getType().equals(NODO_FUNCION_INDIVIDUAL)){
            setDisabledAsECAction(false);
        } else {
            setDisabledAsECAction(true);
        }
    }
    
    public void export(){  
    }
    
    public void solicitarRevision(){
        Messages.sendNotification(FacesMessage.SEVERITY_INFO, "Info", "Revisión solicitada correctamente");
    }
    
    public Converter getTipoFuncionConverter(){
        return new Converter() {

            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                return mapaFuncionalService.findTipoFuncionById(Integer.parseInt(value));
            }

            @Override
            public String getAsString(FacesContext context, UIComponent component, Object value) {
                return ((TipoFuncion)value).getIdTipoFuncion().toString();
            }
        };
    }
    
    public MapaFuncionalService getMapaFuncionalService() {
        return mapaFuncionalService;
    }

    public void setMapaFuncionalService(MapaFuncionalService mapaFuncionalService) {
        this.mapaFuncionalService = mapaFuncionalService;
    }
    
    public void updateRoot(){
        Funcion funcion = mapaFuncionalService.getMapaFuncional(1);
        root = new DefaultTreeNode(funcion.getTipoFuncion().getTipoFuncion(),funcion, null);
        root.setExpanded(true);
        createChilds(funcion.getHijos(),root);
    }
    
    public TreeNode getRoot() {
        if(root==null){
            Funcion funcion = mapaFuncionalService.getMapaFuncional(1);
            root = new DefaultTreeNode(funcion.getTipoFuncion().getTipoFuncion(),funcion, null);
            root.setExpanded(true);
            createChilds(funcion.getHijos(),root);
        }
        
        return root;
    }
    
    private void createChilds(List<Funcion> hijos, TreeNode treeNodeParent) {
        for (Funcion funcion : hijos) {
            DefaultTreeNode treeNode = new DefaultTreeNode(funcion.getTipoFuncion().getTipoFuncion(),funcion, treeNodeParent);
            treeNode.setExpanded(true);
            createChilds(funcion.getHijos(), treeNode);
        }
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public List<TipoFuncion> getChoosableTiposFuncion() {
        return choosableTiposFuncion;
    }

    public void setChoosableTiposFuncion(List<TipoFuncion> choosableTiposFuncion) {
        this.choosableTiposFuncion = choosableTiposFuncion;
    }

    public TipoFuncion getTipoFuncion() {
        return tipoFuncion;
    }

    public void setTipoFuncion(TipoFuncion tipoFuncion) {
        this.tipoFuncion = tipoFuncion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public TreeNode getClipboardNode() {
        return clipboardNode;
    }

    public String getTituloDialogo() {
        return tituloDialogo;
    }

    public void setTituloDialogo(String tituloDialogo) {
        this.tituloDialogo = tituloDialogo;
    }

    public String getNODO_PROPOSITO_PRINCIPAL() {
        return NODO_PROPOSITO_PRINCIPAL;
    }

    public String getNODO_FUNCION_CLAVE() {
        return NODO_FUNCION_CLAVE;
    }

    public String getNODO_FUNCION_INTERMEDIA() {
        return NODO_FUNCION_INTERMEDIA;
    }

    public String getNODO_FUNCION_INDIVIDUAL() {
        return NODO_FUNCION_INDIVIDUAL;
    }

    public String getNODO_FUNCION_ELEMENTAL() {
        return NODO_FUNCION_ELEMENTAL;
    }

    public boolean isDisabledAddAction() {
        return disabledAddAction;
    }

    public void setDisabledAddAction(boolean disabledAddAction) {
        this.disabledAddAction = disabledAddAction;
    }

    public boolean isDisabledAsECAction() {
        return disabledAsECAction;
    }

    public void setDisabledAsECAction(boolean disabledAsECAction) {
        this.disabledAsECAction = disabledAsECAction;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
