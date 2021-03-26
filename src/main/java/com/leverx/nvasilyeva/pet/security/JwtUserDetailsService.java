package com.leverx.nvasilyeva.pet.security;

import com.leverx.nvasilyeva.pet.entity.Owner;
import com.leverx.nvasilyeva.pet.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private OwnerService ownerService;

    @Override
    public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerService.findByUsername(username);
        if(owner==null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return JwtUserDetails.fromOwnerToCustomUserDetails(owner);
    }
}
