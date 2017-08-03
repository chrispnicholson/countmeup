package uk.co.bbc.countmeup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.bbc.countmeup.dto.CandidateDto;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.entity.Vote;
import uk.co.bbc.countmeup.service.CandidateService;
import uk.co.bbc.countmeup.service.UserService;
import uk.co.bbc.countmeup.service.VoteService;

/**
 * Created by Chris on 02-Aug-17.
 */
@RestController
@RequestMapping("/users/{userId}/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> castVote(@PathVariable int userId, @RequestBody CandidateDto candidateDto) {
        try {
            Candidate candidate = candidateService.getCandidate(candidateDto.getId());
            User user = userService.getUser(userId);
            Vote vote = voteService.castVote(user, candidate);

            return new ResponseEntity<>(user.getUserName() + " voted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Maximum votes cast", HttpStatus.FORBIDDEN);
        }

    }
}
