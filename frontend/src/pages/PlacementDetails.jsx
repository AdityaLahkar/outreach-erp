import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getPlacementStudents, selectStudent } from '../services/api';

const PlacementDetails = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [students, setStudents] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchStudents();
    }, [id]);

    const fetchStudents = async () => {
        try {
            const response = await getPlacementStudents(id);
            setStudents(response.data);
        } catch (error) {
            console.error('Failed to fetch students', error);
        } finally {
            setLoading(false);
        }
    };

    const handleSelect = async (studentId) => {
        try {
            await selectStudent(id, studentId);
            // Refresh list to show updated status
            fetchStudents();
            alert('Student selected successfully!');
        } catch (error) {
            alert('Failed to select student');
        }
    };

    return (
        <div className="min-h-screen bg-gray-50 p-8">
            <div className="max-w-6xl mx-auto">
                <button
                    onClick={() => navigate('/dashboard')}
                    className="mb-6 text-blue-600 hover:text-blue-800 flex items-center"
                >
                    &larr; Back to Dashboard
                </button>

                <h1 className="text-3xl font-bold text-gray-800 mb-6">Student Eligibility & Selection</h1>

                {loading ? (
                    <p>Loading...</p>
                ) : (
                    <div className="bg-white rounded-xl shadow-sm overflow-hidden border border-gray-100">
                        <table className="w-full text-left">
                            <thead className="bg-gray-50 border-b">
                                <tr>
                                    <th className="p-4 font-semibold text-gray-600">Roll No</th>
                                    <th className="p-4 font-semibold text-gray-600">Name</th>
                                    <th className="p-4 font-semibold text-gray-600">CGPA</th>
                                    <th className="p-4 font-semibold text-gray-600">Domain</th>
                                    <th className="p-4 font-semibold text-gray-600">Status</th>
                                    <th className="p-4 font-semibold text-gray-600">Action</th>
                                </tr>
                            </thead>
                            <tbody className="divide-y divide-gray-100">
                                {students.map((student) => (
                                    <tr key={student.studentId} className="hover:bg-gray-50">
                                        <td className="p-4 text-gray-900">{student.rollNumber}</td>
                                        <td className="p-4 text-gray-900">{student.firstName} {student.lastName}</td>
                                        <td className="p-4 text-gray-900">{student.cgpa}</td>
                                        <td className="p-4 text-gray-500">{student.domain} / {student.specialisation}</td>
                                        <td className="p-4">
                                            {student.eligible ? (
                                                <span className="text-green-600 bg-green-50 px-2 py-1 rounded text-xs font-medium">Eligible</span>
                                            ) : (
                                                <span className="text-red-600 bg-red-50 px-2 py-1 rounded text-xs font-medium" title={student.eligibilityReason}>
                                                    Ineligible
                                                </span>
                                            )}
                                        </td>
                                        <td className="p-4">
                                            {student.applicationStatus === 'SELECTED' ? (
                                                <span className="text-green-700 font-bold flex items-center">
                                                    ✓ Selected
                                                </span>
                                            ) : (
                                                <button
                                                    onClick={() => handleSelect(student.studentId)}
                                                    disabled={!student.eligible}
                                                    className={`px-4 py-2 rounded-lg text-sm font-medium transition-colors ${student.eligible
                                                            ? 'bg-blue-600 text-white hover:bg-blue-700'
                                                            : 'bg-gray-100 text-gray-400 cursor-not-allowed'
                                                        }`}
                                                >
                                                    Select
                                                </button>
                                            )}
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </div>
        </div>
    );
};

export default PlacementDetails;
