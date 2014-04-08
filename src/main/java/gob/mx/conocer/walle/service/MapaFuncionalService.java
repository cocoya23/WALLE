package gob.mx.conocer.walle.service;

import gob.mx.conocer.walle.entity.Funcion;
import gob.mx.conocer.walle.entity.TipoFuncion;
import java.util.List;

public interface MapaFuncionalService {

    Funcion getMapaFuncional(Integer idRaiz);
    List<Funcion> getHijos(Funcion funcion);
    List<TipoFuncion> getTiposFuncion();
    TipoFuncion findTipoFuncionById(Integer id);
    TipoFuncion findTipoFuncionByName(String name);
    void addFuncion(Funcion funcion);
    void updateFuncion(Funcion funcion);
    void deleteFuncion(Funcion funcion);
    
}
