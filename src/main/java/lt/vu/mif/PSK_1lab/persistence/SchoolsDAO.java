package lt.vu.mif.PSK_1lab.persistence;

import lt.vu.mif.PSK_1lab.Entities.School;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SchoolsDAO {
    @Inject
   private EntityManager em;

    public List<School> loadAll() { return em.createNamedQuery("School.findAll", School.class).getResultList();}

    public void persist(School school){
        this.em.persist(school);
    }

    public School findOne(Integer id){ return null;}

    public void setEm(EntityManager em){this.em = em;}
}
