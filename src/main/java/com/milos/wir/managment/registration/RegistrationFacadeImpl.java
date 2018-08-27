package com.milos.wir.managment.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationFacadeImpl(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public Optional<RegistrationTokenEntity> confirmEmailRegistration(String token) {
        return registrationService.confirmEmailRegistration(token);
    }
}
