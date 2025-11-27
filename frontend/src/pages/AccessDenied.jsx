import React from 'react';
import { useNavigate } from 'react-router-dom';

const AccessDenied = () => {
    const navigate = useNavigate();

    return (
        <div className="min-h-screen flex items-center justify-center bg-red-50">
            <div className="bg-white p-8 rounded-lg shadow-md w-96 text-center">
                <div className="text-red-500 text-6xl mb-4">⚠️</div>
                <h1 className="text-2xl font-bold text-gray-800 mb-2">Access Restricted</h1>
                <p className="text-gray-600 mb-6">
                    Only employees from the <strong>Outreach Department</strong> are allowed to access this dashboard.
                </p>
                <p className="text-sm text-gray-500 mb-6">
                    Please sign in with an email starting with <code>outreach</code>.
                </p>
                <button
                    onClick={() => navigate('/')}
                    className="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition duration-200"
                >
                    Back to Login
                </button>
            </div>
        </div>
    );
};

export default AccessDenied;
