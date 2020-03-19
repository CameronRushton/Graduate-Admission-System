package sysc4806.graduateAdmissions.mapper;
import org.mapstruct.*;
import sysc4806.graduateAdmissions.dto.TermDTO;
import sysc4806.graduateAdmissions.model.Term;

@Mapper(componentModel = "spring")
public interface TermMapper {
    @Mapping(source = "id", target = "termId")
    TermDTO map(Term myTerm);

    @InheritInverseConfiguration
    Term map(TermDTO myTermDTO);

    @InheritInverseConfiguration
    Term map(TermDTO myTermDTO, @MappingTarget Term term);
}
