package com.milos.wir.managment.user.service;

import com.milos.wir.managment.registration.RegistrationTokenEntity;
import com.milos.wir.managment.registration.RegistrationTokenRepository;
import com.milos.wir.managment.user.entity.RoleEntity;
import com.milos.wir.managment.user.entity.UserEntity;
import com.milos.wir.managment.user.entity.UserRoleEntity;
import com.milos.wir.managment.user.repo.RoleRepository;
import com.milos.wir.managment.user.repo.UserRepository;
import com.milos.wir.managment.user.repo.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;
    private RegistrationTokenRepository registrationTokenRepository;
    private JavaMailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository,
                           RegistrationTokenRepository registrationTokenRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.registrationTokenRepository = registrationTokenRepository;
        this.mailSender = mailSender;
    }

    @Override
    public UserEntity findById(Long id) {
        //xxx
        return userRepository.findById(id).get();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        UserEntity savedEntity = userRepository.save(userEntity);
        saveUserRoles(userEntity, savedEntity.getId());
        String registrationToken = saveAndReturnUserRegistrationToken(savedEntity.getLastName(), savedEntity.getUsername());

        String appUrl = "http://localhost:8080/api/registration";

        sendMail(savedEntity, registrationToken, appUrl);
        return savedEntity;
    }

    @Async
    public void sendMail(UserEntity savedEntity, String registrationToken, String appUrl) {
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(savedEntity.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail
                .setText("To confirm your e-mail address, please click the link below:\n" + appUrl + "/confirm/" + registrationToken);
        registrationEmail.setFrom("maricic89@gmail.com");

        mailSender.send(registrationEmail);
    }

    private void saveUserRoles(UserEntity userEntity, Long userId) {
        List<UserRoleEntity> userRoleEntities = userEntity.getUserRoles();
        if (userRoleEntities != null) {
            for (UserRoleEntity newUserRoleEntity : userRoleEntities) {
                RoleEntity roleEntity = roleRepository.findByCode(newUserRoleEntity.getRole().getCode());
                newUserRoleEntity.setRole(roleEntity);
                newUserRoleEntity.setUser(userEntity);

                userRoleRepository.save(newUserRoleEntity);
            }
        }
    }

    private String saveAndReturnUserRegistrationToken(String userEmail, String username) {
        String registrationToken = UUID.randomUUID().toString();
        RegistrationTokenEntity registrationTokenEntity = new RegistrationTokenEntity();
        registrationTokenEntity.setEmail(userEmail);
        registrationTokenEntity.setExpiryDate(LocalDate.now().plusDays(1));
        registrationTokenEntity.setRegistrationToken(registrationToken);
        registrationTokenEntity.setUsername(username);

        registrationTokenRepository.save(registrationTokenEntity);

        return registrationToken;
    }

    @Override
    public boolean checkIfUserNameExists(String userName) {
        Optional<UserEntity> accountEntityOpt = userRepository.findByUsername(userName);
        return accountEntityOpt.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> accountEntity = userRepository.findByUsername(username);
        if (!accountEntity.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        List<UserRoleEntity> userPermissions = userRoleRepository.findAllByUserId(accountEntity.get().getId());
        accountEntity.get().setUserRoles(userPermissions);
        return accountEntity.get();
    }

    @Override
    public List<String> getActiveAccountPermissionCodesByUserId(Long id) {
        return userRepository.getActiveAccountPermissionCodesByUserId(id);
    }

    @Override
    public void findUserByUsername(String username) {
        Optional<UserEntity> userEntityOpt = userRepository.findByUsername(username);
        if(userEntityOpt.isPresent()){
            userEntityOpt.get().setEnabled(Boolean.TRUE);
            userRepository.save(userEntityOpt.get());
        }
    }
}
