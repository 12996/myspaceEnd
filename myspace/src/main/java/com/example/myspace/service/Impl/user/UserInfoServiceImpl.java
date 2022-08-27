package com.example.myspace.service.Impl.user;

import com.example.myspace.pojo.User;
import com.example.myspace.service.user.account.UserInfoService;
import com.example.myspace.service.user.util.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        Map<String,String> map = new HashMap<>();
        map.put("id",user.getId().toString());
        map.put("username",user.getUsername());
        map.put("photo", user.getPhoto());
        map.put("followercount",user.getFollowercount().toString());
        return map;
    }
}
