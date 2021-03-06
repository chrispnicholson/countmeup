package uk.co.bbc.countmeup.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.bbc.countmeup.dao.CandidateRepository;
import uk.co.bbc.countmeup.dao.UserRepository;
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

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CandidateService candidateService;

    private Candidate candidateOne;
    private Candidate candidateTwo;
    private Candidate candidateThree;

    private User userThree;

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
        candidateOne = new Candidate();
        candidateOne.setId(1);
        candidateOne.setUser(userOne);
        for (int i = 0; i < 1000; i++) {
            Vote vote = new Vote(userOne, candidateOne);
        }

        User userTwo = new User();
        userTwo.setId(new Long(5678));
        userTwo.setName("Janey E");
        listOfVotes = new ArrayList<Vote>();
        candidateTwo = new Candidate();
        candidateTwo.setId(2);
        candidateTwo.setUser(userTwo);
        for (int i = 0; i < 9000; i++) {
            Vote vote = new Vote(userTwo, candidateTwo);
        }

        listOfCandidates = new ArrayList<Candidate>();
        listOfCandidates.add(candidateOne);
        listOfCandidates.add(candidateTwo);

        userThree = new User();
        userThree.setId(new Long(9010));
        userThree.setName("Zero Votes");
        candidateThree = new Candidate();
        candidateThree.setId(3);
        candidateThree.setUser(userThree);
    }

    @Test
    public void getCandidate() {
        when(candidateRepository.findOne(1)).thenReturn(candidateOne);
        when(voteRepository.count()).thenReturn(10000l);

        CandidateDto candidateDto = candidateService.getCandidateDto(1);
        assertNotNull(candidateDto);
        assertEquals(1, candidateDto.getId());
        assertEquals("Dougie Jones", candidateDto.getName());
        assertEquals(1000, candidateDto.getVotes());
        assertEquals("10%", candidateDto.getVotesPercentage());
    }

    @Test
    public void getAllCandidates() {
        when(candidateRepository.findAll()).thenReturn(listOfCandidates);
        when(voteRepository.count()).thenReturn(10000l);

        List<CandidateDto> candidateDtoList = candidateService.getAllCandidateDtos();
        assertNotNull(candidateDtoList);
        assertEquals(2, candidateDtoList.size());
        assertEquals("10%", candidateDtoList.get(0).getVotesPercentage());
        assertEquals("90%", candidateDtoList.get(1).getVotesPercentage());
    }

    @Ignore
    @Test
    public void createCandidate() {
        when(candidateRepository.save(candidateThree)).thenReturn(candidateThree);
        when(voteRepository.count()).thenReturn(10000l);
        CandidateDto candidateDto = candidateService.createCandidate(userThree);
    }
}
