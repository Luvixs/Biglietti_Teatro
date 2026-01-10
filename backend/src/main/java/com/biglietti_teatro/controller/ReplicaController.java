package com.biglietti_teatro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biglietti_teatro.model.Replica;
import com.biglietti_teatro.service.ReplicaService;

@RestController
@RequestMapping("/api/repliche")
@CrossOrigin(origins = "http://localhost:5173/")
public class ReplicaController {
	@Autowired
    private ReplicaService replicaService;
    
	// Repliche di uno spettacolo GET /api/repliche/spettacolo/{codSpettacolo}
    @GetMapping("/spettacolo/{codSpettacolo}")
    public ResponseEntity<List<Replica>> getReplicheBySpettacolo(
            @PathVariable String codSpettacolo) {
        List<Replica> repliche = replicaService.getReplicheBySpettacolo(codSpettacolo);
        return ResponseEntity.ok(repliche);
    }

    // Repliche disponibili (da oggi in poi) GET /api/repliche/disponibili
    @GetMapping("/disponibili")
    public ResponseEntity<List<Replica>> getReplicheDisponibili() {
        List<Replica> repliche = replicaService.getReplicheDisponibili();
        return ResponseEntity.ok(repliche);
    }
    
    // DEttaglio replica GET /api/repliche/{codReplica}
    @GetMapping("/{codReplica}")
    public ResponseEntity<Replica> getReplica(@PathVariable String codReplica) {
        return replicaService.getReplicaById(codReplica)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
 
    // tutte le repliche (admin) GET /api/repliche
    @GetMapping
    public ResponseEntity<List<Replica>> getAllRepliche() {
        List<Replica> repliche = replicaService.getAllRepliche();
        return ResponseEntity.ok(repliche);
    }
}
