package com.example.myspace.service.Impl.user;

import com.example.myspace.mapper.UserMapper;
import com.example.myspace.pojo.User;
import com.example.myspace.service.user.account.UpdateUsernamePhotoService;
import com.example.myspace.service.user.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateUsernamePhotoServiceImpl implements UpdateUsernamePhotoService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> updateUsernamePhoto(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        String username = data.get("username");
        String photo = data.get("photo");
        Map<String, String> resp = new HashMap<>();
        if(username == null || username.length() == 0){
            resp.put("error_message", "用户名不能为空");
            return resp;
        }
        user.setPhoto(photo); user.setUsername(username);
        userMapper.updateById(user);
        resp.put("error_message", "success");
        return resp;
    }

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> updatePassword(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        String password = data.get("password");
        Map<String, String> resp = new HashMap<>();
        if(!passwordEncoder.matches(password, user.getPassword())) {
            resp.put("error_message", "您输入的密码不对");
            return resp;
        }
        String alterPassword = data.get("alterPassword");
        String confirmedPassword = data.get("confirmedPassword");
        System.out.println(alterPassword);
        if(alterPassword == null || alterPassword.length() == 0){
            resp.put("error_message", "修改后的密码不能为空");
            return resp;
        }
        if(alterPassword.length() >= 100){
            resp.put("error_message", "密码长度不能大于100");
            return resp;
        }
        if(!alterPassword.equals(confirmedPassword)){
            resp.put("error_message", "两次密码不一致");
            return resp;
        }
        System.out.println(alterPassword);
        user.setPassword(passwordEncoder.encode(alterPassword));
        userMapper.updateById(user);
        resp.put("error_message", "success");
        return resp;
    }

}
