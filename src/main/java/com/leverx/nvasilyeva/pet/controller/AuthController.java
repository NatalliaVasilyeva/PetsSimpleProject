package com.leverx.nvasilyeva.pet.controller;

import com.leverx.nvasilyeva.pet.dto.auth.JwtRequest;
import com.leverx.nvasilyeva.pet.dto.auth.JwtResponse;
import com.leverx.nvasilyeva.pet.dto.request.OwnerCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.OwnerResponseDTO;
import com.leverx.nvasilyeva.pet.security.JwtUserDetailsService;
import com.leverx.nvasilyeva.pet.security.jwt.JwtTokenUtil;
import com.leverx.nvasilyeva.pet.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Objects;

import static com.leverx.nvasilyeva.pet.config.ControllerConstant.AUTH_API;

@RestController
@CrossOrigin
@RequestMapping(AUTH_API)
public class AuthController {

    private final OwnerService ownerService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;

    @Autowired
    public AuthController(OwnerService ownerService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager, JwtUserDetailsService userDetailsService) {
        this.ownerService = ownerService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<OwnerResponseDTO> register(@RequestBody @Valid OwnerCreateDTO ownerCreateDTO) {
        OwnerResponseDTO ownerResponseDTO = ownerService.save(ownerCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ownerResponseDTO);
    }

    @PostMapping("/token")
    public ResponseEntity<?> auth(@RequestBody JwtRequest request) throws Exception {

        authenticate(request.getUsername(), request.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
