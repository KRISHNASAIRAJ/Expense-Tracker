package com.devops.User;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
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
}
