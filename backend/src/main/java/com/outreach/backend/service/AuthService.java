package com.outreach.backend.service;

import com.outreach.backend.entity.Employee;
import com.outreach.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public Employee login(String token) {
        // 1. Verify Token with Google
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + token;
        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response == null || response.get("email") == null) {
                throw new RuntimeException("Invalid Google Token");
            }

            String email = (String) response.get("email");

            // 2. Check if email exists in DB
            Optional<Employee> employee = employeeRepository.findByEmail(email);
            if (employee.isPresent()) {
                return employee.get();
            } else {
                throw new RuntimeException("User not found: " + email);
            }
        } catch (Exception e) {
            throw new RuntimeException("Token verification failed: " + e.getMessage());
        }
    }
}
