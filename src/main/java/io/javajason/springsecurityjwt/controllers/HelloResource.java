package io.javajason.springsecurityjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javajason.springsecurityjwt.models.AuthenticationRequest;
import io.javajason.springsecurityjwt.models.AuthenticationResponse;
import io.javajason.springsecurityjwt.services.MyUserDetailsService;
import io.javajason.springsecurityjwt.utilities.JwtUtilities;

@RestController
public class HelloResource {
    @Autowired
    private AuthenticationManager autheticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtilities jwtUtilities;
    @GetMapping({"/hello"})
    public String Hello(){
        return "Hello World";
    }

    @RequestMapping(value="/authenticate",method=RequestMethod.POST )
    public ResponseEntity<?>  createAuthenticationToken(@RequestBody AuthenticationRequest 
    authenticationRequest) throws Exception{
       try{
             autheticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
               authenticationRequest.getUsername(),
               authenticationRequest.getPassword()
               ));
            }
            catch(BadCredentialsException e){
                throw new Exception("Incorrecto username o password ",e);
            }
            final UserDetails userDetails =userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
            final String jwt=jwtUtilities.generateToken(userDetails);

            return  ResponseEntity.ok(new AuthenticationResponse(jwt));
    } 

}
