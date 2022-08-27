package com.example.myspace.service.Impl.post;

import com.example.myspace.mapper.PostMapper;
import com.example.myspace.pojo.Post;
import com.example.myspace.pojo.User;
import com.example.myspace.service.post.PostUpdataService;
import com.example.myspace.service.user.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PostUpdateServiceImp implements PostUpdataService {
    @Autowired
    PostMapper postMapper;

    @Override
    public Map<String, String> updatePost(Map<String, String> data) {
        Integer id = Integer.valueOf(data.get("id"));
        String title = data.get("title");
        String photo = data.get("photo");
        String content = data.get("content");
        String introduce = data.get("introduce");

        Post post = postMapper.selectById(id);

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl logUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = logUser.getUser();

        Map<String, String> map = new HashMap<>();
        if(user.getId() != post.getUserId()){
            map.put("error_message", "您没有权限修改此文章");
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
        Post post1 = new Post(post.getId(), post.getUserId(), content, title, photo, introduce, post.getSendtime(), new Date());
        postMapper.updateById(post1);
        map.put("error_message", "success");
        return map;
    }
}
