package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.MeterReader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeterReaderRepository extends JpaRepository<MeterReader, Long> {

    List<MeterReader> findTop2ByCustomerOrderByReadingDateDesc(Customer customer);

}
