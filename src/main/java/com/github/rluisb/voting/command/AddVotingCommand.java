package com.github.rluisb.voting.command;

import com.github.rluisb.voting.model.Vote;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class AddVotingCommand {

    @TargetAggregateIdentifier
    private String id;
    private Vote vote;

    public AddVotingCommand() {
    }

    public AddVotingCommand(String id, Vote vote) {
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
        return "AddVotingCommand{" +
                "id='" + id + '\'' +
                ", vote=" + vote +
                '}';
    }
}
