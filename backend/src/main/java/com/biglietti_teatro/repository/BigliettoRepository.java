package com.biglietti_teatro.repository;

import com.biglietti_teatro.model.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BigliettoRepository extends JpaRepository<Biglietto, Integer>{
	// Trova tutti i biglietti di un cliente
    List<Biglietto> findByCliente_CodCliente(Integer codCliente);
    
    // Trova tutti i biglietti di una replica
    List<Biglietto> findByReplica_CodReplica(String codReplica);
    
    // Conta i biglietti totali prenotati per una replica
    @Query("SELECT COALESCE(SUM(b.quantita), 0) FROM Biglietto b " +
           "WHERE b.replica.codReplica = :codReplica")
    Integer countBigliettiByReplica(@Param("codReplica") String codReplica);
    
    // SELECT COALESCE(SUM(QUANTITA), 0) 
    // FROM BIGLIETTI 
    // WHERE COD_REPLICA = ?

}
