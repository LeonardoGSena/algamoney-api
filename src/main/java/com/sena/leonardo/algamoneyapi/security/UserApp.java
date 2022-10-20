package com.sena.leonardo.algamoneyapi.security;

import com.sena.leonardo.algamoneyapi.domain.models.UserSystem;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserApp extends User {
    private UserSystem userSystem;


    public UserApp(UserSystem userSystem, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userSystem.getEmail(), userSystem.getPassword(), authorities);
        this.userSystem = userSystem;
    }

    public UserSystem getUserSystem() {
        return userSystem;
    }
}
