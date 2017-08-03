package uk.co.bbc.countmeup.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 31-Jul-17.
 */
public class Candidate {
    private int candidateId;
    // one-to-one relationship with User - Candidate is a User
    private User user;
    // one-to-many relationship with Votes - can have zero to many votes
    private List<Vote> votes = new ArrayList<Vote>();

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void addVote(Vote vote) {
        votes.add(vote);
    }
}
