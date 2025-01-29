package com.devops.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean userRegister(User user){
    if(userRepository.findByEmailId(user.getEmailId()).isPresent()){
        return false;
    }
    String hashedPassword=bCryptPasswordEncoder.encode(user.getHashedPassword());
    user.setHashedPassword(hashedPassword);

    userRepository.save(user);
    return true;
    }
    @Override
    public boolean userLogin(User user) {
        return userRepository.findByEmailId(user.getEmailId()).map(existingUser->bCryptPasswordEncoder.matches(user.getHashedPassword(),existingUser.getHashedPassword())).orElse(false);
    }

    @Override
    public User findUserByEmailId(String emailId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmailId(emailId);
        if (user.isPresent()) {
            return user.get(); // Return the User entity directly
        }
        throw new UsernameNotFoundException("User not found with email: " + emailId);
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        User user = findUserByEmailId(emailId);
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmailId())
                .password(user.getHashedPassword())
                .roles("USER") // Adjust roles as needed
                .build();
    }
}
