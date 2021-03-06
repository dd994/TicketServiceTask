package com.gmail.ddzhunenko.ticketservice.service;

import com.gmail.ddzhunenko.ticketservice.model.Order;
import com.gmail.ddzhunenko.ticketservice.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TickerService {

    private static Logger log = LoggerFactory.getLogger(TickerService.class);
    private final TicketRepository ticketRepository;
    private Long currentTransactionId;
    private Random random = new Random();

    public TickerService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void receiveOrder(Order order) {
        order.setTransactionID(identify());
        order.setStartTime(LocalDateTime.now());
        currentTransactionId = order.getTransactionID();
        ticketRepository.save(order);
        removeCrashedOrders();
        log.info("Added a new order record to the database");
    }

    public void paymentProcess() {
        new Thread(() -> {
            try {
                String result = paymentService();
                ticketRepository.setPaymentStatus(currentTransactionId, result);
                while (result.equals("Processed")) {
                    Thread.sleep(60000);
                    result = paymentService();
                    ticketRepository.setPaymentStatus(currentTransactionId, result);
                }
            } catch (InterruptedException e) {
                log.error("Error on the thread off paymentProcess method",e);
            }
        }).start();
    }

    public List<Order> getClientOrdersFromNow(int clientID) {
        List<Order> orderList = null;
        try {
            orderList = ticketRepository.getClientsByClientID(clientID)
                    .stream()
                    .filter(order -> order.getRouteDate().isAfter(LocalDateTime.now()))
                    .sorted(Comparator.comparing(Order::getRouteDate))
                    .collect(Collectors.toList());
        } catch (ClassCastException e) {
            log.error("Wrong data type came");
        }
        return orderList;
    }

    private void removeCrashedOrders() {
        new Thread(() -> {
            try {
                while (true) {
                    List<Order> orders = ticketRepository.getAllByTransactionIDNotNull();
                    for (Order order : orders) {
                        if (order.getStartTime().getHour() - LocalDateTime.now().getHour() < 1 && order.getPaymentStatus().equals("Processed")) {
                            ticketRepository.setPaymentStatus(order.getTransactionID(), "Error");
                            ticketRepository.save(order);
                            Thread.sleep(60000);
                        }
                    }
                }
            } catch (NullPointerException e) {
                log.error("No value found in database, method - removeCrashedOrders()",e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private String paymentService() {
        final Map<Integer, String> status = new HashMap<>(Map.of(
                1, "Processed",
                2, "Error",
                3, "Performed"));

        int choice = random.nextInt(3) + 1;
        return status.get(choice);
    }

    private Long identify() {
        return random.nextLong();
    }


}
