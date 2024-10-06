package com.bilalbererk.Dukkan.repositories;

import com.bilalbererk.Dukkan.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
