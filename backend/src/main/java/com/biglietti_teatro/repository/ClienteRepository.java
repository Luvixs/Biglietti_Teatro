package com.biglietti_teatro.repository;

import com.biglietti_teatro.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	// Trova cliente per email (per il login)
    Optional<Cliente> findByEmail(String email);
    
    // SELECT * FROM CLIENTI WHERE EMAIL = ?
    
    // Verifica se email esiste già
    boolean existsByEmail(String email);

}
