package uk.co.bbc.countmeup.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 31-Jul-17.
 */
@Entity
public class Candidate {

    @Id
    //@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    private List<Vote> votes = new ArrayList<Vote>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            Candidate other = (Candidate) obj;
            return id.equals(other.getId());
        }
    }
}
