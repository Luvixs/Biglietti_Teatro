# Biglietti_Teatro
Panoramica

Sistema completo di gestione prenotazioni per teatri che permette ai clienti registrati di:
- Visualizzare il catalogo degli spettacoli disponibili
- Selezionare repliche con orario fisso (21:00)
- Prenotare biglietti con controllo automatico della disponibilità
- Consultare lo storico delle proprie prenotazioni

Il progetto è stato sviluppato seguendo i principi di **clean architecture**, **separation of concerns** e **RESTful API design**.

## Funzionalità

### **Per gli Utenti:**
- **Autenticazione**: Login tramite email per clienti pre-registrati
- **Catalogo Spettacoli**: Visualizzazione spettacoli con informazioni complete
- **Selezione Repliche**: Scelta della data (orario fisso: 21:00)
- **Prenotazione Biglietti**: Form interattivo con validazione real-time
- **Controllo Disponibilità**: Verifica automatica posti disponibili per teatro
- **Storico Prenotazioni**: Visualizzazione delle prenotazioni effettuate
- **Messaggi di Errore**: Feedback chiaro in caso di posti insufficienti

### **Validazioni Implementate:**
- Solo clienti registrati possono prenotare
- Controllo capienza teatro in tempo reale
- Ogni prenotazione crea un nuovo record (no aggiornamenti)
- Orario unico per tutti gli spettacoli (21:00)

## Tecnologie Utilizzate

### **Backend:**
| Tecnologia | Versione | Scopo |
|------------|----------|-------|
| Java | 17 | Linguaggio principale |
| Spring Boot | 3.x | Framework backend |
| Spring Data JPA | 3.x | ORM e gestione database |
| Hibernate | 6.x | Implementazione JPA |
| MySQL | 8.x | Database relazionale |
| Maven | 3.x | Gestione dipendenze |

### **Frontend:**
| Tecnologia | Versione | Scopo |
|------------|----------|-------|
| React | 18.x | Libreria UI |
| Vite | 5.x | Build tool e dev server |
| Redux Toolkit | 2.x | State management |
| Axios | 1.x | HTTP client |
| React Router | 6.x | Routing client-side |

### **Strumenti di Sviluppo:**
- Eclipse IDE (backend)
- Visual Studio Code (frontend)
- Git & GitHub (version control)
- Postman (API testing)
- MySQL Workbench (database management)

## 🏗️ Architettura

### **Architettura a Livelli (Layered Architecture)**
```
┌─────────────────────────────────────┐
│         Presentation Layer          │  ← React Components
├─────────────────────────────────────┤
│         REST Controllers            │  ← Spring @RestController
├─────────────────────────────────────┤
│         Service Layer               │  ← Business Logic
├─────────────────────────────────────┤
│         Repository Layer            │  ← Spring Data JPA
├─────────────────────────────────────┤
│         Data Layer (Entities)       │  ← JPA Entities
├─────────────────────────────────────┤
│         Database (MySQL)            │  ← Persistent Storage
└─────────────────────────────────────┘
```
## Database Schema
``` MySQL
TEATRO (1) ──────┐
                 │
                 ↓ (N)
              SPETTACOLO (1) ──────┐
                                   │
                                   ↓ (N)
                                REPLICA (1) ──────┐
                                                  │
                                                  ↓ (N)
CLIENTE (1) ──────────────────────────────→ BIGLIETTO
```
**Entità principali:**
- **TEATRI**: Informazioni sui teatri (nome, indirizzo, capienza)
- **SPETTACOLI**: Catalogo spettacoli (titolo, autore, regista, prezzo)
- **REPLICHE**: Date degli spettacoli (data, orario fisso 21:00)
- **CLIENTI**: Utenti registrati (nome, cognome, email)
- **BIGLIETTI**: Prenotazioni (cliente, replica, quantità, pagamento)

## API Endpoints
### **Biglietti**
```
POST /api/biglietti/prenota → Prenota biglietti
GET /api/biglietti/cliente/{codCliente} → Biglietti di un cliente
GET /api/biglietti/replica/{codReplica} → Biglietti di una replica
GET /api/biglietti/disponibilita/{codReplica} → Posti disponibili
DELETE /api/biglietti/{codOperazione} → Cancella prenotazione
GET /api/biglietti → Tutti i biglietti (admin)
```
### **Spettacoli**
```
GET /api/spettacoli → Tutti gli spettacoli
GET /api/spettacoli/{codSpettacolo} → Dettaglio spettacolo
GET /api/spettacoli/teatro/{codTeatro} → Spettacoli di un teatro
```
### **Repliche**
```
GET /api/repliche/spettacolo/{codSpettacolo} → Repliche di uno spettacolo
GET /api/repliche/disponibili → Repliche future
GET /api/repliche/{codReplica} → Dettaglio replica
GET /api/repliche → Tutte le repliche (admin)
```
### **Clienti**
```
POST /api/clienti/login → Login cliente
GET /api/clienti/{codCliente} → Dettaglio cliente
GET /api/clienti → Tutti i clienti (admin protetto)
```
### **Teatri**
```
GET /api/teatri → Tutti i teatri
GET /api/teatri/{codTeatro} → Dettaglio teatro
```

## Regole di Business

Il sistema implementa 4 regole fondamentali:

1. **Orario Unico**: Tutti gli spettacoli hanno orario fisso alle 21:00
2. **Accesso Riservato**: Solo clienti pre-registrati possono prenotare
3. **Storico Completo**: Ogni prenotazione crea un nuovo record (no aggiornamenti)
4. **Controllo Capienza**: Il sistema verifica automaticamente che la quantità di biglietti richiesta non superi i posti disponibili del teatro

---

## Cosa ho imparato

Questo progetto mi ha permesso di:

- Progettare e implementare un'architettura **REST API** completa
- Gestire **relazioni complesse** in database (1:N, N:1)
- Implementare **validazioni business** lato server
- Utilizzare **Redux Toolkit** per state management
- Integrare **frontend e backend** con chiamate asincrone
- Applicare principi **SOLID** e **clean code**
- Gestire **errori** e fornire feedback utente
- Utilizzare **Git** per version control

---

---

## Licenza e Ringraziamenti

Questo progetto è stato sviluppato a scopo didattico e di portfolio. 
Progetto sviluppato come parte del percorso di formazione in sviluppo full-stack.
---
