package sysc4806.graduateAdmissions.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sysc4806.graduateAdmissions.model.Season;
import sysc4806.graduateAdmissions.model.Term;
import sysc4806.graduateAdmissions.repositories.TermRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TermControllerUnitTest {

    @InjectMocks
    TermController termController;

    @Mock
    TermRepository repository;

    @Test
    public void testGetTermWithIdAndMockedController() {
        // Test controller with mocks
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Term term = Term.builder().id(1L).active(true).deadline("never").season(Season.SUMMER).year("1234").build();
        when(repository.findTermById(any(Long.class))).thenReturn(term);
        ResponseEntity responseEntity = termController.queryTerm(term.getId());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}