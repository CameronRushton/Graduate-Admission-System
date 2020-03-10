package sysc4806.graduateAdmissions.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue
    private long id;
    private boolean active;
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private Date deadline;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Season season;
    @NotNull
    @NotBlank
    private String year;

    /**
     * Creates a Term with an autogenerated id and given parameters
     *
     * @param deadline String representing the deadline for this term
     * @param season Season determining the season of the term
     * @param year String representing the year of the term
     * @param active boolean showing if the term is active or not
     */
    public Term(Date deadline, Season season, String year, boolean active){
        this.deadline = deadline;
        this.season = season;
        this.year = year;
        this.active = active;
    }
}
