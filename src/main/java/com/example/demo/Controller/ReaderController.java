package com.example.demo.Controller;


import org.springframework.ui.Model;
import com.example.demo.Model.Customer;
import com.example.demo.Model.MeterReader;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.MeterReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class ReaderController {

    @Autowired
    private CustomerRepository customerRepository;

    public ReaderController(CustomerRepository customerRepository, MeterReaderRepository meterReaderRepository) {
        this.customerRepository = customerRepository;
        this.meterReaderRepository = meterReaderRepository;
    }

    @Autowired
    private MeterReaderRepository meterReaderRepository;

    @GetMapping("/meter/addReading")
    public String addReadingForm(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("readingDate") String readingDateStr,
            @RequestParam("meterReading") int meterReading,
            Model model) {

        Optional<Customer> customerOpt = customerRepository.findByAccountNumber(accountNumber);

        if (customerOpt.isEmpty()) {
            model.addAttribute("error", "Customer not found.");
            return "meter-reader";
        }

            LocalDate readingDate = LocalDate.parse(readingDateStr);

            MeterReader reading = new MeterReader();
            reading.setCustomer(customerOpt.get());
            reading.setReadingDate(readingDate);
            reading.setReadingValue(meterReading);

            meterReaderRepository.save(reading);

            model.addAttribute("success", true);
            return "meter-reader";


    }
}
