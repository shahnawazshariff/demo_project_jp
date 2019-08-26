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

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IncomingControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getIncomingInstructions() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/incoming", List.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getIncomingInstructionsBySpecificDate() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/incoming/2019-08-19", List.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getIncomingAmount() throws Exception {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/incoming/amount", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getIncomingAmountBySpecificDate() throws Exception {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/incoming/amount/2019-08-19", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}
