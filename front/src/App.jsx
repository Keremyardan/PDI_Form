import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import PdiForm from './components/PdiForm';
import Login from './components/Login';

function App() {
  const [role, setRole] = useState(null);

  const handleLogin = (role) => {
    setRole(role);
  };

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login onLogin={handleLogin} />} />
        <Route path="/pdi-form" element={role ? <PdiForm /> : <Navigate to="/login" />} />
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </Router>
  );
}

export default App;
