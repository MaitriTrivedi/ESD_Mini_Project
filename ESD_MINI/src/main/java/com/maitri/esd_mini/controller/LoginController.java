package com.maitri.esd_mini.controller;

import com.maitri.esd_mini.dto.LoginRequest;
import com.maitri.esd_mini.dto.LoginResponse;
import com.maitri.esd_mini.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody @Valid LoginRequest request) {
        System.out.println("==================== login");
        return ResponseEntity.ok(loginService.loginUser(request));
    }
}