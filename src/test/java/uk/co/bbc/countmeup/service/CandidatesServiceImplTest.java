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

    private Candidate candidateOne;
    private Candidate candidateTwo;

    private List<Candidate> listOfCandidates;

    @TestConfiguration
    static class CandidateServiceImplTestContextConfiguration {

        @Bean
        public CandidateService candidateService() {
            return new CandidateServiceImpl();
        }
    }

    @Before
    public void setUp() {
        User userOne = new User();
        userOne.setId(new Long(1234));
        userOne.setName("Dougie Jones");
        List<Vote> listOfVotes = new ArrayList<Vote>();
        for (int i = 0; i < 1000; i++) {
            Vote vote = new Vote();
            listOfVotes.add(vote);
        }
        candidateOne = new Candidate();
        candidateOne.setCandidateId(1);
        candidateOne.setUser(userOne);
        candidateOne.setVotes(listOfVotes);

        User userTwo = new User();
        userTwo.setId(new Long(5678));
        userTwo.setName("Janey E");
        listOfVotes = new ArrayList<Vote>();
        for (int i = 0; i < 9000; i++) {
            Vote vote = new Vote();
            listOfVotes.add(vote);
        }
        candidateTwo = new Candidate();
        candidateTwo.setCandidateId(2);
        candidateTwo.setUser(userTwo);
        candidateTwo.setVotes(listOfVotes);

        listOfCandidates = new ArrayList<Candidate>();
        listOfCandidates.add(candidateOne);
        listOfCandidates.add(candidateTwo);
    }

    @Test
    public void getCandidate() {
        when(candidateRepository.findCandidateById(1)).thenReturn(candidateOne);
        when(voteRepository.countAllVotes()).thenReturn(10000l);

        CandidateDto candidateDto = candidateService.getCandidateDto(1);
        assertNotNull(candidateDto);
        assertEquals(1, candidateDto.getId());
        assertEquals("Dougie Jones", candidateDto.getName());
        assertEquals(1000, candidateDto.getVotes());
        assertEquals("10%", candidateDto.getVotesPercentage());
    }

    @Test
    public void getAllCandidates() {
        when(candidateRepository.findAllCandidates()).thenReturn(listOfCandidates);
        when(voteRepository.countAllVotes()).thenReturn(10000l);

        List<CandidateDto> candidateDtoList = candidateService.getAllCandidateDtos();
        assertNotNull(candidateDtoList);
        assertEquals(2, candidateDtoList.size());
        assertEquals("10%", candidateDtoList.get(0).getVotesPercentage());
        assertEquals("90%", candidateDtoList.get(1).getVotesPercentage());
    }
}
