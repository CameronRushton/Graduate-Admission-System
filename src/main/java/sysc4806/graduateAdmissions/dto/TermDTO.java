package sysc4806.graduateAdmissions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import sysc4806.graduateAdmissions.model.Season;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@ToString
public class TermDTO {
    @Id
    private Long termId;
    @NotNull
    private Boolean active;
    @NotNull
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private Date deadline;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Season season;
    @NotNull
    private int year;
}
