package com.example.myspace.service.Impl.follower;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspace.mapper.FollowerMapper;
import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.Follower;
import com.example.myspace.pojo.User;
import com.example.myspace.service.follower.FollowerSelectService;
import com.example.myspace.service.user.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FollowerSelectServiceImpl implements FollowerSelectService {
    @Autowired
    FollowerMapper followerMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> select(Integer follower_user,Boolean f) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl  loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();;
        Integer nowUser = user.getId();
        QueryWrapper<Follower> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_user",nowUser).eq("follower_user",follower_user);
        Follower follower = followerMapper.selectOne(queryWrapper);
        if(follower == null ){
            follower = new Follower(null,nowUser,follower_user,0);
            followerMapper.insert(follower);
        }
        Map<String,String> map = new HashMap<>();
        if(f){
            User user1 = userMapper.selectById(follower_user);
            follower = followerMapper.selectOne(queryWrapper);
            if(follower.getIsFollower() == 1){
                follower.setIsFollower(0);
                user1.setFollowercount(user1.getFollowercount()-1);
            }else {
                follower.setIsFollower(1);
                user1.setFollowercount(user1.getFollowercount()+1);
            }
            userMapper.updateById(user1);
            followerMapper.updateById(follower);
        }
        String res ="";
        if(follower.getIsFollower() ==1){
            res = "true";
        }else {
            res = "false";
        }
        map.put("is_follower",res);
        return map;
    }
}
