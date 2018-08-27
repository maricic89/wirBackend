package com.milos.wir.managment.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationTokenRepository registrationTokenRepository;

    @Autowired
    public RegistrationServiceImpl(RegistrationTokenRepository registrationTokenRepository) {
        this.registrationTokenRepository = registrationTokenRepository;
    }

    @Override
    public Optional<RegistrationTokenEntity> confirmEmailRegistration(String token) {
        return registrationTokenRepository.findByRegistrationToken(token);
    }
}
