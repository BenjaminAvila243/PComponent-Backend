package PComponent_Eva3.PComponent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class estandoController {

    @GetMapping("/health")
public ResponseEntity<String> health() {
    return ResponseEntity.ok("OK");
}
    
}
