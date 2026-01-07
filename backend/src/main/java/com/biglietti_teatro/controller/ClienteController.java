package com.biglietti_teatro.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biglietti_teatro.model.Cliente;
import com.biglietti_teatro.service.ClienteService;

@RestController
@RequestMapping("/api/clienti")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {
	@Autowired
    private ClienteService clienteService;

	// Login Cliente POST /api/clienti/login Body: { "email": "rossi@gmail.com" }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                Map.of("success", false, 
                       "message", "Email richiesta")
            );
        }
        
        Optional<Cliente> clienteOpt = clienteService.getClienteByEmail(email);
        
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login effettuato con successo");
            response.put("cliente", cliente);
            
            return ResponseEntity.ok(response);
            
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Cliente non trovato. Solo clienti registrati possono accedere.");
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    // Dettaglio cliente GET /api/clienti/{codCliente}
    @GetMapping("/{codCliente}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Integer codCliente) {
        return clienteService.getClienteById(codCliente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
