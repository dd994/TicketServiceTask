package com.gmail.ddzhunenko.ticketservice.service;

import com.gmail.ddzhunenko.ticketservice.model.Order;

import java.util.List;
import java.util.stream.Stream;

public interface TicketService {

    void receiveOrder(Order order);

    void paymentProcess();

    void paymentRequest();

    String orderStatusCheck(Long transactionId);

    List<Order> getClientOrdersFromNow(int clientID);

}
