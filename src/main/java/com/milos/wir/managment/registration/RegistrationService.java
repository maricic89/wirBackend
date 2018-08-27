package com.milos.wir.managment.registration;

import java.util.Optional;

public interface RegistrationService {

    Optional<RegistrationTokenEntity> confirmEmailRegistration(String token);
}
