package com.biglietti_teatro.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biglietti_teatro.model.Biglietto;
import com.biglietti_teatro.model.Cliente;
import com.biglietti_teatro.model.Replica;
import com.biglietti_teatro.model.Teatro;
import com.biglietti_teatro.repository.BigliettoRepository;
import com.biglietti_teatro.repository.ClienteRepository;
import com.biglietti_teatro.repository.ReplicaRepository;

@Service
public class BigliettoService {
	
	@Autowired
    private BigliettoRepository bigliettoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ReplicaRepository replicaRepository;
    
    // Prenota biglietti
     
    @Transactional
    public Biglietto prenotaBiglietto(int codCliente, String codReplica, 
                                      String tipoPagamento, int quantita) {
        
        // Verifica cliente registrato 
        Cliente cliente = clienteRepository.findById(codCliente)
            .orElseThrow(() -> new RuntimeException(
                "Cliente non registrato. Solo clienti registrati possono prenotare."));
        
        // Verifica replica esistente
        Replica replica = replicaRepository.findById(codReplica)
            .orElseThrow(() -> new RuntimeException("Replica non trovata"));
        
        // Ottieni teatro e posti totali
        Teatro teatro = replica.getSpettacolo().getTeatro();
        if (teatro == null) {
            throw new RuntimeException("Teatro non trovato per questo spettacolo");
        }
        
        int postiTotali = teatro.getPosti();
        
        // Calcola biglietti già prenotati
        Integer giaPrenotati = bigliettoRepository.countBigliettiByReplica(codReplica);
        
        // Verifica disponibilità
        int nuoviPosti = giaPrenotati + quantita;
        if (nuoviPosti > postiTotali) {
            int disponibili = postiTotali - giaPrenotati.intValue();
            throw new RuntimeException(
                String.format("Posti insufficienti! Disponibili: %d, Richiesti: %d. " +
                             "Teatro: %s (Capacità: %d posti)",
                             disponibili, quantita, teatro.getNome(), postiTotali));
        }
        
        //Crea nuovo biglietto (sempre nuovo record)
        Biglietto biglietto = new Biglietto();
        biglietto.setCliente(cliente);
        biglietto.setReplica(replica);
        biglietto.setTipoPagamento(tipoPagamento);
        biglietto.setQuantita(quantita);
        biglietto.setDataOra(LocalDate.now());
        
        return bigliettoRepository.save(biglietto);
    }
    
    // Lista tutti i biglietti (solo admin)
    public List<Biglietto> getAllBiglietti() {
        return bigliettoRepository.findAll();
    }
    
    //Biglietti di un cliente
    public List<Biglietto> getBigliettiByCliente(int codCliente) {
        return bigliettoRepository.findByCliente_CodCliente(codCliente);
    }
    
    //Biglietti di una replica (per admin/statistiche)
    public List<Biglietto> getBigliettiByReplica(String codReplica) {
        return bigliettoRepository.findByReplica_CodReplica(codReplica);
    }
    
   //Calcola posti disponibili
    public int getPostiDisponibili(String codReplica) {
    	Replica replica = replicaRepository.findById(codReplica).orElse(null);
        if (replica == null) return 0;
        
        Teatro teatro = replica.getSpettacolo().getTeatro();
        if (teatro == null) return 0;
        
        int postiTotali = teatro.getPosti();
        
        Integer giaPrenotati = bigliettoRepository.countBigliettiByReplica(codReplica);
    
        return postiTotali - giaPrenotati;
    }
    
    //Cancella prenotazione (con controlli)
    @Transactional
    public void deleteBiglietto(Integer id) {
        Biglietto biglietto = bigliettoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Biglietto non trovato"));
        
        // Verifica che la replica non sia già passata
        LocalDate dataReplica = biglietto.getReplica().getDataReplica();
        if (LocalDate.now().isAfter(dataReplica)) {
            throw new RuntimeException(
                "Impossibile cancellare: la replica è già avvenuta");
        }
        
        bigliettoRepository.deleteById(id);
    }

}
