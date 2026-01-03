package com.biglietti_teatro.model;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENTI")
public class Cliente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CLIENTE")
    private Integer codCliente;
    
    @Column(name = "COGNOME", length = 20)
    private String cognome;
    
    @Column(name = "NOME", length = 20)
    private String nome;
    
    @Column(name = "TEL", length = 20)
    private String tel;
    
    @Column(name = "EMAIL", length = 50, unique = true)
    private String email;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Biglietto> biglietti;
    
    // Costruttore vuoto
    public Cliente() {
    }
    
    // Costruttore con parametri
    public Cliente(String cognome, String nome, String tel, String email) {
        this.cognome = cognome;
        this.nome = nome;
        this.tel = tel;
        this.email = email;
    }
    
    // Getter e Setter
    public Integer getCodCliente() {
        return codCliente;
    }
    
    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }
    
    public String getCognome() {
        return cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Biglietto> getBiglietti() {
        return biglietti;
    }
    
    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }
}
