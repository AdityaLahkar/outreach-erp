import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { GoogleLogin } from '@react-oauth/google';
import { jwtDecode } from "jwt-decode";
import { login } from '../services/api';

const Login = () => {
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSuccess = async (credentialResponse) => {
        try {
            // 1. Decode the token to check email on Frontend
            const decoded = jwtDecode(credentialResponse.credential);
            const email = decoded.email;

            if (!email.startsWith('outreach')) {
                navigate('/access-denied');
                return;
            }

            // 2. If valid, send to backend for verification & session
            const response = await login(credentialResponse.credential);
            localStorage.setItem('user', JSON.stringify(response.data));
            navigate('/dashboard');
        } catch (err) {
            console.error(err);
            setError('Login failed. Server Error or Invalid Token.');
        }
    };

    const handleError = () => {
        setError('Google Login Failed');
    };

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-100">
            <div className="bg-white p-8 rounded-lg shadow-md w-96 flex flex-col items-center">
                <h2 className="text-2xl font-bold mb-6 text-center text-blue-600">Outreach Login</h2>
                {error && <p className="text-red-500 mb-4 text-sm">{error}</p>}

                <div className="w-full flex justify-center">
                    <GoogleLogin
                        onSuccess={handleSuccess}
                        onError={handleError}
                    />
                </div>
                <p className="mt-4 text-xs text-gray-500 text-center">
                    Please sign in with your official Outreach email.
                </p>
            </div>
        </div>
    );
};

export default Login;
