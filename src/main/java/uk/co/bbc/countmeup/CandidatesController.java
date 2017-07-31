package uk.co.bbc.countmeup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.bbc.countmeup.dto.CandidateDto;

/**
 * Created by Chris on 30-Jul-17.
 */

@RestController
public class CandidatesController {

    @RequestMapping(method={RequestMethod.GET},value={"/candidates"})
    public CandidateDto getCandidate(@RequestParam(value="id") String id) {
        CandidateDto candidate = new CandidateDto();
        candidate.setId(1);
        candidate.setName("Dougie Jones");
        candidate.setVotes(10000);
        candidate.setVotesPercentage("10");
        return candidate;
    }
}
