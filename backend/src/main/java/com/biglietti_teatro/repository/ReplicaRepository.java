package com.biglietti_teatro.repository;

import com.biglietti_teatro.model.Replica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReplicaRepository extends JpaRepository<Replica, String>{
	// Trova tutte le repliche di uno spettacolo
    List<Replica> findBySpettacolo_CodSpettacolo(String codSpettacolo);
    
    // Trova repliche future (da oggi in poi)
    List<Replica> findByDataReplicaGreaterThanEqual(LocalDate data);
    
    // Trova repliche in una data specifica
    List<Replica> findByDataReplica(LocalDate data);
    

}
