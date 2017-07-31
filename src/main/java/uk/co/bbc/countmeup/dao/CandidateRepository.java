package uk.co.bbc.countmeup.dao;

import uk.co.bbc.countmeup.entity.Candidate;

/**
 * Created by Chris on 31-Jul-17.
 */
public interface CandidateRepository /* extends JpaRepository<Candidate, Integer> */ {
    public Candidate findCandidateById(Integer id);
}
