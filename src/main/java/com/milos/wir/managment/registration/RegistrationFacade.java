package com.milos.wir.managment.registration;

import java.util.Optional;

public interface RegistrationFacade {

    Optional<RegistrationTokenEntity> confirmEmailRegistration(String token);
}
