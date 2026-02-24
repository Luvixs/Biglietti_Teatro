import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { loginCliente, clearError } from '../redux/slices/authSlice';

/**
 * Permette al cliente di accedere all'app inserendo la propria email.
 * Utilizza Redux per gestire lo stato dell'autenticazione.
 * Flow:
 * 1. Utente inserisce email
 * 2. Click su "Accedi"
 * 3. Dispatch dell'azione loginCliente
 * 4. Se successo → redirect alla home
 * 5. Se fallimento → mostra messaggio di errore
 */

function Login() {
    // State locale per il form (non serve Redux per dati temporanei del form)
    const [email, setEmail] = useState('');

    const dispatch = useDispatch();
    // Legge lo stato da Redux usando useSelector
    const { cliente, status, error} = useSelector((state) => state.auth);
    //  ROUTING 
    const navigate = useNavigate();
    /**
   * Effetto: redirect automatico se il cliente è già loggato.
   * useEffect esegue codice quando cambiano le dipendenze (cliente).
   * Se cliente !== null, significa che il login è avvenuto con successo,
   * quindi reindirizziamo alla home.
   */
    useEffect(() =>{
        if (cliente) {
            navigate('/'); // Redirect alla home
            }
    }, [cliente, navigate]);
    /**
   * Effetto: pulisce gli errori quando il componente viene smontato
   * Questo evita che errori di login precedenti rimangano visibili
   * se l'utente torna alla pagina di login.
   */
    useEffect(() => {
        return () => {
            dispatch(clearError());
        };
    }, [dispatch]);
    // Handlers Gestisce il submit del form
    const handleSubmit = (e) => {
        e.preventDefault(); // previene reload della pagina

        if (!email.trim()){
            alert('Inserisci un\'email');
            return;
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)){
            alert('Inserisci un\'email valida');
            return;
        }

        //Dispatch dell'azione di login
        dispatch(loginCliente(email.trim()));
    };
    // Gestione cambio del campo email
    const handleEmailChange = (e) => {
    setEmail(e.target.value);
    
    // Se c'è un errore visualizzato, lo pulisce quando l'utente inizia a digitare
    if (error) {
      dispatch(clearError());
    }
};
return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-purple-500 to-pink-500 p-4">
      <div className="bg-white rounded-lg shadow-2xl p-8 w-full max-w-md">
        
        {/* Header */}
        <div className="text-center mb-8">
          <h1 className="text-3xl font-bold text-gray-800 mb-2">
            🎭 Teatro Biglietti
          </h1>
          <p className="text-gray-600">
            Accedi con la tua email per prenotare
          </p>
        </div>

        {/* Form */}
        <form onSubmit={handleSubmit} className="space-y-6">
          
          {/* Campo Email */}
          <div>
            <label 
              htmlFor="email" 
              className="block text-sm font-medium text-gray-700 mb-2"
            >
              Email
            </label>
            <input
              id="email"
              type="email"
              value={email}
              onChange={handleEmailChange}
              placeholder="mario.rossi@email.com"
              disabled={status === 'loading'}
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500 focus:border-transparent outline-none transition disabled:bg-gray-100 disabled:cursor-not-allowed text-black"
            />
          </div>

          {/* Messaggio di errore */}
          {error && (
            <div className="bg-red-50 border border-red-200 rounded-lg p-4">
              <p className="text-red-800 text-sm">
                ⚠️ {error}
              </p>
            </div>
          )}

          {/* Bottone Submit */}
          <button
            type="submit"
            disabled={status === 'loading'}
            className="w-full bg-purple-600 text-white py-3 rounded-lg font-semibold hover:bg-purple-700 transition disabled:bg-gray-400 disabled:cursor-not-allowed flex items-center justify-center"
          >
            {status === 'loading' ? (
              <>
                {/* Spinner di caricamento */}
                <svg 
                  className="animate-spin h-5 w-5 mr-2" 
                  xmlns="http://www.w3.org/2000/svg" 
                  fill="none" 
                  viewBox="0 0 24 24"
                >
                  <circle 
                    className="opacity-25" 
                    cx="12" 
                    cy="12" 
                    r="10" 
                    stroke="currentColor" 
                    strokeWidth="4"
                  />
                  <path 
                    className="opacity-75" 
                    fill="currentColor" 
                    d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
                  />
                </svg>
                Accesso in corso...
              </>
            ) : (
              'Accedi'
            )}
          </button>
        </form>

        {/* Info aggiuntiva */}
        <div className="mt-6 text-center">
          <p className="text-sm text-gray-500">
            Solo clienti registrati possono accedere
          </p>
        </div>
      </div>
    </div>
  );
}

export default Login;