package sysc4806.graduateAdmissions.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a Term (4 month period)
 * Used to Store Deadlines and denote which Term is active
 *
 * @author Eric
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Term {

    @Id
    @Column(name = "term_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Boolean active;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    @Enumerated(EnumType.STRING)
    private Season season;
    private int year;

    /**
     * Creates a Term with an autogenerated id and given parameters
     *
     * @param deadline String representing the deadline for this term
     * @param season Season determining the season of the term
     * @param year String representing the year of the term
     * @param active boolean showing if the term is active or not
     */
    public Term(Date deadline, Season season, int year, Boolean active){
        this.deadline = deadline;
        this.season = season;
        this.year = year;
        this.active = active;
    }
}
