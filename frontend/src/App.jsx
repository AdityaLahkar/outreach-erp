import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { GoogleOAuthProvider } from '@react-oauth/google';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import PlacementDetails from './pages/PlacementDetails';
import AccessDenied from './pages/AccessDenied';

function App() {
  return (
    <GoogleOAuthProvider clientId="805326466884-a3404jlc5rnghka41s70690r4o06k19s.apps.googleusercontent.com">
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/placement/:id" element={<PlacementDetails />} />
          <Route path="/access-denied" element={<AccessDenied />} />
        </Routes>
      </Router>
    </GoogleOAuthProvider>
  );
}

export default App;
