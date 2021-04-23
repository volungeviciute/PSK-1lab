package lt.vu.mif.PSK_1lab.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.PSK_1lab.Entities.School;
import lt.vu.mif.PSK_1lab.Entities.Teacher;
import lt.vu.mif.PSK_1lab.persistence.SchoolsDAO;
import lt.vu.mif.PSK_1lab.persistence.TeachersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class TeacherDetails implements Serializable {

    private Teacher teacher;

    @Inject
    private TeachersDAO teachersDAO;

    @Inject
    private SchoolsDAO schoolsDAO;
    @Getter @Setter
    private Integer schoolToAssignId = 0;

    @PostConstruct
    private void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teacherId = Integer.parseInt(requestParameters.get("teacherId"));
        this.teacher = teachersDAO.findOne(teacherId);
    }
    @Transactional
    public String assignToNewSchool(){
        List<School> schools = teacher.getSchools();
        School schoolToAssign = schoolsDAO.findOne(schoolToAssignId);
        if(!schools.contains(schoolToAssign)) {
            schools.add(schoolToAssign);
            teacher.setSchools(schools);
            teachersDAO.update(teacher);
        }
        return "teacher?faces-redirect=true&teacherId="+ teacher.getId();
    }

}
