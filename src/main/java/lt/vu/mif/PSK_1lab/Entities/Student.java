package lt.vu.mif.PSK_1lab.Entities;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.PSK_1lab.Enums.SchoolYear;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "STUDENT")
@Getter @Setter
public class Student implements Serializable {

    public Student(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(max = 50)
    @Column(name = "SURNAME")
    private String surname;

    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "YEAR")
    private SchoolYear year;

    @ManyToOne
    private School school;
}
