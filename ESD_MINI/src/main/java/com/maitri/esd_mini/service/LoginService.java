package com.maitri.esd_mini.service;

import com.maitri.esd_mini.dto.LoginRequest;
import com.maitri.esd_mini.dto.LoginResponse;
import com.maitri.esd_mini.entity.Students;
import com.maitri.esd_mini.helper.EncryptionService;
import com.maitri.esd_mini.helper.JWTHelper;
import com.maitri.esd_mini.mapper.LoginMapper;
import com.maitri.esd_mini.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final StudentRepo repo;
    private final LoginMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public LoginResponse loginUser(LoginRequest request) {
        Students customer = mapper.toEntity(request);

        // Attempt to find the customer in the database
        Optional<Students> existingCustomer = repo.findByEmail(customer.getEmail());
        System.out.println("-------LOGIN SERVICE");

        if (existingCustomer.isPresent()) {
            System.out.println("-------LOGIN SERVICE1");
            if (!encryptionService.validates(request.password(), existingCustomer.get().getPassword())) {
                System.out.println("-------LOGIN SERVICE2");
                return new LoginResponse(false, "Wrong Password", null, null);
            } else {
                System.out.println("-------LOGIN SERVICE3");
                String token = jwtHelper.generateToken(request.email());
                return new LoginResponse(true, "Login successful", token, existingCustomer.get().getStudentId());
            }
        } else {
            System.out.println("-------LOGIN SERVICE4");
            return new LoginResponse(false, "User not found", null, null);
        }
    }
}
