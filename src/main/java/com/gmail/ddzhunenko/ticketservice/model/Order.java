package com.gmail.ddzhunenko.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Timer;

@Entity
@Table(name = "TICKET_ORDERS")
@Data
@Slf4j
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ID;

    @Column(name = "route_number",nullable = false)
    private String routeNumber;

    @Column(name = "route_date",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime routeDate;

    @JsonIgnore
    @Column(name = "transaction_id",nullable = false)
    private Long transactionID;

    @JsonIgnore
    @Column(name = "payment_status")
    private String paymentStatus;

    @JsonIgnore
    @Column(name = "processing_start_time")
    private LocalDateTime startTime;

    @Column(name = "client_id")
    private int clientID;


}
