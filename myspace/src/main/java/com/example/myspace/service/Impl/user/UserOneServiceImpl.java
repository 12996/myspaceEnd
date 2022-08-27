package com.example.myspace.service.Impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspace.mapper.FollowerMapper;
import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.Follower;
import com.example.myspace.pojo.User;
import com.example.myspace.service.user.account.UserOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOneServiceImpl implements UserOneService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    FollowerMapper followerMapper;
    @Override
    public User getOne(Integer id) {
        User user = userMapper.selectById(id);;
        if(user != null) user.setPassword("");
        QueryWrapper<Follower> queryWrapper = new QueryWrapper<>();
        Integer attention = Math.toIntExact(followerMapper.selectCount(queryWrapper.eq("login_user", id).eq("is_follower", 1)));
        user.setAttention(attention);
        return user;
    }
}
