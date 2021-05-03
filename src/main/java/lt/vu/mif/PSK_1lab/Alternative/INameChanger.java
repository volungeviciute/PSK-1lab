package lt.vu.mif.PSK_1lab.Alternative;


import lt.vu.mif.PSK_1lab.Entities.School;

public interface INameChanger {
    void ChangeName(School school);

    void ChangeName(lt.vu.mif.PSK_1lab.mybatis.model.School school);
}
