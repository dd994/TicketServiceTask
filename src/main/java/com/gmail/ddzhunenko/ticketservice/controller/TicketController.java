package com.gmail.ddzhunenko.ticketservice.controller;

import com.gmail.ddzhunenko.ticketservice.model.Order;
import com.gmail.ddzhunenko.ticketservice.service.TickerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tickets")
public class TicketController {

    private static Logger log = LoggerFactory.getLogger(TickerService.class);

    private final TickerService tickerService;


    @PostMapping("/new-order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        tickerService.receiveOrder(order);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/payment")
    public void paymentProcessing() {
        tickerService.paymentProcess();
    }

    @GetMapping("/get-orders/{id}")
    public List<Order> getClientOrders(@PathVariable("id") int clientID) {
        log.info("Order information sent");
        return tickerService.getClientOrdersFromNow(clientID);
    }


}
