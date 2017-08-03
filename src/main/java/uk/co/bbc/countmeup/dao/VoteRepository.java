package uk.co.bbc.countmeup.dao;

import uk.co.bbc.countmeup.entity.Vote;

/**
 * Created by Chris on 31-Jul-17.
 */
public interface VoteRepository {
    public long countAllVotes();

    public void save(Vote vote);

}
