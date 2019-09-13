//package com.gmail.ddzhunenko.ticketservice.thread;
//
//import com.gmail.ddzhunenko.ticketservice.repository.TicketRepository;
//import com.gmail.ddzhunenko.ticketservice.service.TickerServiceImpl;
//
//import java.util.Random;
//
//public class PaymentThread extends Thread {
//    Random random = new Random();
//    String result = null;
//    TicketRepository ticketRepository;
//    TickerServiceImpl tickerService;
//    Long currentTransactionID = tickerService.getCurrentTransactionId();
//
//    @Override
//    public void run() {
//        try {
//            String result = StatusGenerator();
//            if (result.equals("Processed")) {
//                while (true) {
//                    wait(5999);
//                    return;
//
//                    ticketRepository.setPaymentStatus(currentTransactionID,null);
//                }
//
//            } else {
//                result = StatusGenerator();
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public String getResult() {
//        return result;
//    }
//
//
//    private String StatusGenerator() {
//        String result = null;
//        int choice = random.nextInt(3) + 1;
//        switch (choice) {
//            case 0:
//                result = "Processed";
//                break;
//            case 1:
//                result = "Error";
//                break;
//            case 2:
//                result = "Performed";
//                break;
//        }
//
//        return result;
//    }
//}
