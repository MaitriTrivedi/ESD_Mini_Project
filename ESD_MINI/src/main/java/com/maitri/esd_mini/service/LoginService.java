package com.maitri.esd_mini.service;

import com.maitri.esd_mini.dto.LoginRequest;
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

    public String loginUser(LoginRequest request) {
        Students customer = mapper.toEntity(request);
        // Attempt to find the customer in the database
        Optional<Students> existingCustomer = repo.findByEmail(customer.getEmail());

        if (existingCustomer.isPresent()) {
            // Check if the password matches
            if(!encryptionService.validates(request.password(), existingCustomer.get().getPassword())) {
                return "Wrong Password or Email";
            }
            else {
                return jwtHelper.generateToken(request.email());
            }
        } else {
            return "User not found";
        }
    }
}