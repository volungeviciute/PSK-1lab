package lt.vu.mif.PSK_1lab.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.PSK_1lab.mybatis.dao.SchoolMapper;
import lt.vu.mif.PSK_1lab.mybatis.model.School;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class SchoolsMyBatis {
    @Inject
    private SchoolMapper schoolMapper;

    @Getter
    private List<School> allSchools;

    @Getter @Setter
    private School schoolToCreate = new School();

    @PostConstruct
    public void init(){this.loadAllSchools();}

    private void loadAllSchools() { this.allSchools = schoolMapper.selectAll();}

    @Transactional
    public String createSchool(){
        schoolMapper.insert(schoolToCreate);
        return "/myBatis/Schools?faces-redirect=true";
    }
}
