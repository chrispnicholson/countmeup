package uk.co.bbc.countmeup.dao;

import org.springframework.data.repository.CrudRepository;
import uk.co.bbc.countmeup.entity.Vote;

/**
 * Created by Chris on 31-Jul-17.
 */
public interface VoteRepository extends CrudRepository<Vote, Integer> {
    //public Long count();
}
