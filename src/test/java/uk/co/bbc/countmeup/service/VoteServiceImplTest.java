package uk.co.bbc.countmeup.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.bbc.countmeup.dao.VoteRepository;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.entity.Vote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Chris on 02-Aug-17.
 */
@RunWith(SpringRunner.class)
public class VoteServiceImplTest {

    @MockBean
    private VoteRepository voteRepository;

    @Autowired
    private VoteService voteService;

    private User voter;
    private Candidate candidate;

    @Before
    public void setUp() {
        voter = new User();
        candidate = new Candidate();
        voter.setId(new Long(1234l));
        voter.setUserName("dougiejones@lasvegas.co.uk");
        voter.setName("Dougie Jones");

        User candidateUser = new User();
        candidateUser.setId(new Long(5678));
        candidateUser.setUserName("david.lynch@davidlynchfoundation.org");
        candidateUser.setName("David Lynch");
        candidate.setUser(candidateUser);
        candidate.setCandidateId(1);
    }

    @TestConfiguration
    static class VoteServiceImplTestContextConfiguration {

        @Bean
        public VoteService voteService() {
            return new VoteServiceImpl();
        }
    }

    @Test
    public void castVoteOnce() throws Exception {

        Vote vote = voteService.castVote(voter, candidate);
        assertNotNull(vote);
        assertNotNull(vote.getCandidate());
        assertEquals(1, vote.getCandidate().getCandidateId());
        assertNotNull(vote.getCandidate().getUser());
        assertEquals("David Lynch", vote.getCandidate().getUser().getName());
        assertEquals("david.lynch@davidlynchfoundation.org", vote.getCandidate().getUser().getUserName());
        assertEquals(1, candidate.getVotes().size());
        assertEquals(1, voter.getVotesCast());

    }

    @Test(expected = Exception.class)
    public void castVoteFourTimes() throws Exception {
        assertNotNull(voteService.castVote(voter, candidate));
        assertNotNull(voteService.castVote(voter, candidate));
        assertNotNull(voteService.castVote(voter, candidate));
        assertEquals(3, candidate.getVotes().size());
        assertEquals(3, voter.getVotesCast());

        voteService.castVote(voter, candidate); // this should throw an exception
    }
}
