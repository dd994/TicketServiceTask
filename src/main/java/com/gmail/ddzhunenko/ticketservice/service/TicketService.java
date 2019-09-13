package com.gmail.ddzhunenko.ticketservice.service;

import com.gmail.ddzhunenko.ticketservice.model.Order;

public interface TicketService {


    void receiveOrder(Order order);
    void paymentProcess();
    void paymentRequest();
    String orderStatusCheck(Long transactionId);

}
