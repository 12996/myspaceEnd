package com.example.myspace.service.Impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.User;
import com.example.myspace.service.user.account.ResignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResignSerceImpl implements ResignService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> resign(Map<String,String> data) {
        String username = data.get("username");
        String password = data.get("password");
        String confirmedPassword = data.get("confirmedPassword");
        Map<String,String> map = new HashMap<>();
        if(username == null){
            map.put("error_massage","用户名不能为空");
            return  map;
        }

        if(password == null || confirmedPassword == null){
            map.put("error_massage","用户密码不能为空");
            return  map;
        }

        if(username.length() == 0)
        {
            map.put("error_message","用户名不能为空");
            return  map;
        }

        if(password.length() ==0 || confirmedPassword.length() == 0){
            map.put("error_message","用户密码不能为空");
            return  map;
        }

        if(username.length()>=100){
            map.put("error_message","用户名不能大于一百");
            return  map;
        }

        if(password.length() > 100 || confirmedPassword.length() > 100){
            map.put("error_message","用户密码不能大于一百");
            return  map;
        }

        if(!password.equals(confirmedPassword)){
            map.put("error_message","两次输入不一致");
            return  map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            map.put("error_message","用户名已存在");
            return  map;
        }
        String encodingPassword = passwordEncoder.encode(password);
        String photo = "https://cdn.acwing.com/media/user/profile/photo/68476_lg_1c71788bcd.jpg";

        User user = new User(null,username,encodingPassword,photo,null, null, null);
        userMapper.insert(user);
        map.put("error_message","success");
        return map;
    }

}
