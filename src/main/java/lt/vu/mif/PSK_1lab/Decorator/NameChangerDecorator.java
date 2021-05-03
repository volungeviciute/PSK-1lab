package lt.vu.mif.PSK_1lab.Decorator;

import lt.vu.mif.PSK_1lab.Alternative.INameChanger;
import lt.vu.mif.PSK_1lab.Entities.School;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.Locale;

@Decorator
public abstract class NameChangerDecorator implements INameChanger {
    @Inject @Delegate @Any
    private INameChanger nameChanger;

    public void ChangeName(School school) {
        nameChanger.ChangeName(school);
        school.setName(school.getName().toUpperCase(Locale.ROOT));
    }

    public void ChangeName(lt.vu.mif.PSK_1lab.mybatis.model.School school) {
        nameChanger.ChangeName(school);
        school.setName(school.getName().toUpperCase(Locale.ROOT));
    }

}