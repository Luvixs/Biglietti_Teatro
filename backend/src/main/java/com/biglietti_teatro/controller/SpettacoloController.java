package com.biglietti_teatro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biglietti_teatro.model.Spettacolo;
import com.biglietti_teatro.service.SpettacoloService;

@RestController
@RequestMapping("/api/spettacoli")
@CrossOrigin(origins = "http://localhost:3000")
public class SpettacoloController {
	
	@Autowired
    private SpettacoloService spettacoloService;

	// Lista di tutti gli spettacoli  GET/api/spettacoli
    @GetMapping
    public ResponseEntity<List<Spettacolo>> getAllSpettacoli() {
        List<Spettacolo> spettacoli = spettacoloService.getAllSpettacoli();
        return ResponseEntity.ok(spettacoli);
    }

    // Dettaglio spettacolo GET /api/spettacoli/{codSpettacolo}
    @GetMapping("/{codSpettacolo}")
    public ResponseEntity<Spettacolo> getSpettacoloById(
            @PathVariable String codSpettacolo) {

        return spettacoloService.getSpettacoloById(codSpettacolo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Spettacoli per teatro GET /api/spettacoli/teatro/{codTeatro}
    @GetMapping("/teatro/{codTeatro}")
    public ResponseEntity<List<Spettacolo>> getSpettacoliByTeatro(
            @PathVariable String codTeatro) {

        List<Spettacolo> spettacoli =
                spettacoloService.getSpettacoliByTeatro(codTeatro);

        return ResponseEntity.ok(spettacoli);
    }

    // Crea nuovo spettacolo POST /api/spettacoli
    @PostMapping
    public ResponseEntity<Spettacolo> createSpettacolo(
            @RequestBody Spettacolo spettacolo) {

        Spettacolo saved = spettacoloService.saveSpettacolo(spettacolo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Aggiorna spettacolo  PUT /api/spettacoli/{codSpettacolo}
    @PutMapping("/{codSpettacolo}")
    public ResponseEntity<Spettacolo> updateSpettacolo(
            @PathVariable String codSpettacolo,
            @RequestBody Spettacolo spettacolo) {

        if (!spettacoloService.getSpettacoloById(codSpettacolo).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        spettacolo.setCodSpettacolo(codSpettacolo);
        Spettacolo updated = spettacoloService.saveSpettacolo(spettacolo);

        return ResponseEntity.ok(updated);
    }

    // Elimina spettacolo DELETE /api/spettacoli/{codSpettacolo}
    @DeleteMapping("/{codSpettacolo}")
    public ResponseEntity<Void> deleteSpettacolo(
            @PathVariable String codSpettacolo) {

        if (!spettacoloService.getSpettacoloById(codSpettacolo).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        spettacoloService.deleteSpettacolo(codSpettacolo);
        return ResponseEntity.noContent().build();
    }

}
