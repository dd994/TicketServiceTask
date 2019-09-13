package com.gmail.ddzhunenko.ticketservice;

import com.gmail.ddzhunenko.ticketservice.model.Order;
import org.springframework.web.client.RestTemplate;

public class TicketServiceTestByRestTemplate {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String CreateOrderUrl = "http://localhost:8080/order/new-order";

    Order order = new Order();



}
