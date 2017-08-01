package uk.co.bbc.countmeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.bbc.countmeup.dao.CandidateRepository;
import uk.co.bbc.countmeup.dao.VoteRepository;
import uk.co.bbc.countmeup.dto.CandidateDto;
import uk.co.bbc.countmeup.entity.Candidate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 31-Jul-17.
 */
@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public CandidateDto getCandidate(int id) {
        Candidate candidate = candidateRepository.findCandidateById(id);
        long allVotesCount = voteRepository.countAllVotes();

        return populateCandidateDto(candidate, allVotesCount);
    }

    private CandidateDto populateCandidateDto(Candidate candidate, long allVotesCount) {
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setId(candidate.getCandidateId());
        candidateDto.setName(candidate.getUser().getName());
        candidateDto.setVotes(candidate.getVotes().size());
        double rawPercentage = ((double) candidateDto.getVotes()) / ((double) allVotesCount);
        candidateDto.setVotesPercentage(toPercentage(rawPercentage));

        return candidateDto;
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidateList = candidateRepository.findAllCandidates();
        long allVotesCount = voteRepository.countAllVotes();

        List<CandidateDto> candidateDtoList = new ArrayList<CandidateDto>();

        for (Candidate candidate : candidateList) {
            candidateDtoList.add(populateCandidateDto(candidate, allVotesCount));
        }

        return candidateDtoList;
    }

    //without decimal digits
    public static String toPercentage(double n){
        return String.format("%.0f",n*100.0d)+"%";
    }
}
