package uk.co.bbc.countmeup.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.bbc.countmeup.dto.CandidateDto;
import uk.co.bbc.countmeup.service.CandidateService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Chris on 01-Aug-17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CandidatesController.class)
public class CandidatesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;

    @Test
    public void getCandidateById() throws Exception {
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setName("Dougie Jones");
        candidateDto.setId(1);
        candidateDto.setVotes(10000);
        candidateDto.setVotesPercentage("10%");

        Mockito.when(candidateService.getCandidateDto(1)).thenReturn(candidateDto);

        mockMvc.perform(get("/candidates/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Dougie Jones")))
                .andExpect(jsonPath("$.votes", is(10000)))
                .andExpect(jsonPath("$.votesPercentage", is("10%")));
    }

    @Test
    public void getListOfCandidates() throws Exception {
        List<CandidateDto> listOfCandidates = new ArrayList<CandidateDto>();
        for (int i = 0; i < 5; i++) {
            CandidateDto candidateDto = new CandidateDto();
            int candidateId = i+1;
            candidateDto.setId(candidateId);
            candidateDto.setName("candidate-"+ candidateId);
            candidateDto.setVotes(10000 + i*1000);
            candidateDto.setVotesPercentage(10 + i + "%");
            listOfCandidates.add(candidateDto);
        }

        Mockito.when(candidateService.getAllCandidateDtos()).thenReturn(listOfCandidates);

        mockMvc.perform(get("/candidates")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("candidate-1")))
                .andExpect(jsonPath("$[0].votes", is(10000)))
                .andExpect(jsonPath("$[0].votesPercentage", is("10%")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("candidate-2")))
                .andExpect(jsonPath("$[1].votes", is(11000)))
                .andExpect(jsonPath("$[1].votesPercentage", is("11%")));

    }
}
