//import { useSelector, useDispatch } from 'react-redux';
//import { loginSuccess, logout } from './redux/slices/userSlice';

import { Route, Routes, Navigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import Login from './pages/Login';
import './App.css'

function App() {
  const { cliente } = useSelector((state) => state.auth);
  return (
    <Routes>
      {/* Rotta Login - accessibile solo se NON sei loggato */}
      <Route 
        path="/login" 
        element={!cliente ? <Login /> : <Navigate to="/" replace />} 
      />
      
      {/* Rotta Home - accessibile solo se SEI loggato */}
      <Route 
        path="/" 
        element={cliente ? <h1>Home - Benvenuto!</h1> : <Navigate to="/login" replace />} 
      />
      
      {/* Fallback: qualsiasi altra rotta non trovata */}
      <Route 
        path="*" 
        element={<Navigate to={cliente ? "/" : "/login"} replace />} 
      />
    </Routes>
  );
}

export default App;
