package sysc4806.graduateAdmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import sysc4806.graduateAdmissions.dto.TermDTO;
import sysc4806.graduateAdmissions.mapper.TermMapper;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.repositories.TermRepository;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
public class TermManagerImpl implements TermManager {

    @Autowired
    private TermRepository termRepository;
    @Autowired
    private TermMapper termMapper;

    @Override
    public Optional<TermDTO> createTerm(@Valid TermDTO termDTO) {
        if (termDTO.getTermId() != null && termRepository.existsById(termDTO.getTermId())) {
            return Optional.empty();
        }
        Term newTerm = termMapper.map(termDTO);
        if(newTerm.getYear() >= 0){
            return Optional.of(termMapper.map(termRepository.save(newTerm)));
        } else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<TermDTO> updateTerm(@Valid TermDTO termDTO) {
        Optional<Term> existingTerm = termRepository.findById(termDTO.getTermId());
        if (existingTerm.isPresent() && termDTO.getYear() >= 0) {
            Term updatedTerm = termRepository.save(termMapper.map(termDTO, existingTerm.get()));
            return Optional.ofNullable(termMapper.map(updatedTerm));
        } else {
            return Optional.empty();
        }
    }

}
