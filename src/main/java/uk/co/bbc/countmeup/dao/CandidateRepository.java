package uk.co.bbc.countmeup.dao;

import uk.co.bbc.countmeup.entity.Candidate;

import java.util.List;

/**
 * Created by Chris on 31-Jul-17.
 */
public interface CandidateRepository /* extends JpaRepository<Candidate, Integer> */ {
    public Candidate findCandidateById(Integer id);

    public List<Candidate> findAllCandidates();
}
