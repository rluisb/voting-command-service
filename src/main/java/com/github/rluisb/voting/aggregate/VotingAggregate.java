package com.github.rluisb.voting.aggregate;

import com.github.rluisb.voting.command.AddVotingCommand;
import com.github.rluisb.voting.event.VotingAddedEvent;
import com.github.rluisb.voting.model.Vote;
import org.apache.log4j.Logger;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class VotingAggregate {
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @AggregateIdentifier
    private String id;
    private Vote vote;

    public VotingAggregate() {
    }

    @CommandHandler
    public VotingAggregate(AddVotingCommand addVotingCommand) {
        LOGGER.info(String.format("Handling %s command: %s", addVotingCommand.getClass().getSimpleName(), addVotingCommand));
        Assert.hasLength(addVotingCommand.getId(), "Id should not be empty or null.");

        apply(new VotingAddedEvent(addVotingCommand.getId(), addVotingCommand.getVote()));
        LOGGER.info(String.format("Done handling $s command: %s", addVotingCommand.getClass().getSimpleName(), addVotingCommand));
    }

    @EventSourcingHandler
    public void on(VotingAddedEvent event) {
        LOGGER.info(String.format("Handling %s event: %s", event.getClass().getSimpleName(), event));
        this.id = event.getId();
        this.vote = event.getVote();
        LOGGER.info(String.format("Done handling %s event: %s", event.getClass().getSimpleName(), event));
    }
}
