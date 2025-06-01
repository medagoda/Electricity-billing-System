package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {
    @PostMapping("/meter-reader")
    public String meterReaderPage() {
        return "meter-reader";
    }

    @GetMapping("/customer-bill")
    public String customerBillPage() {
        return "customer-bill";
    }
}
