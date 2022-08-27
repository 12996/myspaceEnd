package com.example.myspace.service.Impl.user;

import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.User;
import com.example.myspace.service.user.account.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public String updateUser(Integer userId, Integer attention, Integer readNumber) {
        User user = userMapper.selectById(userId);
        user.setAttention(user.getAttention() + attention);
        user.setReadNumber(user.getReadNumber() + readNumber);
        return "success";
    }
}
