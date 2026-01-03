package com.biglietti_teatro.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "REPLICHE")
public class Replica {
	@Id
    @Column(name = "COD_REPLICA", length = 4)
    private String codReplica;

    @ManyToOne
    @JoinColumn(name = "COD_SPETTACOLO", referencedColumnName = "COD_SPETTACOLO")
    private Spettacolo spettacolo;

    @Column(name = "DATA_REPLICA")
    private LocalDate dataReplica;

    // Orario fisso
    @Transient
    public String getOrario() {
        return "21:00";
    }

    // Costruttori
    public Replica() {}

    public Replica(String codReplica, Spettacolo spettacolo, LocalDate dataReplica) {
        this.codReplica = codReplica;
        this.spettacolo = spettacolo;
        this.dataReplica = dataReplica;
    }

    // Getter e Setter
    public String getCodReplica() { return codReplica; }
    public void setCodReplica(String codReplica) { this.codReplica = codReplica; }

    public Spettacolo getSpettacolo() { return spettacolo; }
    public void setSpettacolo(Spettacolo spettacolo) { this.spettacolo = spettacolo; }

    public LocalDate getDataReplica() { return dataReplica; }
    public void setDataReplica(LocalDate dataReplica) { this.dataReplica = dataReplica; }
}
