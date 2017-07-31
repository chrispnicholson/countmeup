package uk.co.bbc.countmeup.dto;

/**
 * Created by Chris on 30-Jul-17.
 */
public class CandidateDto {
    int id;
    String name;
    long votes;
    String votesPercentage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getVotesPercentage() {
        return votesPercentage;
    }

    public void setVotesPercentage(String votesPercentage) {
        this.votesPercentage = votesPercentage;
    }
}
