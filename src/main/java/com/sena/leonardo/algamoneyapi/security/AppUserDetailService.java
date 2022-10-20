package com.sena.leonardo.algamoneyapi.security;

import com.sena.leonardo.algamoneyapi.domain.models.UserSystem;
import com.sena.leonardo.algamoneyapi.domain.repositories.UserSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Primary
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private UserSystemRepository userSystemRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserSystem> optionalUser = userSystemRepository.findByEmail(email);
        UserSystem userSystem = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Invalid user or password."));
        return new UserApp(userSystem, getPermissions(userSystem));
    }

    private Collection<? extends GrantedAuthority> getPermissions(UserSystem userSystem) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        userSystem.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
        return authorities;
    }
}
