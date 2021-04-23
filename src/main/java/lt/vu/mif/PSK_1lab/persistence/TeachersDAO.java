package lt.vu.mif.PSK_1lab.persistence;

import lt.vu.mif.PSK_1lab.Entities.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class TeachersDAO {
    @Inject
    private EntityManager em;

    public void persist(Teacher teacher){ this.em.persist(teacher);}

    public Teacher findOne(Integer id){ return em.find(Teacher.class, id);}

    public Teacher update(Teacher teacher){ return em.merge(teacher);}
}
