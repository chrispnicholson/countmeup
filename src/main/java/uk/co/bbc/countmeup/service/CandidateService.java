package uk.co.bbc.countmeup.service;

import org.springframework.stereotype.Service;
import uk.co.bbc.countmeup.dto.CandidateDto;

import java.util.List;

/**
 * Created by Chris on 31-Jul-17.
 */
@Service
public interface CandidateService {
    public CandidateDto getCandidate(int id);

    public List<CandidateDto> getAllCandidates();
}
