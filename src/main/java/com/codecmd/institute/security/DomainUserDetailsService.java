package com.codecmd.institute.security;

import com.codecmd.institute.domain.Student;
import com.codecmd.institute.repository.StudentRepository;
import com.codecmd.institute.web.rest.error.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        if (new EmailValidator().isValid(login, null)) {
            return studentRepository.findOneByEmailIgnoreCase(login)
                    .map(student -> createSpringSecurityUser(login, student))
                    .orElseThrow(StudentNotFoundException::new);
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return studentRepository.findOneByLoginIgnoreCase(lowercaseLogin)
                .map(student -> createSpringSecurityUser(lowercaseLogin, student))
                .orElseThrow(StudentNotFoundException::new);
    }

    private User createSpringSecurityUser(String lowercaseLogin, Student student) {
        log.debug("Authenticating {}, {}, {}", lowercaseLogin, student.getLogin(), student.getEmail());
        List<GrantedAuthority> grantedAuthorities = Collections.emptyList();
        return new User(student.getLogin(), student.getPassword(), grantedAuthorities);
    }
}
