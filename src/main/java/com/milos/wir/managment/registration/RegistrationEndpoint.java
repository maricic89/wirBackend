package com.milos.wir.managment.registration;

import com.milos.wir.managment.user.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/registration")
public class RegistrationEndpoint {

    private RegistrationFacade registrationFacade;

    private UserFacade userFacade;

    @Autowired
    public RegistrationEndpoint(RegistrationFacade registrationFacade, UserFacade userFacade) {
        this.registrationFacade = registrationFacade;
        this.userFacade = userFacade;
    }

    @GetMapping(path = { "confirm/{registrationToken}" })
    public ResponseEntity modifyUserAccount(@PathVariable String registrationToken) {
        Optional<RegistrationTokenEntity> registrationTokenEntityOptional = registrationFacade.confirmEmailRegistration(registrationToken);
        if (!registrationTokenEntityOptional.isPresent() || registrationTokenEntityOptional.get().getExpiryDate().isBefore(LocalDate.now())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userFacade.enableUser(registrationTokenEntityOptional.get().getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
