package com.bdg.pc_build.order.repository;

import com.bdg.pc_build.order.enumerations.OrderStatus;
import com.bdg.pc_build.order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(OrderStatus status);
}