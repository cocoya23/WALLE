package gob.mx.conocer.walle.service.impl;

import gob.mx.conocer.walle.service.MapaFuncionalService;
import gob.mx.conocer.walle.dao.FuncionDao;
import gob.mx.conocer.walle.dao.TipoFuncionDao;
import gob.mx.conocer.walle.entity.Funcion;
import gob.mx.conocer.walle.entity.TipoFuncion;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mapaFuncionalService")
public class MapaFuncionalServiceImpl implements MapaFuncionalService, Serializable {
    
    @Autowired
    private FuncionDao funcionDao;
    
    @Autowired
    private TipoFuncionDao tipoFuncionDao;
    
    @Override
    public Funcion getMapaFuncional(Integer idRaiz){        
        return funcionDao.getById(idRaiz);
    }

    @Override
    public List<Funcion> getHijos(Funcion funcion) {
        return funcionDao.getChilds(funcion);
    }
    
    @Override
    public List<TipoFuncion> getTiposFuncion() {        
        return tipoFuncionDao.getTiposFuncion();
    }
    
    @Override
    public TipoFuncion findTipoFuncionById(Integer id) {
        return tipoFuncionDao.findById(id);
    }
    
    @Override
    public TipoFuncion findTipoFuncionByName(String name) {
        return tipoFuncionDao.findByName(name);
    }

    @Override
    public void addFuncion(Funcion funcion) {
        funcionDao.saveFuncion(funcion);
    }

    @Override
    public void updateFuncion(Funcion funcion) {
        funcionDao.updateFuncion(funcion);
    }

    @Override
    public void deleteFuncion(Funcion funcion) {
        deleteHerencia(funcion);
        
    }
    
    public void deleteHerencia(Funcion padre){
        for(Funcion hijo:getHijos(padre)){
            deleteHerencia(hijo);
        }
        funcionDao.deleteFuncion(padre);
    }
    
    public FuncionDao getFuncionDao() {
        return funcionDao;
    }

    public void setFuncionDao(FuncionDao funcionDao) {
        this.funcionDao = funcionDao;
    }

    public TipoFuncionDao getTipoFuncionDao() {
        return tipoFuncionDao;
    }

    public void setTipoFuncionDao(TipoFuncionDao tipoFuncionDao) {
        this.tipoFuncionDao = tipoFuncionDao;
    }

    

}
