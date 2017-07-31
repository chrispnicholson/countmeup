package uk.co.bbc.countmeup.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.bbc.countmeup.dao.CandidateRepository;
import uk.co.bbc.countmeup.dao.VoteRepository;
import uk.co.bbc.countmeup.dto.CandidateDto;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.entity.Vote;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by Chris on 31-Jul-17.
 */
@RunWith(SpringRunner.class)
public class CandidatesServiceImplTest {

    @MockBean
    private CandidateRepository candidateRepository;

    @MockBean
    private VoteRepository voteRepository;

    @Autowired
    private CandidateService candidateService;

    private Candidate candidate;

    @TestConfiguration
    static class CandidateServiceImplTestContextConfiguration {

        @Bean
        public CandidateService employeeService() {
            return new CandidateServiceImpl();
        }
    }

    @Before
    public void setUp() {
        User user = new User();
        user.setId(1234);
        user.setName("Dougie Jones");
        List<Vote> listOfVotes = new ArrayList<Vote>();
        for (int i = 0; i < 1000; i++) {
            Vote vote = new Vote();
            listOfVotes.add(vote);
        }
        candidate = new Candidate();
        candidate.setCandidateId(1);
        candidate.setUser(user);
        candidate.setVotes(listOfVotes);
    }

    @Test
    public void getCandidate() {
        when(candidateRepository.findCandidateById(1)).thenReturn(candidate);
        when(voteRepository.countAllVotes()).thenReturn(10000l);

        CandidateDto candidateDto = candidateService.getCandidate(1);
        assertNotNull(candidateDto);
        assertEquals(1, candidateDto.getId());
        assertEquals("Dougie Jones", candidateDto.getName());
        assertEquals(1000, candidateDto.getVotes());
        assertEquals("10%", candidateDto.getVotesPercentage());
    }
}
