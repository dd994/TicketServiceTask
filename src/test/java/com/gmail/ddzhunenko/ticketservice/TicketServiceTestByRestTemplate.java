package com.gmail.ddzhunenko.ticketservice;

import com.gmail.ddzhunenko.ticketservice.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TicketServiceTestByRestTemplate {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String createOrderUrl = "http://localhost:8080/tickets/new-order";
    private final String getOrdersUrl = "http://localhost:8080/tickets/get-orders/1";
    private final String paymentUrl = "http://localhost:8080/tickets/payment";

    Order order = new Order( 1L, "156A", LocalDateTime.of(2019, 10, 8, 15, 00), null, null, LocalDateTime.now(), 1);


    @Test
    public void createOrderTest() {
        HttpEntity<Order> userRequest = new HttpEntity<>(order);
        ResponseEntity<Order> responseEntity = restTemplate.postForEntity(createOrderUrl, userRequest, Order.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void paymentTest() {
        HttpEntity<Order> userRequest = new HttpEntity<>(order);
        ResponseEntity<Order> responseEntity = restTemplate.postForEntity(paymentUrl, userRequest, Order.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getUserByRestTemplateTest() {
        ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity(getOrdersUrl, Order[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


}