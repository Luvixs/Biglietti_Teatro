package com.biglietti_teatro.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "BIGLIETTI")
public class Biglietto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_OPERAZIONE")
    private Integer codOperazione;

    @ManyToOne
    @JoinColumn(name = "COD_CLIENTE", referencedColumnName = "COD_CLIENTE")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "COD_REPLICA", referencedColumnName = "COD_REPLICA")
    private Replica replica;

    @Column(name = "DATA_ORA")
    private LocalDate dataOra;

    @Column(name = "TIPO_PAGAMENTO")
    private String tipoPagamento;

    @Column(name = "QUANTITA")
    private Integer quantita;

    // --- Costruttori ---
    public Biglietto() {}

    public Biglietto(Cliente cliente, Replica replica, LocalDate dataOra, String tipoPagamento, Integer quantita) {
        this.cliente = cliente;
        this.replica = replica;
        this.dataOra = dataOra;
        this.tipoPagamento = tipoPagamento;
        this.quantita = quantita;
    }

    // --- Getter e Setter ---
    public Integer getCodOperazione() {
        return codOperazione;
    }

    public void setCodOperazione(Integer codOperazione) {
        this.codOperazione = codOperazione;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Replica getReplica() {
        return replica;
    }

    public void setReplica(Replica replica) {
        this.replica = replica;
    }

    public LocalDate getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDate dataOra) {
        this.dataOra = dataOra;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }
}
