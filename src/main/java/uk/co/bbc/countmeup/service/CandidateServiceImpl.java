package uk.co.bbc.countmeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.bbc.countmeup.dao.CandidateRepository;
import uk.co.bbc.countmeup.dao.UserRepository;
import uk.co.bbc.countmeup.dao.VoteRepository;
import uk.co.bbc.countmeup.dto.CandidateDto;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;

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

    @Autowired
    private UserRepository userRepository;

    @Override
    public CandidateDto getCandidateDto(int id) {
        Candidate candidate = candidateRepository.findOne(new Integer(id));
        long allVotesCount = voteRepository.count();

        return populateCandidateDto(candidate, allVotesCount);
    }

    private CandidateDto populateCandidateDto(Candidate candidate, long allVotesCount) {
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setId(candidate.getId());
        candidateDto.setName(candidate.getUser().getName());
        candidateDto.setVotes(candidate.getVotes().size());
        double rawPercentage = ((double) candidateDto.getVotes()) / ((double) allVotesCount);
        candidateDto.setVotesPercentage(toPercentage(rawPercentage));

        return candidateDto;
    }

    @Override
    public List<CandidateDto> getAllCandidateDtos() {
        Iterable<Candidate> candidateList = candidateRepository.findAll();
        long allVotesCount = voteRepository.count();

        List<CandidateDto> candidateDtoList = new ArrayList<CandidateDto>();

        for (Candidate candidate : candidateList) {
            candidateDtoList.add(populateCandidateDto(candidate, allVotesCount));
        }

        return candidateDtoList;
    }

    @Override
    public Candidate getCandidate(int id) {
        return null;
    }

    @Override
    public CandidateDto createCandidate(User nominatedUser) {
        Candidate candidate = new Candidate();
        candidate.setUser(nominatedUser);
        Candidate nominatedCandidate = candidateRepository.save(candidate);
        long allVotes = voteRepository.count();

        return populateCandidateDto(nominatedCandidate, allVotes);
    }

    //without decimal digits
    public static String toPercentage(double n){
        return String.format("%.0f",n*100.0d)+"%";
    }
}
