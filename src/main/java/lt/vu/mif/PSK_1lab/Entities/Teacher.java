package lt.vu.mif.PSK_1lab.Entities;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.PSK_1lab.Enums.SchoolSubject;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TEACHER")
@Getter
@Setter
public class Teacher implements Serializable {

    public Teacher(){}

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

    @Column(name = "SUBJECT")
    private SchoolSubject subject;

    @ManyToMany
    private List<School> schools;
}
