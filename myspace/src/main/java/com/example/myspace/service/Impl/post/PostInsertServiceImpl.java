package com.example.myspace.service.Impl.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspace.mapper.PostMapper;
import com.example.myspace.pojo.Post;
import com.example.myspace.pojo.User;
import com.example.myspace.service.post.PostInsertService;
import com.example.myspace.service.user.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PostInsertServiceImpl implements PostInsertService {
    @Autowired
    PostMapper postMapper;
    @Override
    public Map<String, String> insert(Map<String,String> data) {
       UsernamePasswordAuthenticationToken authentication =
               (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
       UserDetailsImpl logUser = (UserDetailsImpl) authentication.getPrincipal();
       User user = logUser.getUser();

       Integer id = Integer.valueOf(data.get("id"));
       String title = data.get("title");
       String photo = data.get("photo");
       String introduce = data.get("introduce");
       String content = data.get("content");

       Map<String, String> map = new HashMap<>();
       if(user.getId() != id){
           map.put("error_message", "请登录您的账号或者使用合法的账号操作");
           return map;
       }
       if(title == null || title.length() == 0){
           map.put("error_message", "标题不能为空");
           return map;
       }
       if(title.length() > 300){
           map.put("error_message", "标题长度不能大于300");
           return map;
       }
       if(introduce == null || introduce.length() == 0){
           introduce = "这个人很懒什么都没有留下~~";
       }
       if(content == null || content.length() == 0){
           map.put("error_message", "内容不能为空");
           return map;
       }
       if(content.length() > 10000){
           map.put("error_message", "内容不能大于10000");
           return map;
       }
       Post post = new Post(null, user.getId(), content, title, photo, introduce, new Date(), new Date());
       postMapper.insert(post);
       map.put("error_message", "success");
       return map;
    }
}
