package uk.co.bbc.countmeup.dao;

import org.springframework.data.repository.CrudRepository;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;

/**
 * Created by Chris on 31-Jul-17.
 */
public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
    //public Candidate findCandidateById(Integer id);

    //public List<Candidate> findAllCandidates();

    public Candidate findByUser(User user);
}
