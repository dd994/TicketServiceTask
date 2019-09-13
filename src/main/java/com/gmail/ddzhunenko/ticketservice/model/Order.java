package com.gmail.ddzhunenko.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TICKET_ORDERS")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "route_number",nullable = false)
    private String routeNumber;

    @Column(name = "route_date",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate routeDate;

    @Column(name = "transaction_id",nullable = false)
    private Long transactionID;

    @Column(name = "payment_status")
    private String paymentStatus;

}
