package gob.mx.conocer.walle.dao.impl;

import gob.mx.conocer.walle.dao.TipoFuncionDao;
import gob.mx.conocer.walle.entity.TipoFuncion;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TipoFuncionDaoImpl extends AbstractDaoImpl<TipoFuncion, Integer> implements TipoFuncionDao{
    
    protected TipoFuncionDaoImpl(){
        super(TipoFuncion.class);
    }

    @Override
    public List<TipoFuncion> getTiposFuncion() {
        
        return getAll();
        
    }
    
    @Override
    public TipoFuncion findById(Integer id) {
        
        return super.findById(id);
        
    }   

    @Override
    public TipoFuncion findByName(String name) {
        return findByCriteria(Restrictions.eq("tipoFuncion", name)).get(0);
    }
    

}
