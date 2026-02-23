import { configureStore } from '@reduxjs/toolkit';
import authReducer from './slices/authSlice';

/**
 * Store Redux centrale dell'applicazione.
 * Contiene tutto lo stato globale diviso in slice.
 */
export const store = configureStore({
  reducer: {
    auth: authReducer,
  }
});

export default store;