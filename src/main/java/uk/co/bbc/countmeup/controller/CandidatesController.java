package uk.co.bbc.countmeup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.bbc.countmeup.dto.CandidateDto;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.service.CandidateService;
import uk.co.bbc.countmeup.service.UserService;

import java.util.List;

/**
 * Created by Chris on 30-Jul-17.
 */

@RestController
@RequestMapping("/candidates")
public class CandidatesController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private UserService userService;

    @RequestMapping(method={RequestMethod.GET},value={"/{id}"})
    public CandidateDto getCandidate(@PathVariable int id) {
        return candidateService.getCandidateDto(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CandidateDto> getAllCandidates() {
        List<CandidateDto> listOfCandidates = candidateService.getAllCandidateDtos();
        return listOfCandidates;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CandidateDto> createCandidate(@RequestBody User user) {
        // does user exist?
        User existingUser = userService.getUser(user.getId());

        // TODO: null check - if this fails, send a forbidden response back

        //
        CandidateDto candidateDto = candidateService.createCandidate(existingUser);

        return new ResponseEntity<CandidateDto>(candidateDto, HttpStatus.CREATED);
    }
}
