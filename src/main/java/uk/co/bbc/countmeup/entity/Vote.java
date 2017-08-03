package uk.co.bbc.countmeup.entity;

import javax.persistence.*;

/**
 * Created by Chris on 31-Jul-17.
 */
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private User voter;

    @ManyToOne
    private Candidate candidate;


    public Vote(User voter, Candidate candidate) {
        this.voter = voter;
        this.candidate = candidate;
        this.voter.castVote();
        this.candidate.addVote(this);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            Vote other = (Vote) obj;
            return id.equals(other.getId());
        }
    }
}
