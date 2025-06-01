package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

      Optional <Customer> findByAccountNumber(String accountNumber);
}
