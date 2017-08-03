package uk.co.bbc.countmeup.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Chris on 31-Jul-17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Long id;
    private String name;
    private String userName;
    private int votesCast = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getVotesCast() {
        return votesCast;
    }

    public void castVote() {
        votesCast++;
    }
}
