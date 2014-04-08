package gob.mx.conocer.walle.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;


public interface AbstractDao<E, I extends Serializable> extends Serializable{
    
    public E findById(I id);
    public void saveOrUpdate(E e);
    public void delete(E e);
    public List findByCriteria(Criterion criterion);
    public List getAll();
    
}
