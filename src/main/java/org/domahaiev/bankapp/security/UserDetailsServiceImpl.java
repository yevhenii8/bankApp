package org.domahaiev.bankapp.security;

import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.exceptions.errorMessages.ErrorMessage;
import org.domahaiev.bankapp.model.Account;
import org.domahaiev.bankapp.model.Role;
import org.domahaiev.bankapp.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public final AccountRepository accountRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.getAccountByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));

        return withUsername(username)
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(getAuthorities(account.getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));

            role.getAuthorities().forEach(
                    authority -> authorities.add(new SimpleGrantedAuthority(authority.getName()))
            );
        }

        return authorities;
    }
}
