package uk.co.bbc.countmeup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.bbc.countmeup.dto.CandidateDto;
import uk.co.bbc.countmeup.service.CandidateService;

import java.util.List;

/**
 * Created by Chris on 30-Jul-17.
 */

@RestController
@RequestMapping("/candidates")
public class CandidatesController {

    @Autowired
    private CandidateService candidateService;

    @RequestMapping(method={RequestMethod.GET},value={"/{id}"})
    public CandidateDto getCandidate(@PathVariable int id) {
        return candidateService.getCandidateDto(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CandidateDto> getAllCandidates() {
        List<CandidateDto> listOfCandidates = candidateService.getAllCandidateDtos();
        return listOfCandidates;
    }
}
