package com.devops.User;

public interface UserService {
    boolean userRegister(User user);
    boolean userLogin(User user);
    User findUserByEmailId(String emailId);
}
