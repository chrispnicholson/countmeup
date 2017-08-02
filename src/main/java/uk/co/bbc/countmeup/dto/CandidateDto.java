package uk.co.bbc.countmeup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by Chris on 30-Jul-17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "candidate")
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
