package com.milos.wir.managment.user.endpoint;

import com.milos.wir.managment.user.facade.UserFacade;
import com.milos.wir.managment.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/account")
public class UserEndpoint {

    private UserFacade userFacade;

    @Autowired
    public UserEndpoint(UserFacade userFacade) {
        Assert.notNull(userFacade, "UserFacade must not be null!");
        this.userFacade = userFacade;
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @GetMapping(path = "{id}")
    public ResponseEntity<User> findAccountById(@PathVariable("id") Long id) {
        User user = userFacade.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUserAccount(@RequestBody User user) {
        User savedUser = userFacade.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping(path = {"{userId}"})
    public ResponseEntity<User> modifyUserAccount(@RequestBody User user,@PathVariable String userId) {
        User savedUser = userFacade.updateUser(user, userId);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

}
