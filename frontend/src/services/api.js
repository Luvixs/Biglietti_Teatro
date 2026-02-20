import axios from 'axios';

/**
 * Istanza axios configurata per comunicare con il backend Spring Boot.
 * 
 * Vantaggi di questa configurazione:
 * - URL base centralizzato (cambi in un solo punto)
 * - Interceptors per gestire errori in modo uniforme
 * - Timeout configurabile
 * - Headers di default
 */
// istanza axios con configurazione base
const api = axios.create({
    baseURL: import.meta.env.VITE_API_URL, // Legge da .env: http://localhost:8080/api
    timeout: 10000, // 10 secondi di timeout per le richieste
    headers: {
        'Content-Type': 'application/json',
    },
});
/**
 * INTERCEPTOR DI RICHIESTA
 * Viene eseguito PRIMA di ogni chiamata API.
 * Utile per aggiungere token di autenticazione, logging, etc.
 */
api.interceptors.request.use(
    (config) => {
        // log solo la richiesta
        console.log(`${config.method.toUpperCase()} ${config.url}`);
        // futuro posso aggiungere un token JWT:
        // const token = localStorage.getItem('token');
        // if (token) {
        //   config.headers.Authorization = `Bearer ${token}`;
        // }
        return config;
    },
    (error) => {
        console.error('Errore nella richiesta:', error);
        return Promise.reject(error);
    }
);
/**
 * INTERCEPTOR DI RISPOSTA
 * Viene eseguito DOPO ogni risposta dal server.
 * Gestisce gli errori in modo centralizzato.
 */
api.interceptors.response.use(
    (response) => {
        console.log(`${response.config.method.toUpperCase()} ${response.config.url} - Status: ${response.status}`);
        return response;
    },
    (error) => {
        //gestione errori HTTP centralizzata. Backend restituisce oggetti strutturati.
        let errorMessage = 'Errore connessione al server';

        if (error.response) {
            // Il server ha risposto con uno status code fuori dal range 2xx
            const status = error.response.status; 
            const data = error.response.data;
            // Estrae il messaggio di errore dal backend
            errorMessage = data?.message || data?.error || `Errore ${status}`;

            console.error(`${error.config.method.toUpperCase()} ${error.config.url} - Status: ${status}`);
            console.error('Dettagli:', errorMessage);

            // Gestione errori per status code
            switch (status) {
                case 400:
                errorMessage = data?.message || 'Dati non validi';
                break;
                case 401:
                errorMessage = 'Non autorizzato. Effettua il login.';
                break;
                case 404:
                errorMessage = 'Risorsa non trovata';
                break;
                case 500:
                errorMessage = 'Errore interno del server';
                break;
            }
        } else if (error.request) {
            // La richiesta è stata fatta ma non c'è risposta
            console.error('Nessuna risposta dal server:', error.request);
            errorMessage = 'Il server non risponde. Controlla la connessione.';
        }else {
            // Errore nella configurazione della richiesta
            console.error('Errore nella configurazione:', error.message);
            errorMessage = error.message;
        }

        // Restituisce un errore con messaggio leggibile
        return Promise.reject(new Error(errorMessage));
    }
);

export default api;