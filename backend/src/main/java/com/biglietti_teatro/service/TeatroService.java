package com.biglietti_teatro.service;

import com.biglietti_teatro.model.Teatro;
import com.biglietti_teatro.repository.TeatroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeatroService {
    
    @Autowired
    private TeatroRepository teatroRepository;
    
    public List<Teatro> getAllTeatri() {
        return teatroRepository.findAll();
    }
    
    public Optional<Teatro> getTeatro(String codTeatro) {
        return teatroRepository.findById(codTeatro);
    }
}