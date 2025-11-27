import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getPlacements } from '../services/api';

const Dashboard = () => {
    const [placements, setPlacements] = useState([]);
    const navigate = useNavigate();
    const user = JSON.parse(localStorage.getItem('user'));

    useEffect(() => {
        if (!user) {
            navigate('/');
            return;
        }
        fetchPlacements();
    }, []);

    const fetchPlacements = async () => {
        try {
            const response = await getPlacements();
            setPlacements(response.data);
        } catch (error) {
            console.error('Failed to fetch placements', error);
        }
    };

    return (
        <div className="min-h-screen bg-gray-50 p-8">
            <div className="max-w-6xl mx-auto">
                <header className="flex justify-between items-center mb-8">
                    <h1 className="text-3xl font-bold text-gray-800">Placement Offers</h1>
                    <div className="text-gray-600">
                        Welcome, <span className="font-semibold">{user?.firstName}</span>
                        <button
                            onClick={() => { localStorage.removeItem('user'); navigate('/'); }}
                            className="ml-4 text-red-500 hover:text-red-700 text-sm"
                        >
                            Logout
                        </button>
                    </div>
                </header>

                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                    {placements.map((placement) => (
                        <div
                            key={placement.id}
                            onClick={() => navigate(`/placement/${placement.id}`)}
                            className="bg-white p-6 rounded-xl shadow-sm hover:shadow-md transition-shadow cursor-pointer border border-gray-100"
                        >
                            <div className="flex justify-between items-start mb-4">
                                <h3 className="text-xl font-bold text-gray-900">{placement.organisation.name}</h3>
                                <span className="bg-blue-100 text-blue-800 text-xs px-2 py-1 rounded-full">
                                    {placement.profile}
                                </span>
                            </div>
                            <p className="text-gray-600 mb-4 line-clamp-2">{placement.description || 'No description available.'}</p>
                            <div className="flex justify-between text-sm text-gray-500 border-t pt-4 mt-4">
                                <span>Intake: {placement.intake}</span>
                                <span>Min Grade: {placement.minimumGrade}</span>
                            </div>

                            {placement.filters && placement.filters.length > 0 && (
                                <div className="mt-4 pt-4 border-t border-gray-100">
                                    <h4 className="text-xs font-semibold text-gray-500 uppercase tracking-wider mb-2">Eligibility</h4>
                                    <div className="flex flex-wrap gap-2">
                                        {placement.filters.map((filter, idx) => (
                                            <span key={idx} className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-purple-100 text-purple-800">
                                                {filter.domain.program} - {filter.specialisation.code}
                                            </span>
                                        ))}
                                    </div>
                                </div>
                            )}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
