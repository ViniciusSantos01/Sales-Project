package io.github.ViniciusSantos01.Implementation;

import io.github.ViniciusSantos01.Repository.UserRepository;
import io.github.ViniciusSantos01.entity.UserEntiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public UserEntiry save(UserEntiry userEntiry){
        return repository.save(userEntiry);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntiry userEntiry = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        String[] roles = userEntiry.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(userEntiry.getLogin())
                .password(userEntiry.getPassword())
                .roles(roles)
                .build();
    }
}
