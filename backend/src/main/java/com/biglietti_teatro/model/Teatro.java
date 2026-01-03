package com.biglietti_teatro.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEATRI")
public class Teatro {
	
	@Id
    @Column(name = "COD_TEATRO", length = 4)
    private String codTeatro;

    @Column(name = "NOME", length = 30)
    private String nome;

    @Column(name = "INDIRIZZO", length = 30)
    private String indirizzo;

    @Column(name = "CITTA", length = 25)
    private String citta;

    @Column(name = "PROVINCIA", length = 2)
    private String provincia;

    @Column(name = "TELEFONO", length = 14)
    private String telefono;

    @Column(name = "POSTI")
    private Integer posti;  // Integer può essere NULL

    @OneToMany(mappedBy = "teatro", cascade = CascadeType.ALL) // Un teatro ha molti spettacoli (relazione 1:N)
    private List<Spettacolo> spettacoli;

    // Costruttore vuoto
    public Teatro() {
    }

    // Costruttore con parametri
    public Teatro(String codTeatro, String nome, String indirizzo, 
                  String citta, String provincia, String telefono, Integer posti) {
        this.codTeatro = codTeatro;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.provincia = provincia;
        this.telefono = telefono;
        this.posti = posti;
    }

    // Getter e Setter
    public String getCodTeatro() { 
        return codTeatro; 
    }
    
    public void setCodTeatro(String codTeatro) { 
        this.codTeatro = codTeatro; 
    }

    public String getNome() { 
        return nome; 
    }
    
    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getIndirizzo() { 
        return indirizzo; 
    }
    
    public void setIndirizzo(String indirizzo) { 
        this.indirizzo = indirizzo; 
    }

    public String getCitta() { 
        return citta; 
    }
    
    public void setCitta(String citta) { 
        this.citta = citta; 
    }

    public String getProvincia() { 
        return provincia; 
    }
    
    public void setProvincia(String provincia) { 
        this.provincia = provincia; 
    }

    public String getTelefono() { 
        return telefono; 
    }
    
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }

    public Integer getPosti() { 
        return posti; 
    }
    
    public void setPosti(Integer posti) { 
        this.posti = posti; 
    }

    public List<Spettacolo> getSpettacoli() { 
        return spettacoli; 
    }
    
    public void setSpettacoli(List<Spettacolo> spettacoli) { 
        this.spettacoli = spettacoli; 
    }
	    	
}
