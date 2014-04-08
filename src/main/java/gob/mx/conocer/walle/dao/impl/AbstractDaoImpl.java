package gob.mx.conocer.walle.dao.impl;

import gob.mx.conocer.walle.dao.AbstractDao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E, I> {
 
    private Class<E> entityClass;
    private static Session session;
 
    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public Session getCurrentSession() {
        try {
            if(session==null) {
                session = sessionFactory.openSession();
                System.out.println("porque es nulo");
            } else if(!session.isConnected()) {
                session = sessionFactory.openSession();
                System.out.println("porque no esta conectado");
            } else if(!session.isOpen()) {
                session = sessionFactory.openSession();
                System.out.println("porque no esta abierta");
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return session;
    }
 
    @Override
    public E findById(I id) {
        return (E) getCurrentSession().get(entityClass, id);
    }
 
    @Override
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }
 
    @Override
    public void delete(E e) {
        getCurrentSession().delete(e);
    }
 
    @Override
    public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }

    @Override
    public List<E> getAll() {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        return criteria.list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}