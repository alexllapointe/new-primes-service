/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package edu.iu.p466.prime_service.repository;

import java.io.IOException;

import edu.iu.p466.prime_service.model.Customer;

public interface IAuthenticationRepository {

    boolean save(Customer customer) throws IOException;
    Customer findByUsername(String username) throws IOException;

}
