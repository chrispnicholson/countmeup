package uk.co.bbc.countmeup.service;

import org.springframework.stereotype.Service;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.entity.Vote;

/**
 * Created by Chris on 02-Aug-17.
 */

@Service
public class VoteServiceImpl implements VoteService {
    @Override
    public Vote castVote(User user, Candidate candidate) throws Exception {
        return null;
    }
}
