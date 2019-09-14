package com.gmail.ddzhunenko.ticketservice.repository;

import com.gmail.ddzhunenko.ticketservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;

public interface TicketRepository extends JpaRepository<Order, Long>{

    Order getByTransactionID(Long transactionId);

    List<Order> getAllByTransactionIDNotNull();

    void deleteByClientID(int clientID);

    @Modifying
    @Transactional
    @Query(value = "update  TICKET_ORDERS t set t.PAYMENT_STATUS = :status where t.TRANSACTION_ID = :transactionId", nativeQuery = true)
    void setPaymentStatus(@Param("transactionId")Long transactionId, @Param("status") String status);

    List<Order> getClientsByClientID(int clientID);


}
