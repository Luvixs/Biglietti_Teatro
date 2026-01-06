package com.biglietti_teatro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.biglietti_teatro.model.Replica;
import com.biglietti_teatro.repository.ReplicaRepository;

@Service
public class ReplicaService {
	@Autowired
    private ReplicaRepository replicaRepository;

    // Tutte le repliche (per Admin)
    public List<Replica> getAllRepliche() {
        return replicaRepository.findAll();
    }

    // Replica singola
    public Optional<Replica> getReplicaById(String id) {
        return replicaRepository.findById(id);
    }

    // Tutte le repliche per spettacolo
    public List<Replica> getReplicheBySpettacolo(String codSpettacolo) {
        return replicaRepository.findBySpettacolo_CodSpettacolo(codSpettacolo);
    }

    // Repliche future (da oggi in poi)
    public List<Replica> getReplicheDisponibili() {
        return replicaRepository.findByDataReplicaGreaterThanEqual(LocalDate.now());
    }
    
}
