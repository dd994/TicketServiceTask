package com.gmail.ddzhunenko.ticketservice.service;

import com.gmail.ddzhunenko.ticketservice.model.Order;
import com.gmail.ddzhunenko.ticketservice.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TickerServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private Long currentTransactionId;
    Random random = new Random();

    public TickerServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void receiveOrder(Order order) {
        order.setTransactionID(identify());
        this.currentTransactionId = order.getTransactionID();
        ticketRepository.save(order);
    }

    @Override
    public void paymentProcess() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String result = StatusGenerator();
                    ticketRepository.setPaymentStatus(currentTransactionId, result);
                    while (result.equals("Processed")){
                        Thread.sleep(10000);
                        result = StatusGenerator();
                        ticketRepository.setPaymentStatus(currentTransactionId, result);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void paymentRequest() {

    }

    @Override
    public String orderStatusCheck(Long currentTransactionId) {
        return null;
    }

    private String StatusGenerator() {
        String result = null;
        int choice = random.nextInt(3) + 1;
        switch (choice) {
            case 1:
                result = "Processed";
                break;
            case 2:
                result = "Error";
                break;
            case 3:
                result = "Performed";
                break;
        }
        return result;
    }

    private Long identify() {
        return random.nextLong() + 1;
    }


}
