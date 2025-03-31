package edu.iu.p466.prime_service.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.iu.p466.prime_service.service.IPrimeService;

@RestController
@CrossOrigin

@RequestMapping("/primes")
public class PrimesController {

    IPrimeService primesService;

    public PrimesController(IPrimeService primesService){
        this.primesService = primesService;
    }

    @GetMapping("/{n}")
    public boolean isPrime(@PathVariable int n) {
        return primesService.isPrime(n);
    }
    
}