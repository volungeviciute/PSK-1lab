package lt.vu.mif.PSK_1lab.usecases;

import java.util.List;
import lombok.Setter;
import lombok.Getter;

import lt.vu.mif.PSK_1lab.Alternative.INameChanger;
import lt.vu.mif.PSK_1lab.Entities.School;
import lt.vu.mif.PSK_1lab.persistence.SchoolsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class Schools {
    @Inject
    private INameChanger nameChangerComponent;

    @Inject
    private SchoolsDAO schoolsDAO;

    @Getter @Setter
    private School schoolToCreate = new School();

    @Getter
    private List<School> allSchools;

    @PostConstruct
    public void init(){loadAllSchools();}

    @Transactional
    public String createSchool(){
        this.nameChangerComponent.ChangeName(schoolToCreate);
        this.schoolsDAO.persist(schoolToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllSchools(){ this.allSchools = schoolsDAO.loadAll();  }
}
