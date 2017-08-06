package uk.co.bbc.countmeup.service;

import org.springframework.stereotype.Service;
import uk.co.bbc.countmeup.dto.CandidateDto;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;

import java.util.List;

/**
 * Created by Chris on 31-Jul-17.
 */
@Service
public interface CandidateService {
    public CandidateDto getCandidateDto(int id);

    public List<CandidateDto> getAllCandidateDtos();

    public Candidate getCandidate(int id);

    public CandidateDto createCandidate(User nominatedUser);
}
