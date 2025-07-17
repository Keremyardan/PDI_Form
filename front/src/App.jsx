import { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import PdiForm from './components/PdiForm';
import Login from './components/Login';
import SelectionScreen from './components/SelectionScreen';
import FormView from './components/FormView';
import FormDetail from './components/FormDetails';

function App() {
  const [role, setRole] = useState(null);
  

useEffect(() => {

  
  const storedRole = localStorage.getItem("role");
  if (storedRole) setRole(storedRole);
}, []);


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
        <Route path='/form-detail/:id' element={<FormDetail/>}/>
        <Route path="/formview" element={<FormView />} />
      </Routes>
    </Router>
  );
}

export default App;
