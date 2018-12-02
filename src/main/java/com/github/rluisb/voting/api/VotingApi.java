package com.github.rluisb.voting.api;

import com.github.rluisb.voting.api.dto.VoteDto;
import com.github.rluisb.voting.command.AddVotingCommand;
import com.github.rluisb.voting.model.CommandResponse;
import com.github.rluisb.voting.model.Vote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = "Voting Command Api")
public class VotingApi implements BaseVersion {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private ModelMapper modelMapper;

    private static final int OK = 200;
    private static final int EXPECTATION_FAILED = 417;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final String OK_MESSAGE = "Successfully Operation.";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong in server. Please try again in a few minutes.";
    private static final String EXPECTATION_FAILED_MESSAGE = "Cannot send this command. Please, try again later.";

    @PostMapping("/votes")
    @ApiOperation(value = "This endpoint is the command to generate a new voting event.")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = CommandResponse.class),
            @ApiResponse(code = EXPECTATION_FAILED, message = EXPECTATION_FAILED_MESSAGE, response = CommandResponse.class),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<CommandResponse> create(@Valid @RequestBody VoteDto voteDto) {
        AddVotingCommand addVotingCommand = new AddVotingCommand(UUID.randomUUID().toString(), convertToModel(voteDto));
        LOGGER.info(String.format("Executing command: %s", addVotingCommand));
        String result = commandGateway.sendAndWait(addVotingCommand);
        if (Objects.isNull(result)) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new CommandResponse(1, "Command wasn't sent. An Error occurred.", result));
        }
        return ok().body(new CommandResponse(0, "Command was sent successfully.", result));
    }

    private Vote convertToModel(VoteDto voteDto) {
        return modelMapper.map(voteDto, Vote.class);
    }
}
