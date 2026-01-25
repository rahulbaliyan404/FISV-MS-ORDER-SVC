package com.home.service;

import com.home.client.UserClient;
import com.home.dto.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final UserClient userClient;

    public OrderService(UserClient userClient) {
        this.userClient = userClient;
    }

    @CircuitBreaker(name = "userCB", fallbackMethod = "fallbackCall")
    public User placeOrder(Long id) {
        User user = userClient.getUser(id);
        if (user == null || user.getId() == null) {
            throw new IllegalStateException("User not found");
        }
        return user;
    }

    public User fallbackCall(Long id, Throwable ex) {
        User pending = new User();
        pending.setId(id);
        return pending;
    }
}
