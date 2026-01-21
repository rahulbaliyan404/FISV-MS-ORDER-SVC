package com.home;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private final UserClient userClient;


    public OrderController(UserClient userClient) {
        this.userClient = userClient;
    }


    @GetMapping("/{id}")
    @CircuitBreaker(name = "userCB", fallbackMethod = "fallback")
    public String placeOrder(@PathVariable Long id) {
        User user = userClient.getUser(id);
        return "Order placed for " + user.getName();
    }


    public String fallback(Long id, Exception ex) {
        return "User Service DOWN → Order in PENDING state";
    }
}
