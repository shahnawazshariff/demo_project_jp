package com.jpmorgan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OutgoingControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getOutgoingInstructions() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/outgoing", List.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getOutgoingInstructionsBySpecificDate() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/outgoing/2019-08-18", List.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getOutgoingAmount() throws Exception {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/outgoing/amount", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getOutgoingAmountBySpecificDate() throws Exception {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/outgoing/amount/2019-08-18", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}
