package com.github.rluisb.voting.unity;

import com.github.rluisb.voting.aggregate.VotingAggregate;
import com.github.rluisb.voting.api.dto.VoteDto;
import com.github.rluisb.voting.command.AddVotingCommand;
import com.github.rluisb.voting.event.VotingAddedEvent;
import com.github.rluisb.voting.model.Vote;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class VotingTest {

    private FixtureConfiguration<VotingAggregate> fixture;

    @Before
    public void setUp() throws Exception {
        fixture =
                new AggregateTestFixture<VotingAggregate>(VotingAggregate.class);
    }

    @Test
    public void testSendNewVoteEventWithSuccess() {
        String id = UUID.randomUUID().toString();
        Vote vote = mockVote();
        fixture.given()
                .when(new AddVotingCommand(id,vote))
                .expectEvents(new VotingAddedEvent(id, vote));
    }

    @Test
    public void testSendOnlyVoteEventIdAndReturnError() {
        fixture.given()
                .when(new AddVotingCommand())
                .expectException(IllegalArgumentException.class);
    }

    private Vote mockVote() {
        Vote vote = new Vote();
        vote.setName("Voter 1");
        vote.setVote(true);
        vote.setVoterId(UUID.randomUUID().toString());
        return vote;
    }
}
