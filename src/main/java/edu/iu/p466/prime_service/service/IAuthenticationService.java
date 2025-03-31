package edu.iu.p466.prime_service.service;

import java.io.IOException;

import edu.iu.p466.prime_service.model.Customer;

public interface IAuthenticationService {

    boolean register(Customer customer) throws IOException;
    String login(Customer customer) throws IOException;
    
}
