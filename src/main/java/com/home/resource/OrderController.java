package com.home.resource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.dto.User;
import com.home.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

   @GetMapping("/{id}")
   public ResponseEntity<User> getOrder(@PathVariable Long id) {
       User result = orderService.placeOrder(id);
       if (result == null) {
           return ResponseEntity.notFound().build();
       }
         return ResponseEntity.ok(result);
   }
}


