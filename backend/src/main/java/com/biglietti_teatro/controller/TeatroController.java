package com.biglietti_teatro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biglietti_teatro.model.Teatro;
import com.biglietti_teatro.service.TeatroService;

@RestController
@RequestMapping("/api/teatri")
@CrossOrigin(origins = "http://localhost:5173/")
public class TeatroController {
	@Autowired
    private TeatroService teatroService;

	// Lista di Tutti i Teatri GET /api/teatri
    @GetMapping
    public ResponseEntity<List<Teatro>> getAllTeatri() {
        List<Teatro> teatri = teatroService.getAllTeatri();
        return ResponseEntity.ok(teatri);
    }
  
    // Dettaglio Teatro GET /api/teatri/{codTeatro}
    @GetMapping("/{codTeatro}")
    public ResponseEntity<Teatro> getTeatro(@PathVariable String codTeatro) {
        return teatroService.getTeatro(codTeatro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
