package com.example.myspace.service.user.account;

import com.example.myspace.pojo.User;

public interface UpdateUserService {
    String updateUser(Integer userId, Integer attention, Integer readNumber);
}
