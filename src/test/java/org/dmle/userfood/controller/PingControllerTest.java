package org.dmle.userfood.controller;

import org.dmle.userfood.domain.ResponseEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

class PingControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ping_returnsResponseEntityInstance() {
        ResponseEntity<Map<String, String>> result = getController().ping();
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    void ping_returnsOKStatusInResponseEntity() {
        ResponseEntity<Map<String, String>> result = getController().ping();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void ping_returnsMapInResponseEntity() {
        ResponseEntity<Map<String, String>> result = getController().ping();
        Assertions.assertInstanceOf(Map.class, result.getData());
    }

    @Test
    void ping_returnsMessageKeyInMap() {
        ResponseEntity<Map<String, String>> result = getController().ping();
        Assertions.assertTrue(result.getData().containsKey("message"));
    }

    @Test
    void ping_returnsPongInMessageKeyInMap() {
        ResponseEntity<Map<String, String>> result = getController().ping();
        Assertions.assertEquals(result.getData().get("message"), "pong");
    }

    private PingController getController() {
        PingController controller = new PingController();
        return controller;
    }
}