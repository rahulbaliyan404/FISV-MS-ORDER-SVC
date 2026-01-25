package com.home.resource;
import com.home.dto.User;
import com.home.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

   @GetMapping("/order/{id}")
   public ResponseEntity<User> getOrder(@PathVariable Long id) {
       User result = orderService.placeOrder(id);
       if (result == null) {
           return ResponseEntity.notFound().build();
       }
         return ResponseEntity.ok(result);
   }
}


