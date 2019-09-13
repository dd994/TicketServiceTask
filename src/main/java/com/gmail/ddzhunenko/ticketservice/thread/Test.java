package com.gmail.ddzhunenko.ticketservice.thread;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Test {

    private static ArrayList<String> listFiles = new ArrayList<>();


    public static void main(String[] args) {

        Logger log = (Logger) LoggerFactory.getLogger(Test.class);
        log.info("Test started ...");
        for (int c = 0; c < 10; c++) {
            listFiles.add(String.valueOf(c));
        }


        new Thread() {
            public void run() {
                System.out.println(1);
                while (!listFiles.isEmpty()) {
                    System.out.println("Thread-1: ill getted: " + getElemAndRemoveFList());
                    try {
                        sleep(2);
                        System.out.println("Thread-1: delay end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                while (!listFiles.isEmpty()) {
                    System.out.println("Thread-2: ill getted: " + getElemAndRemoveFList());
                    try {
                        sleep(2);
                        System.out.println("Thread-2: delay end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


    private static synchronized String getElemAndRemoveFList() {
        String buff = null;
        if (listFiles.size() != 0) {
            buff = listFiles.get(0);
            listFiles.remove(0);
        }
        return buff;

    }

}