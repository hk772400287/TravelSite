package com.ggw.travel.service;

import com.ggw.travel.entity.User;

public interface UserService {
    void register(User user);

    User login(User user);
}
