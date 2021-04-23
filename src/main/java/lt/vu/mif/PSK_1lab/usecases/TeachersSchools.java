package lt.vu.mif.PSK_1lab.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.PSK_1lab.Entities.School;
import lt.vu.mif.PSK_1lab.Entities.Teacher;
import lt.vu.mif.PSK_1lab.persistence.SchoolsDAO;
import lt.vu.mif.PSK_1lab.persistence.TeachersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class TeachersSchools implements Serializable {
    @Inject
    private SchoolsDAO schoolsDAO;
    @Inject
    private TeachersDAO teachersDAO;
    @Getter @Setter
    private Teacher teacherToCreate = new Teacher();
    @Getter @Setter
    private School school;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int schoolId = Integer.parseInt(requestParameters.get("schoolId"));
        this.school = schoolsDAO.findOne(schoolId);
    }
    @Transactional
    public String createTeacher(){
        List<School> schools = new ArrayList<>();
        schools.add(this.school);
        teacherToCreate.setSchools(schools);
        teachersDAO.persist(this.teacherToCreate);
        return "students?faces-redirect=true&schoolId="+this.school.getId();
    }
}
