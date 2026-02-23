import { createSlice, createAsyncThunk} from '@reduxjs/toolkit';
import authService from '../../services/authService';


/**
 * STATO INIZIALE
 * 
 * Lo slice mantiene queste informazioni:
 * - cliente: dati del cliente loggato (null se non loggato)
 * - status: stato della richiesta ('idle' | 'loading' | 'succeeded' | 'failed')
 * - error: messaggio di errore in caso di fallimento
 */
const initialState = {
  cliente: JSON.parse(localStorage.getItem('cliente')) || null,
  status: 'idle',
  error: null,
};

/**
 * THUNK ASINCRONO: loginCliente
 * 
 * Un thunk è una funzione speciale di Redux Toolkit che permette
 * di eseguire operazioni asincrone (chiamate API) e poi aggiornare lo stato.
 * 
 * createAsyncThunk gestisce automaticamente 3 stati:
 * - pending: richiesta in corso
 * - fulfilled: richiesta completata con successo
 * - rejected: richiesta fallita
 * 
 * @param {string} email - Email del cliente
 * @returns {object} Dati del cliente se login ha successo
 */
export const loginCliente = createAsyncThunk(
  'auth/login',
  async (email, { rejectWithValue }) => {
    try {
      const response = await authService.login(email);
      const { cliente } = response.data;
      
      localStorage.setItem('cliente', JSON.stringify(cliente));
      
      return cliente;
      
    } catch (error) {
      return rejectWithValue(error.message || 'Errore durante il login');
    }
  }
);

/**
 * SLICE REDUX
 */
const authSlice = createSlice({
  name: 'auth',
  initialState,
  
  /**
   * REDUCERS SINCRONI
   * Azioni che modificano lo stato immediatamente, senza chiamate API.
   */
  reducers: {
    /**
     * Resetta eventuali errori.
     */
    clearError: (state) => {
      state.error = null;
      state.status = 'idle';
    },
    
    /**
     * Logout del cliente.
     * Pulisce lo stato Redux e rimuove i dati dal localStorage.
     */
    logout: (state) => {
      state.cliente = null;
      state.status = 'idle';
      state.error = null;
      localStorage.removeItem('cliente');
    },
  },
  
  /**
   * EXTRA REDUCERS
   * Gestiscono le azioni asincrone (thunk).
   */
  extraReducers: (builder) => {
    builder
      .addCase(loginCliente.pending, (state) => {
        state.status = 'loading';
        state.error = null;
      })
      .addCase(loginCliente.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.cliente = action.payload;
        state.error = null;
      })
      .addCase(loginCliente.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.payload;
        state.cliente = null;
      });
  },
});

export const { clearError, logout } = authSlice.actions;

export default authSlice.reducer;


