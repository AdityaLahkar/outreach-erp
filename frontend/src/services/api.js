import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
});

export const login = (token) => api.post('/auth/login', { token });
export const getPlacements = () => api.get('/placements');
export const getPlacementStudents = (id) => api.get(`/placements/${id}/students`);
export const selectStudent = (placementId, studentId) => api.post(`/placements/${placementId}/students/${studentId}/select`);

export default api;
