package lt.vu.mif.PSK_1lab.Alternative;

import lt.vu.mif.PSK_1lab.Entities.School;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import java.util.Locale;

@Alternative
@Named
@RequestScoped
public class NameAllCaps implements INameChanger {
    public void ChangeName(School school) {
        school.setName(school.getName().toUpperCase(Locale.ROOT));
    }
    @Override
    public void ChangeName(lt.vu.mif.PSK_1lab.mybatis.model.School school) {
        school.setName(school.getName().toUpperCase(Locale.ROOT));
    }
}