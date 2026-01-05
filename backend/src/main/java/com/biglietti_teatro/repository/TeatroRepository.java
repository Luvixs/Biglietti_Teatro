package com.biglietti_teatro.repository;

import com.biglietti_teatro.model.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeatroRepository extends JpaRepository<Teatro, String> {
	// Spring Data JPA genera automaticamente i metodi:
    // - findAll()
    // - findById(String codTeatro)
    // - save(Teatro teatro)
    // - deleteById(String codTeatro)
    // - count()
    // - existsById(String codTeatro)

}
