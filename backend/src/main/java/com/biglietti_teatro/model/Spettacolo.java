package com.biglietti_teatro.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "SPETTACOLI")
public class Spettacolo {
	@Id
    @Column(name = "COD_SPETTACOLO", length = 4)
    private String codSpettacolo;
    
    @Column(name = "TITOLO", length = 40, nullable = false)
    private String titolo;
    
    @Column(name = "AUTORE", length = 25)
    private String autore;
    
    @Column(name = "REGISTA", length = 25)
    private String regista;
    
    @Column(name = "PREZZO", precision = 6, scale = 2)
    private BigDecimal prezzo;
    
    @ManyToOne //  Molti spettacoli appartengono a un teatro (relazione N:1)
    @JoinColumn(name = "COD_TEATRO", referencedColumnName = "COD_TEATRO")
    private Teatro teatro;
    
    @OneToMany(mappedBy = "spettacolo", cascade = CascadeType.ALL)  // Navigazione
    private List<Replica> repliche;
    
    // Costruttori
    public Spettacolo() {}
    
    public Spettacolo(String codSpettacolo, String titolo, String autore,
                      String regista, BigDecimal prezzo, Teatro teatro) {
        this.codSpettacolo = codSpettacolo;
        this.titolo = titolo;
        this.autore = autore;
        this.regista = regista;
        this.prezzo = prezzo;
        this.teatro = teatro;
    }
    
    //getter e setter
	public String getCodSpettacolo() {
		return codSpettacolo;
	}

	public void setCodSpettacolo(String codSpettacolo) {
		this.codSpettacolo = codSpettacolo;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getRegista() {
		return regista;
	}

	public void setRegista(String regista) {
		this.regista = regista;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public Teatro getTeatro() {
		return teatro;
	}

	public void setTeatro(Teatro teatro) {
		this.teatro = teatro;
	}

	public List<Replica> getRepliche() {
		return repliche;
	}

	public void setRepliche(List<Replica> repliche) {
		this.repliche = repliche;
	}
        
}
