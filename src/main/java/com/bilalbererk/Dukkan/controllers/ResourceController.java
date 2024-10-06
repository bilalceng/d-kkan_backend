package com.bilalbererk.Dukkan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
public class ResourceController {

    @GetMapping("/resource")
    ResponseEntity<String> getResource(){
      return ResponseEntity.ok("Here is the content");
    }
}
