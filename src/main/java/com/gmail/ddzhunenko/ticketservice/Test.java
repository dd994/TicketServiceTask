package com.gmail.ddzhunenko.ticketservice;

import java.util.HashMap;
import java.util.Random;

public class Test {

    static int getRand() {

        int random = new Random().nextInt(2);
        return random != 0 ? random : 2;
    }


    public static void main(String[] args) throws InterruptedException {
        HashMap<Integer, String> numState = new HashMap<>(2);
        numState.put(1, "Sleep");
        numState.put(2, "Processed");


        while ("Sleep".equals(numState.get(getRand()))) {
            System.out.println("Im sleeping 2 sec ...");
            Thread.sleep(2000);
        }


        System.exit(0);
    }
}
