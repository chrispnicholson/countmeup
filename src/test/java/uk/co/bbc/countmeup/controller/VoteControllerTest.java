package uk.co.bbc.countmeup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.bbc.countmeup.dto.CandidateDto;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.entity.Vote;
import uk.co.bbc.countmeup.service.CandidateService;
import uk.co.bbc.countmeup.service.UserService;
import uk.co.bbc.countmeup.service.VoteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Chris on 02-Aug-17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(VoteController.class)
public class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VoteService voteService;

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private UserService userService;

    private User user;
    private Candidate candidate;

    private CandidateDto candidateDto;

    private Vote voteOne;
    private Vote voteTwo;
    private Vote voteThree;

    private JacksonTester<CandidateDto> jsonTester;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, objectMapper);

        user = new User();
        user.setUserName("dougie.jones@lasvegas.co.uk");
        user.setId(1234l);
        user.setName("Dougie Jones");

        candidate = new Candidate();
        User candidateUser = new User();
        candidateUser.setName("David Lynch");
        candidateUser.setId(5678l);
        candidateUser.setUserName("david.lynch@davidlynchfoundation.org");
        candidate.setUser(candidateUser);
        candidate.setCandidateId(1);
//        candidate.setVotes(listVotes);

        candidateDto = new CandidateDto();
        candidateDto.setId(1);

        voteOne = new Vote(user, candidate);
        voteTwo = new Vote(user, candidate);
        voteThree = new Vote(user, candidate);

        voteOne.setVoteId(new Long(1000l));
        voteTwo.setVoteId(new Long(1001l));
        voteThree.setVoteId(new Long(1002l));

        Mockito.when(candidateService.getCandidate(1)).thenReturn(candidate);

        Mockito.when(userService.getUser(1234l)).thenReturn(user);

        Mockito.when(voteService.castVote(user, candidate))
                .thenReturn(voteOne)
                .thenReturn(voteTwo)
                .thenReturn(voteThree)
                .thenThrow(new Exception());
    }

    @Test
    public void castVoteForCandidate() throws Exception {
        String candidateJson = jsonTester.write(candidateDto).getJson();

        mockMvc.perform(post("/users/1234/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(candidateJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void castThreeVotes() throws Exception {
        String candidateJson = jsonTester.write(candidateDto).getJson();

        mockMvc.perform(post("/users/1234/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(candidateJson))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/users/1234/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(candidateJson))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/users/1234/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(candidateJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void castFourVotes() throws Exception {
        String candidateJson = jsonTester.write(candidateDto).getJson();

        mockMvc.perform(post("/users/1234/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(candidateJson))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/users/1234/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(candidateJson))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/users/1234/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(candidateJson))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/users/1234/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(candidateJson))
                .andExpect(status().isForbidden());

    }
}
