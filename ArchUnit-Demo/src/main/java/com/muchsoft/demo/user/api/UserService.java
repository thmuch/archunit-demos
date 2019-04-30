package com.muchsoft.demo.user.api;

import com.muchsoft.demo.user.backend.UserRepository;

public interface UserService {

    default UserRepository getRepo() {
        return null;
    }
}
