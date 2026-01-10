package com.biglietti_teatro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biglietti_teatro.model.Biglietto;
import com.biglietti_teatro.service.BigliettoService;

@RestController
@RequestMapping("/api/biglietti")
@CrossOrigin(origins = "http://localhost:5173/")
public class BigliettoController {
	@Autowired
    private BigliettoService bigliettoService;

    // Prenotazione Biglietti
    @PostMapping("/prenota")
    public ResponseEntity<?> prenotaBiglietto(@RequestBody Map<String, Object> body) {
        try {
            // Estrae e valida dati
            Integer codCliente = body.get("codCliente") != null 
                ? Integer.parseInt(body.get("codCliente").toString()) 
                : null;
            String codReplica = (String) body.get("codReplica");
            String tipoPagamento = (String) body.get("tipoPagamento");
            Integer quantita = body.get("quantita") != null 
                ? Integer.parseInt(body.get("quantita").toString()) 
                : null;

            // Validazione
            if (codCliente == null || codReplica == null || quantita == null) {
                return ResponseEntity.badRequest().body(
                    Map.of("success", false, 
                           "message", "Dati mancanti: Codice Cliente, Codice Replica e Quantità sono obbligatori")
                );
            }

            if (quantita <= 0) {
                return ResponseEntity.badRequest().body(
                    Map.of("success", false, 
                           "message", "Inserire la quantità dei biglietti")
                );
            }

            // Prenota
            Biglietto biglietto = bigliettoService.prenotaBiglietto(
                codCliente, codReplica, tipoPagamento, quantita);

            // Risposta strutturata
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Prenotazione effettuata con successo!",
                "biglietto", biglietto,
                "codOperazione", biglietto.getCodOperazione()
            ));

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                Map.of("success", false, 
                       "message", "Formato dati non valido")
            );

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("success", false, 
                       "message", e.getMessage())
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of("success", false, 
                       "message", "Errore interno del server")
            );
        }
    }

    // BIglietti di un cliente
    @GetMapping("/cliente/{codCliente}")
    public ResponseEntity<List<Biglietto>> getBigliettiByCliente(
            @PathVariable int codCliente) {
        List<Biglietto> biglietti = bigliettoService.getBigliettiByCliente(codCliente);
        return ResponseEntity.ok(biglietti);
    }

   // Posti disponibili
    @GetMapping("/disponibilita/{codReplica}")
    public ResponseEntity<?> getDisponibilita(@PathVariable String codReplica) {
        try {
            int disponibili = bigliettoService.getPostiDisponibili(codReplica);
            
            return ResponseEntity.ok(Map.of(
                "codReplica", codReplica,
                "postiDisponibili", disponibili,
                "disponibile", disponibili > 0
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", e.getMessage())
            );
        }
    }

    // Tutti Biglietti (admin)
    @GetMapping
    public ResponseEntity<List<Biglietto>> getAllBiglietti() {
        List<Biglietto> biglietti = bigliettoService.getAllBiglietti();
        return ResponseEntity.ok(biglietti);
    }

    // Cancella Prenotazione
    @DeleteMapping("/{codOperazione}")
    public ResponseEntity<?> deleteBiglietto(@PathVariable Integer codOperazione) {
        try {
            bigliettoService.deleteBiglietto(codOperazione);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Prenotazione cancellata con successo"
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        }
    }
}
