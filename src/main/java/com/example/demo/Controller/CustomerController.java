package com.example.demo.Controller;

import org.springframework.ui.Model;
import com.example.demo.Model.Customer;
import com.example.demo.Model.MeterReader;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.MeterReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MeterReaderRepository meterReaderRepository;

    public CustomerController(CustomerRepository customerRepository, MeterReaderRepository meterReaderRepository) {
        this.customerRepository = customerRepository;
        this.meterReaderRepository = meterReaderRepository;
    }

    @PostMapping("/customer/bill")
    public String getBill(@RequestParam String accountNumber, Model model) {
        Optional<Customer> customerOpt = customerRepository.findByAccountNumber(accountNumber);

        if (customerOpt.isEmpty()) {
            model.addAttribute("searched", true);
            model.addAttribute("bill", null);
            return "customer-bill";
        }

        Customer customer = customerOpt.get();
        List<MeterReader> readings = meterReaderRepository.findTop2ByCustomerOrderByReadingDateDesc(customer);

        if (readings.size() < 2) {
            model.addAttribute("searched", true);
            model.addAttribute("bill", null);
            return "customer-bill";
        }

        MeterReader last = readings.get(0);
        MeterReader previous = readings.get(1);

        int dateDifference = (int) ChronoUnit.DAYS.between(previous.getReadingDate(), last.getReadingDate());
        int usage = last.getReadingValue() - previous.getReadingValue();

        // Calculate unit ranges
        int firstRangeUnits = dateDifference;
        int secondRangeUnits = 2 * dateDifference;
        int thirdRangeUnits = usage - firstRangeUnits - secondRangeUnits;
        if (thirdRangeUnits < 0) {
            thirdRangeUnits = 0;
        }

        // Calculate charges
        int firstRangeAmount = 0;
        int secondRangeAmount = 0;
        int thirdRangeAmount = 0;
        int fixedCharge = 0;

        if (usage > (firstRangeUnits + secondRangeUnits)) {
            fixedCharge = 1500;
            int thirdRangeRate = 40;
            for (int i = 0; i < thirdRangeUnits; i++) {
                thirdRangeAmount += thirdRangeRate;
                thirdRangeRate += 1.0;
            }
        } else if (usage > firstRangeUnits) {
            fixedCharge = 1000;
            secondRangeAmount = secondRangeUnits * 35;
        } else {
            fixedCharge = 500;
            firstRangeAmount = firstRangeUnits * 20;
        }

        int totalAmount = fixedCharge + firstRangeAmount + secondRangeAmount + thirdRangeAmount;

        Map<String, Object> billDetails = new LinkedHashMap<>();
        billDetails.put("accountNumber", accountNumber);
        billDetails.put("lastReadingDate", last.getReadingDate());
        billDetails.put("previousReadingDate", previous.getReadingDate());
        billDetails.put("lastMeterReading", last.getReadingValue());
        billDetails.put("previousMeterReading", previous.getReadingValue());
        billDetails.put("fixedChargeAmount", fixedCharge);
        billDetails.put("firstRangeAmount", firstRangeAmount);
        billDetails.put("secondRangeAmount", secondRangeAmount);
        billDetails.put("thirdRangeAmount", thirdRangeAmount);
        billDetails.put("totalBilledAmount", totalAmount);

        model.addAttribute("bill", billDetails);
        model.addAttribute("searched", true);

        return "customer-bill";
    }

}
