package gob.mx.conocer.walle.dao.impl;

import gob.mx.conocer.walle.dao.FuncionDao;
import gob.mx.conocer.walle.entity.Funcion;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class FuncionDaoImpl extends AbstractDaoImpl<Funcion, Integer> implements FuncionDao{
    
    protected FuncionDaoImpl(){
        super(Funcion.class);
    }
    
    @Override
    public Funcion getById(Integer id){
        return findById(id);
    }
    
    @Override
    public void saveFuncion(Funcion funcion){
        getCurrentSession().beginTransaction();
        saveOrUpdate(funcion);
        getCurrentSession().getTransaction().commit();
    }
    
    @Override
    public void updateFuncion(Funcion funcion){
        getCurrentSession().beginTransaction();
        saveOrUpdate(funcion);
        getCurrentSession().getTransaction().commit();
    }

    @Override
    public void deleteFuncion(Funcion funcion) {
        getCurrentSession().beginTransaction();
        delete(funcion);
        getCurrentSession().getTransaction().commit();
    }
    
    @Override
    public List<Funcion> getChilds(Funcion funcion){
        return findByCriteria(Restrictions.eq("funcionPadre.idFuncion", funcion.getIdFuncion()));
    }

}
