package com.gmail.ddzhunenko.ticketservice.controller;

import com.gmail.ddzhunenko.ticketservice.model.Order;
import com.gmail.ddzhunenko.ticketservice.service.TickerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class TicketController {

    private final TickerServiceImpl tickerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new-order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        tickerService.receiveOrder(order);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/payment")
    public void paymentProcessing(){
        tickerService.paymentProcess();
    }



}
