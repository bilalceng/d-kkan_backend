package com.bilalbererk.Dukkan.repositories;

import com.bilalbererk.Dukkan.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
