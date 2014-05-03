package com.umeca.service.account;

import com.umeca.model.entities.account.Role;
import com.umeca.repository.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Israel
 * Date: 4/28/14
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.umeca.model.entities.account.User domainUser;

        try {
            domainUser = userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(domainUser == null)
            throw new UsernameNotFoundException("Usuario no encontrado");

        try{
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            List<GrantedAuthority> authorities = new ArrayList<>();
            List<Role> roles = domainUser.getRoles();

            for(Role role : roles){
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }

            return new User(
                    domainUser.getUsername(),
                    domainUser.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    authorities);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
