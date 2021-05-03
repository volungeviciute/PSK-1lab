package lt.vu.mif.PSK_1lab.Alternative;

import lt.vu.mif.PSK_1lab.Entities.School;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Locale;

@Named
@RequestScoped
public class NameAllLower implements INameChanger{
    public void ChangeName(School school) {
        school.setName(school.getName().toLowerCase(Locale.ROOT));
    }

    public void ChangeName(lt.vu.mif.PSK_1lab.mybatis.model.School school) {
        school.setName(school.getName().toLowerCase(Locale.ROOT));
    }

}