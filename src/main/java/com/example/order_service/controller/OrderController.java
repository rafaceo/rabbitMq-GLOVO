package com.example.order_service.controller;

import com.example.order_service.dto.OrderDto;
import com.example.order_service.publisher.OrderPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderPublisher orderPublisher;

    @PostMapping("/")
    public ResponseEntity<String> create (@RequestBody OrderDto orderDto) {
        try {
            orderPublisher.publish( orderDto,"almaty");
            return new ResponseEntity<>("created", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("almost created", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{status}")
    public ResponseEntity<String> update (@RequestBody OrderDto orderDto, @PathVariable String status) {
        try {
            orderPublisher.update(orderDto,status);
            return new ResponseEntity<>("updated", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("almost updated bro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 }
