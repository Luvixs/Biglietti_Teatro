package com.biglietti_teatro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.biglietti_teatro.model.Spettacolo;
import com.biglietti_teatro.repository.SpettacoloRepository;

@Service
public class SpettacoloService {
	
	@Autowired
    private SpettacoloRepository spettacoloRepository;

	// Lista di tutti gli spettacoli
    public List<Spettacolo> getAllSpettacoli() {
        return spettacoloRepository.findAll();
    }

    // Trova spettacolo per ID
    public Optional<Spettacolo> getSpettacoloById(String id) {
        return spettacoloRepository.findById(id);
    }

    // Trova spettacoli per teatro
    public List<Spettacolo> getSpettacoliByTeatro(String codTeatro) {
        return spettacoloRepository.findByTeatro_CodTeatro(codTeatro);
    }
    
    //salva nuovo spettacolo
    public Spettacolo saveSpettacolo(Spettacolo spettacolo) {
        return spettacoloRepository.save(spettacolo);
    }
    
    //elimina spettacolo
    public void deleteSpettacolo(String id) {
        spettacoloRepository.deleteById(id);
    }

}
