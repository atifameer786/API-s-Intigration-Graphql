package org.task;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;



public class Implementation implements QuarkusApi {

    @Inject
    EntityManager entityManager;

    public List<Information> getAllInfo(){
        return entityManager.createNamedQuery("Information.findAll", Information.class).getResultList();
    }
    
    @Transactional
    public  Information addOrder(Information name){
        entityManager.persist(name);
        return name;
    }
    public Information getById(int i) {
        return entityManager.createQuery("select f from Information f where f.id=?1",Information.class).setParameter(1, i).getSingleResult();
       /* //using ORM
        Information entity=entityManager.find(Information.class, i);
            if (entity==null) {
                throw new WebApplicationException("Fruit with id of " + i + " does not exist.", 404);
            }
        return entity;
        */
    }
    
    public List<Information> getByName(Information name1) {
        List<Information> entities= entityManager.createQuery("select f from Information f where f.name=?1",Information.class).setParameter(1, name1.getName()).getResultList();
        if (entities.size() == 0) {
            throw new WebApplicationException("Fruit with name of  " + name1 + " does not exist.", 404);
        }
        return entities;
    }

    @Transactional    
    public Information deleteId(int i) {
        Information entity=entityManager.find(Information.class,i);
        entityManager.remove(entity);
        return entity;
    }

    @Transactional
    public Information updateName(int id, Information info) {
        Information entity=entityManager.find(Information.class,id);
        entityManager.remove(entity);
        entity.setName(info.getName());
        entityManager.persist(entity);
        return entity;
    }
}
