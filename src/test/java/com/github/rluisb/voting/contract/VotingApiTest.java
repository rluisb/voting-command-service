package com.github.rluisb.voting.contract;

import com.github.rluisb.voting.api.dto.VoteDto;
import com.github.rluisb.voting.model.CommandResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VotingApiTest {

    @LocalServerPort
    private Integer port;
    private WireMockServer wireMockServer = new WireMockServer(8097);


    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
        RestAssured.baseURI = String.format("http://localhost:%s/api/voting/v1", port);
        wireMockServer.start();
    }

    @After
    public void tearDown() throws Exception {
        wireMockServer.stop();
    }

    @Test
    public void shouldReturnSuccessWhenPostAVote() {
        given()
                .contentType(ContentType.JSON)
                .body(buildVoteDto())
                .when()
                .post("/votes")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract()
                .as(CommandResponse.class);
    }

    @Test
    public void shouldReturnErrorWhenPostAVote() {
        given()
                .contentType(ContentType.JSON)
                .body(buildEmptyVoteDto())
                .when()
                .post("/votes")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(400);
    }


    private VoteDto buildVoteDto() {
        VoteDto voteDto = new VoteDto();
        voteDto.setName("Voter 1");
        voteDto.setVote(true);
        voteDto.setVoterId(UUID.randomUUID().toString());
        return voteDto;
    }

    private VoteDto buildEmptyVoteDto() {
        return new VoteDto();
    }
}
