package com.github.rluisb.voting.event;

import com.github.rluisb.voting.model.Vote;

public class VotingAddedEvent {

    private String id;
    private Vote vote;

    public VotingAddedEvent(String id, Vote vote) {
        this.id = id;
        this.vote = vote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "VotingAddedEvent{" +
                "id='" + id + '\'' +
                ", vote=" + vote +
                '}';
    }
}
