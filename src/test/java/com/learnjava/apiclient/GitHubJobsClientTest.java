package com.learnjava.apiclient;

import com.learnjava.domain.github.GitHubPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GitHubJobsClientTest {

    private WebClient webClient = WebClient.create("https://jobs.github.com/");
    private GitHubJobsClient gitHubJobsClient = new GitHubJobsClient(webClient);

    @Test
    void invokeGithubJonsAPI_withPageNumber() {
        List<GitHubPosition> gitHubPositions = gitHubJobsClient.invokeGithubJonsAPI_withPageNumber(1, "Java");

        assertNotNull(gitHubPositions);
        assertTrue(gitHubPositions.size() > 0);
        gitHubPositions
                .forEach(Assertions::assertNotNull);
    }
}