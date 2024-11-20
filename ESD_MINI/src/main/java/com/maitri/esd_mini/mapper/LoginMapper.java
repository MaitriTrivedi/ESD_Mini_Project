
package com.maitri.esd_mini.mapper;

import com.maitri.esd_mini.dto.LoginRequest;
import com.maitri.esd_mini.entity.Students;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {
    public Students toEntity(LoginRequest request) {
        return Students.builder()
                .email(request.email())
                .password(request.password())
                .build();
    }
}
