package com.example.myspace.service.Impl.post;

import com.example.myspace.mapper.PostMapper;
import com.example.myspace.pojo.Post;
import com.example.myspace.pojo.User;
import com.example.myspace.service.post.PostDeleteService;
import com.example.myspace.service.user.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PostDeleteServiceImpl implements PostDeleteService {
    @Autowired
    PostMapper postMapper;
    @Override
    public Map<String, String> deletePost(Integer id) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        Post post = postMapper.selectById(id);
        Map<String,String> map = new HashMap<>();
        if(!user.getId().equals(post.getUserId())){
            map.put("error_message","您没有权限删除该帖子");
            return map;
        }
        postMapper.deleteById(id);
        map.put("error_message","success");
        return map;
    }
}
