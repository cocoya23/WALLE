package gob.mx.conocer.walle.dao;

import gob.mx.conocer.walle.entity.TipoFuncion;
import java.util.List;

public interface TipoFuncionDao {
    
    List<TipoFuncion> getTiposFuncion();
    TipoFuncion findById(Integer id);
    TipoFuncion findByName(String name);

}
