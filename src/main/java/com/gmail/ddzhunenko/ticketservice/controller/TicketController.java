package com.gmail.ddzhunenko.ticketservice.controller;

import com.gmail.ddzhunenko.ticketservice.model.Order;
import com.gmail.ddzhunenko.ticketservice.service.TickerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tickets")
public class TicketController {

    private final TickerServiceImpl tickerService;

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

    @GetMapping("/get-orders/{id}")
    public List<Order> getClientOrders (@PathVariable("id") int clientID){
        return tickerService.getClientOrdersFromNow(clientID);
    }



}
