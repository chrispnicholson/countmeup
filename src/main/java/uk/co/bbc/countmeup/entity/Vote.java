package uk.co.bbc.countmeup.entity;

/**
 * Created by Chris on 31-Jul-17.
 */
public class Vote {
    private Long voteId;
    // many-to-one relationship
    private User voter;

    // many-to-one relationship
    private Candidate candidate;


    public Vote(User voter, Candidate candidate) {
        this.voter = voter;
        this.candidate = candidate;
        this.voter.castVote();
        this.candidate.addVote(this);

    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    @Override
    public int hashCode() {
        return voteId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            Vote other = (Vote) obj;
            return voteId.equals(other.getVoteId());
        }
    }
}
