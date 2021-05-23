package lt.vu.mif.PSK_1lab.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.PSK_1lab.Entities.School;
import lt.vu.mif.PSK_1lab.Entities.Student;
import lt.vu.mif.PSK_1lab.Enums.SchoolYear;
import lt.vu.mif.PSK_1lab.Interceptor.InterceptorMethod;
import lt.vu.mif.PSK_1lab.persistence.SchoolsDAO;
import lt.vu.mif.PSK_1lab.persistence.StudentsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class SchoolStudents implements Serializable {
    @Inject
    private SchoolsDAO schoolsDAO;

    @Inject
    private StudentsDAO studentsDAO;

    @Getter @Setter
    private School school;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer schoolId = Integer.parseInt(requestParameters.get("schoolId"));
        System.out.println("SCHOOL ID: " + schoolId);
        this.school = schoolsDAO.findOne(schoolId);
    }

    @Transactional
    public String createStudent(){
        studentToCreate.setSchool(this.school);
        studentToCreate.setYear(SchoolYear.FIRST);
        studentsDAO.persist(studentToCreate);
        System.out.println("students?faces-redirect=true&schoolId="+this.school.getId());
        return "students?faces-redirect=true&schoolId="+this.school.getId();
    }

    @Transactional
    public String updateSchool(){
        System.out.println("updateSchool called");
        try {
            schoolsDAO.update(this.school);
        } catch (OptimisticLockException e){
            return "students?faces-redirect=true&schoolId=" + this.school.getId() + "&error=optimistic-lock-exception";
        }
        return "students?faces-redirect=true&schoolId="+this.school.getId();
    }
}
