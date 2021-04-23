package lt.vu.mif.PSK_1lab.Entities;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQueries(
        {
                @NamedQuery(name="School.findAll", query = "select s from School as s")
        }
)
@Table(name = "SCHOOL")
@Getter @Setter
public class School implements Serializable {
    public School(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(max = 50)
    @Column(name = "ADDRESS")
    private  String address;

    @OneToMany(mappedBy = "school")
    private List<Student> studentList;

    @ManyToMany(mappedBy = "schools")
    private List<Teacher> teachers;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return Objects.equals(id, school.id) &&
                Objects.equals(name, school.name);
    }

    public int hashCode(){
        return Objects.hash(id, name);
    }
}
