package sysc4806.graduateAdmissions.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Term {

    @Id
    @GeneratedValue
    private long id;
    private boolean active;
    private String deadline;
    private String semester;
    private String year;

    public Term(String deadline, String semester, String year, boolean active){
        this.deadline = deadline;
        this.semester = semester;
        this.year = year;
        this.active = active;
    }
}
