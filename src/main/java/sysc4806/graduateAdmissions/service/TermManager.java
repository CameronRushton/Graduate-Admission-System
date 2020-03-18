package sysc4806.graduateAdmissions.service;

import sysc4806.graduateAdmissions.dto.TermDTO;

import javax.validation.Valid;
import java.util.Optional;

public interface TermManager {

    /**
     * Create Term entity
     * @param termCreateDTO details to use to create the term
     * @return created term id
     */
    Optional<TermDTO> createTerm(@Valid TermDTO termCreateDTO);

    /**
     * Update Term entity by id if exists
     * @param termDTO details to update
     * @return updated term details
     */
    Optional<TermDTO> updateTerm(@Valid TermDTO termDTO);
}
