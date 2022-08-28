package io.github.ViniciusSantos01.Implementation;

import io.github.ViniciusSantos01.Exception.InvalidPasswordException;
import io.github.ViniciusSantos01.Repository.UserRepository;
import io.github.ViniciusSantos01.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Transactional
    public UserEntity save(UserEntity userEntity){
        return repository.save(userEntity);
    }

    public UserDetails authenticate (UserEntity userEntity){
        UserDetails user = loadUserByUsername(userEntity.getLogin());
        boolean samePassword = encoder.matches(userEntity.getPassword(), user.getPassword());
        if(samePassword){
            return user;
        }
        throw new InvalidPasswordException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        String [] roles = userEntity.isAdmin() ? new String[] {"USER", "ADMIN"} : new String[]{"USER"};

        return User
                .builder()
                .username(userEntity.getLogin())
                .password(userEntity.getPassword())
                .roles(roles)
                .build();
    }
}
