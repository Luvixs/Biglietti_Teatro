package com.biglietti_teatro.repository;

import com.biglietti_teatro.model.Spettacolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpettacoloRepository extends JpaRepository<Spettacolo, String> {
    
    // trova tutti gli spettacoli di un teatro
    List<Spettacolo> findByTeatro_CodTeatro(String codTeatro);
    
    // SELECT * FROM SPETTACOLI WHERE COD_TEATRO = ?
}
