package org.g1tf.controller;
import org.g1tf.dto.*; import org.g1tf.service.AuthService;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") @CrossOrigin("*")
public class AuthController{
 private final AuthService service;
 public AuthController(AuthService service){this.service=service;}
 @PostMapping("/register")
 public ResponseEntity<?> register(@RequestBody RegisterDTO dto){ return ResponseEntity.ok(service.register(dto));}
 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody LoginDTO dto){
   return service.login(dto)?ResponseEntity.ok("Login realizado"):ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais invalidas");
 }
}