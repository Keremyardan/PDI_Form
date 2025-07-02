import { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import PdiForm from './components/PdiForm';
import Login from './components/Login';
import SelectionScreen from './components/SelectionScreen';
import FormView from './components/FormView';

function App() {
  const [role, setRole] = useState(null);

  const handleLogin = (role) => {
    setRole(role);
  };

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login onLogin={handleLogin} />} />
        <Route path ="/selection" element={role ? <SelectionScreen/> : <Navigate to="/login" />}/>
        <Route path='/view' element={role ? <FormView/> : <Navigate to="/login"/>}/>
        <Route path="/pdi-form" element={role ? <PdiForm /> : <Navigate to="/login" />} />
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </Router>
  );
}

export default App;
