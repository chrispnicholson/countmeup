package uk.co.bbc.countmeup.observable;

import uk.co.bbc.countmeup.observer.VoteObserver;

/**
 * Created by Chris on 02-Aug-17.
 */
public interface VoteObservable {
    public void registerVoteObserver(VoteObserver voteObserver);
    public void notifyObservers();
}
