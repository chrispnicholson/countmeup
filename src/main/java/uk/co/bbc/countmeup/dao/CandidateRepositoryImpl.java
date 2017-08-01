package uk.co.bbc.countmeup.dao;

import org.springframework.stereotype.Repository;
import uk.co.bbc.countmeup.entity.Candidate;

import java.util.List;

/**
 * Created by Chris on 31-Jul-17.
 */
@Repository
public class CandidateRepositoryImpl implements CandidateRepository {
    @Override
    public Candidate findCandidateById(Integer id) {
        return null;
    }

    @Override
    public List<Candidate> findAllCandidates() {
        return null;
    }
}
