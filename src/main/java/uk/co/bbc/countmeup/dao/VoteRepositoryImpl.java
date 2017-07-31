package uk.co.bbc.countmeup.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by Chris on 31-Jul-17.
 */
@Repository
public class VoteRepositoryImpl implements VoteRepository {
    @Override
    public long countAllVotes() {
        return 0;
    }
}
