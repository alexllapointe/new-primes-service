package edu.iu.p466.prime_service.service;

import java.io.IOException;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.iu.p466.prime_service.model.Customer;
import edu.iu.p466.prime_service.repository.IAuthenticationRepository;

@Service
public class AuthenticationService implements IAuthenticationService, UserDetailsService {

    IAuthenticationRepository authenticationRepository;

    public AuthenticationService(IAuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }
    
    @Override

    public boolean register(Customer customer) throws IOException {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String passwordEncoded = bc.encode(customer.getPassword());
        customer.setPassword(passwordEncoded);
        return authenticationRepository.save(customer);
    }
    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Customer customer = authenticationRepository.findByUsername(username);
            if (customer == null) {
                throw new UsernameNotFoundException("");
            }

            return User
                .withUsername(username)
                .password(customer.getPassword())
                .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String login(Customer customer) throws IOException {
        Customer foundCustomer = authenticationRepository.findByUsername(customer.getUsername());
        if (foundCustomer != null && new BCryptPasswordEncoder().matches(customer.getPassword(), foundCustomer.getPassword())) {
            return "Login successful";
        }
        return "Invalid credentials";
    }
}
