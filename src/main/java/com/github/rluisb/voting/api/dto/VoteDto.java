package com.github.rluisb.voting.api.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

public class VoteDto {

    @NotNull(message = "You must to fill voter id.")
    private String voterId;
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "You must to vote passing the value true or false.")
    private Boolean vote;

    public VoteDto() {
    }

    public VoteDto(String voterId, String name, Boolean vote) {
        this.voterId = voterId;
        this.name = name;
        this.vote = vote;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "VoteDto{" +
                "voterId='" + voterId + '\'' +
                ", name='" + name + '\'' +
                ", vote=" + vote +
                '}';
    }
}
