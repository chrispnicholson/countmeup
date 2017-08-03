package uk.co.bbc.countmeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.bbc.countmeup.dao.VoteRepository;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.entity.Vote;
import uk.co.bbc.countmeup.observable.VoteObservable;
import uk.co.bbc.countmeup.observer.VoteObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 02-Aug-17.
 */

@Service
public class VoteServiceImpl implements VoteService, VoteObservable {

    @Autowired
    private VoteRepository voteRepository;

    private List<VoteObserver> voteObservers = new ArrayList<VoteObserver>();

    // this method should be all one transaction - if rollback, all operations discarded
    @Override
    public Vote castVote(User user, Candidate candidate) throws Exception {
        Vote vote = null;
        if (user.getVotesCast() < 3) {
            vote = new Vote(user, candidate);
            voteRepository.save(vote);
        } else {
            throw new Exception();
        }

        return vote;
    }

    @Override
    public void registerVoteObserver(VoteObserver voteObserver) {
        voteObservers.add(voteObserver);
    }

    @Override
    public void notifyObservers() {
        for (VoteObserver voteObserver : voteObservers) {
            //voteObserver.
        }
    }
}
