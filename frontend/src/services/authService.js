import api from './api';

/**
 * Service per la gestione dell'autenticazione.
 * 
 * Questo file contiene tutte le funzioni che comunicano con gli endpoint
 * del controller ClienteController.java nel backend.
 * 
 * Pattern utilizzato: ogni metodo ritorna direttamente la Promise di axios,
 * la gestione degli errori è centralizzata nell'interceptor di api.js
 */

const authService = {
    /**
   * Login del cliente tramite email.
   * Endpoint backend: POST /api/clienti/login
   * Body richiesto: { "email": "esempio@email.com" }
   * Risposta successo:
   * {
   *   success: true,
   *   message: "Login effettuato con successo",
   *   cliente: {
   *     codCliente: 1,
   *     nomeCliente: "Mario Rossi",
   *     email: "rossi@gmail.com",
   *     telefono: "1234567890"
   *   }
   * }
   * Risposta errore:
   * {
   *   success: false,
   *   message: "Cliente non trovato. Solo clienti registrati possono accedere."
   * }
   * @param {string} email - Email del cliente
   * @returns {Promise} Promise con i dati della risposta
   */

    login: (email) => {
        return api.post('/clienti/login', {email});
    },
    /**
   * Ottiene i dettagli di un cliente specifico.
   * Endpoint backend: GET /api/clienti/{codCliente}
   * Risposta: oggetto Cliente
   * @param {number} codCliente - Codice identificativo del cliente
   * @returns {Promise} Promise con i dati del cliente
   */
  getCliente: (codCliente) => {
    return api.get(`/clienti/${codCliente}`);
  },
  /**
   * Simula il logout rimuovendo i dati dal localStorage.
   * 
   * Nota: nel backend non c'è un endpoint di logout perché
   * l'autenticazione è gestita solo lato frontend. */
  logout: () => {
    // Rimuove i dati del cliente salvati in localStorage
    localStorage.removeItem('cliente');
    return Promise.resolve();
  },
};

export default authService;